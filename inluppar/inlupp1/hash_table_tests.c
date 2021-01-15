#include "hash_table.h"
#include <string.h>
#include <CUnit/Basic.h>
#include "hash_table.c"
#include <stdbool.h>

int init_suite(void)
{
  return 0;
}

int clean_suite(void)
{
  return 0;
}

void test_insert1()
{
  ioopm_hash_table_t *ht = ioopm_hash_table_create();
  ioopm_hash_table_insert(ht, 5, "43");
  ioopm_hash_table_insert(ht, 22, "42");
  ioopm_hash_table_insert(ht, 39, "41");
  
  ioopm_hash_table_insert(ht, 4, "43");
  ioopm_hash_table_insert(ht, 38, "41");
  ioopm_hash_table_insert(ht, 21, "42");
  
  entry_t *current1 = &ht->buckets[5];
  entry_t *current2 = &ht->buckets[4];
  while(current1->next != NULL)
	{
	  CU_ASSERT(current1->value == current2->value);
	  current1 = current1->next;
	  current2 = current2->next;
	}
  ioopm_hash_table_destroy(ht);
}
void test_insert2()
{
  ioopm_hash_table_t *ht = ioopm_hash_table_create();
  ioopm_hash_table_insert(ht, 5, "43");
  ioopm_hash_table_insert(ht, 22, "42");
  ioopm_hash_table_insert(ht, 39, "41");
  ioopm_hash_table_insert(ht, 5, "45");
  CU_ASSERT(strcmp(ht->buckets[5].next->value , "45") == 0);
  ioopm_hash_table_destroy(ht);
}

void test_lookup1()
{
  int key = 5;
	ioopm_hash_table_t *ht = ioopm_hash_table_create();
  ioopm_hash_table_insert(ht, key, "42");
  char **value_ptr = ioopm_hash_table_lookup(ht, key);
  char *value = NULL; // no value yet
  if (value_ptr != NULL)
  {
    // key was in ht
    value = *value_ptr; // Value now points to the string key maps to in ht
    //*value_ptr = "43"; // We just updated the value!
    CU_ASSERT(strcmp(value, "42") == 0);
  }
  else
  {
    CU_ASSERT(false);
  }
  ioopm_hash_table_destroy(ht);
  //print_bucket(ht, 5);
}
void test_lookup2()
{
  int key = 5;
	ioopm_hash_table_t *ht = ioopm_hash_table_create();
  char **value_ptr = ioopm_hash_table_lookup(ht, key);
  char *value = NULL; // no value yet
  if (value_ptr != NULL)
  {
    // key was in ht
    value = *value_ptr; // Value now points to the string key maps to in ht
    //*value_ptr = "43"; // We just updated the value!
    CU_ASSERT(strcmp(value, "42") == 0);
  }
  else
  {
    CU_ASSERT(true);
  }
  ioopm_hash_table_destroy(ht);
  //print_bucket(ht, 5);
}


void test_remove1()
{
  int key = 5;
	ioopm_hash_table_t *ht = ioopm_hash_table_create();
  ioopm_hash_table_insert(ht, key, "42");
  char **value_ptr = ioopm_hash_table_remove(ht, key);
  char *value = NULL; // no value yet
  if (value_ptr != NULL)
  {
    // key was in ht
    value = *value_ptr; // Value now points to the string key maps to in ht
    //*value_ptr = "43"; // We just updated the value!
    CU_ASSERT(strcmp(value, "42") == 0);
  }
  else
  {
    CU_ASSERT(false);
  }
  ioopm_hash_table_destroy(ht);
  free(value_ptr);
  
}
void test_remove2()
{
  int key = 5;
	ioopm_hash_table_t *ht = ioopm_hash_table_create();
  char **value_ptr = ioopm_hash_table_remove(ht, key);
  char *value = NULL; // no value yet
  if (value_ptr != NULL)
  {
    // key was in ht
    value = *value_ptr; // Value now points to the string key maps to in ht
    //*value_ptr = "43"; // We just updated the value!
    CU_ASSERT(strcmp(value, "42") == 0);
    
  }
  else
  {
     CU_ASSERT(true);
  }
  ioopm_hash_table_destroy(ht);
  free(value_ptr);
}

int main()
{
  CU_pSuite test_insert = NULL;
  CU_pSuite test_lookup = NULL;
  CU_pSuite test_remove = NULL;

  if (CUE_SUCCESS != CU_initialize_registry())
    return CU_get_error();

  test_insert = CU_add_suite("test_insert", init_suite, clean_suite);
  if (NULL == test_insert)
    {
      CU_cleanup_registry();
      return CU_get_error();
    }

  if (
    (NULL == CU_add_test(test_insert, "test_insert1", test_insert1)) ||
    (NULL == CU_add_test(test_insert, "test_insert2", test_insert2))
  )
    {
      CU_cleanup_registry();
      return CU_get_error();
    }
  
  
  
  test_lookup = CU_add_suite("test_lookup", init_suite, clean_suite);
  if (NULL == test_lookup)
    {
      CU_cleanup_registry();
      return CU_get_error();
    }

  if (
    (NULL == CU_add_test(test_lookup, "test_lookup1", test_lookup1)) ||
    (NULL == CU_add_test(test_lookup, "test_lookup2", test_lookup2))
  )
    {
      CU_cleanup_registry();
      return CU_get_error();
    }
    
  

 test_remove = CU_add_suite("test_remove", init_suite, clean_suite);
  if (NULL == test_remove)
    {
      CU_cleanup_registry();
      return CU_get_error();
    }

  if (
    (NULL == CU_add_test(test_remove, "test_remove", test_remove1)) ||
    (NULL == CU_add_test(test_remove, "test_remove", test_remove2))
  )
    {
      CU_cleanup_registry();
      return CU_get_error();
    }
    
  CU_basic_set_mode(CU_BRM_VERBOSE);
  CU_basic_run_tests();
  CU_cleanup_registry();
  return CU_get_error();
}

