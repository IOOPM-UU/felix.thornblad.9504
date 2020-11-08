typedef union elem elem_t;

union elem
{
  int i;
  unsigned int ui;
  bool b;
  float f;
  void *p;
};

#define int_elem(x) (elem_t) { .i=(x) }
#define bool_elem(x) (elem_t) { .b=(x) }
#define pointer_elem(x) (elem_t) { .p=(x) }

typedef struct option option_t;

struct option {
    bool success;
    elem_t value;
};

#define Success(v)      (option_t) { .success = true, .value = v };
#define Failure()       (option_t) { .success = false };
#define Successful(o)   (o.success == true)
#define Unsuccessful(o) (o.success == false)

/// Compares two elements and returns true if they are equal
typedef bool(*ioopm_eq_function)(elem_t a, elem_t b);
typedef int (*ioopm_hash_function)(elem_t key);
typedef bool(*ioopm_predicate)(elem_t key, elem_t value, ioopm_eq_function eq, void *extra);
typedef void(*ioopm_apply_function)(elem_t key, elem_t *value, void *extra);
