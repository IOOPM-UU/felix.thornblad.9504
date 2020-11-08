#include "hash_table.h"
#include <stdio.h>
#include <stdlib.h>

#define LOAD_FACTOR 0.75

typedef struct entry entry_t;

struct entry {
    elem_t key;       
    elem_t value;   
    entry_t *next; 
};

struct hash_table {
    size_t size;
    ioopm_hash_function hash_function;
    ioopm_eq_function eq;
    bool prev_bucket_size;
    size_t no_buckets;
    entry_t *buckets;
};

static entry_t *entry_create(elem_t key, elem_t value, entry_t *next) {
    entry_t *entry = calloc(1, sizeof(entry_t));
    entry->key = key;
    entry->value = value;
    entry->next = next;
    return entry;
}

static void entry_destroy(entry_t *entry) {
    free(entry);
    entry = NULL;
}

static entry_t *find_previous_entry_for_key(entry_t *entry, elem_t key, ioopm_eq_function eq) {
    
    entry_t *prev = entry; 
    entry_t *next = entry->next;
    
    if (next == NULL) {
        return prev;
    } 

    while (next != NULL) {
        if (eq(next->key,key)) { 
            return prev;
        }else {
            prev = next;
            next = next->next; 
        }
    }      
    return prev;        
}

size_t number_of_buckets[] = {17, 31, 67, 127, 257, 509, 1021, 2053, 4099, 8191, 16381};

static int map_key(ioopm_hash_table_t *ht, elem_t key) {
 
    int int_key = ht->hash_function(key);
    int buckets = number_of_buckets[ht->no_buckets];

    if (ht->prev_bucket_size) {
        buckets = number_of_buckets[ht->no_buckets-1];
    }

    int bucket = int_key % buckets;
    // key is negativ adjust mod value
    if (int_key < 0 && bucket != 0) {
        bucket = bucket + buckets;
    }
    return bucket;       
}

static void rehash_hash_table(ioopm_hash_table_t *ht) {
    for (int i = 0; i < number_of_buckets[ht->no_buckets-1]; i++) {
        entry_t *entry = &ht->buckets[i];
        entry_t *next = entry->next;
        entry_t *tmp;

        while (next != NULL) {
            int mapped_key = map_key(ht, next->key);
            tmp = next->next;
            if (i != mapped_key) {
                elem_t key = next->key; 
                ht->prev_bucket_size = true;
                elem_t value = ioopm_hash_table_remove(ht, key);
                ht->prev_bucket_size = false;
                ioopm_hash_table_insert(ht, key, value);
            }
            next = tmp;
        }
    }
}

static void ioopm_hash_table_create_dynamic(ioopm_hash_table_t *ht) {

    ht->no_buckets += 1; 
    ht->buckets = realloc(ht->buckets,number_of_buckets[ht->no_buckets] * sizeof(entry_t));

    for (int i = number_of_buckets[ht->no_buckets-1]; i < number_of_buckets[ht->no_buckets]; i++) {
        ht->buckets[i].key = pointer_elem(NULL);
        ht->buckets[i].value = pointer_elem(NULL);
        ht->buckets[i].next = NULL;
    }

    rehash_hash_table(ht);
}

ioopm_hash_table_t *ioopm_hash_table_create(ioopm_eq_function eq, ioopm_hash_function hf) {
    ioopm_hash_table_t *result = calloc(1, sizeof(ioopm_hash_table_t));
    result->eq = eq;
    result->hash_function = hf;
    result->no_buckets = 0; 
    result->buckets = calloc(1, number_of_buckets[result->no_buckets] * sizeof(entry_t));
    return result;
}

void ioopm_hash_table_destroy(ioopm_hash_table_t *ht) {
    ioopm_hash_table_clear(ht); 
    free(ht->buckets);
    ht->buckets = NULL;
    free(ht);
    ht = NULL;
} 

void ioopm_hash_table_clear(ioopm_hash_table_t *ht) {
    for (int i = 0; i < number_of_buckets[ht->no_buckets]; i++) {
        entry_t *entry = &ht->buckets[i]; 

        if (entry->next != NULL) {
            entry_t *next = entry->next;
            entry_t *tmp;

            while (next->next != NULL) {  
                tmp = next->next;
                entry_destroy(next);
                next = tmp; 
            }
            
            entry_destroy(next);
        }
        entry->next = NULL;
    }
}

void ioopm_hash_table_insert(ioopm_hash_table_t *ht, elem_t key, elem_t value) {

    int bucket = map_key(ht,key);

    /// Search for an existing entry for a key
    entry_t *entry = find_previous_entry_for_key(&ht->buckets[bucket], key, ht->eq);
    entry_t *next = entry->next;
    /// Check if the next entry should be updated or not
    if (next != NULL && ht->eq(next->key, key)) {
        next->value = value;
    }else {
        entry->next = entry_create(key, value, next);
        ht->size++;
    }

    if (ht->size > LOAD_FACTOR * number_of_buckets[ht->no_buckets] && ht->no_buckets < 10) {
        ioopm_hash_table_create_dynamic(ht);
    }
}

option_t ioopm_hash_table_lookup(ioopm_hash_table_t *ht, elem_t key) {

    int bucket = map_key(ht,key);

    entry_t *entry = find_previous_entry_for_key(&ht->buckets[bucket], key, ht->eq);
    entry_t *next = entry->next;

    if (next && ht->eq(next->key,key)){
        /// If entry was found, return its value...
        return Success(next->value);
    }else {
        return Failure(); 
    }       
}

elem_t ioopm_hash_table_remove(ioopm_hash_table_t *ht, elem_t key) {  

    int bucket = map_key(ht,key);

    entry_t *entry = find_previous_entry_for_key(&ht->buckets[bucket], key, ht->eq);
    entry_t *next = entry->next;

    if (next && ht->eq(next->key,key)) {

        entry_t *nextNext = next->next;
        elem_t removed_value = next->value;
        //update what it points to
        entry->next = nextNext; 
        //remove value from memory
        entry_destroy(next);
        //update size
        ht->size--;
        //return the removed value
        return removed_value;
    }
    return bool_elem(false);
}

size_t ioopm_hash_table_size(ioopm_hash_table_t *ht) {
    return ht->size;
}


bool ioopm_hash_table_is_empty(ioopm_hash_table_t *ht) {
    return ioopm_hash_table_size(ht) == 0;
}


ioopm_list_t *ioopm_hash_table_keys(ioopm_hash_table_t *ht) {

    ioopm_list_t *keys = ioopm_linked_list_create(NULL);
    size_t size = ioopm_hash_table_size(ht);
    
    if (size == 0) {
        return keys;
    }

    for (int i = 0 ;i < number_of_buckets[ht->no_buckets]; i++) {
        entry_t *entry = ht->buckets[i].next;

        while (entry != NULL) {
            ioopm_linked_list_append(keys, entry->key);
            entry = entry->next;
        }
    }
    
    return keys;  
}

ioopm_list_t *ioopm_hash_table_values(ioopm_hash_table_t *ht) {
    ioopm_list_t *values = ioopm_linked_list_create(NULL);
    size_t size = ioopm_hash_table_size(ht);

    if (size == 0) {
        return values;
    }

    for (int i = 0 ;i < number_of_buckets[ht->no_buckets]; i++) {
        entry_t *entry = ht->buckets[i].next;

        while (entry != NULL) {
            ioopm_linked_list_append(values, entry->value);
            entry = entry->next;
        }
    }
    return values;  
}


static bool key_equiv(elem_t key, elem_t value_ignored, ioopm_eq_function eq, void *x) {
    elem_t *other_key_ptr = x;
    elem_t other_key = *other_key_ptr;
    return eq(key,other_key);
}

bool ioopm_hash_table_has_key(ioopm_hash_table_t *ht, elem_t key) {
    return ioopm_hash_table_any(ht, key_equiv, &key);
}

bool ioopm_value_equiv(elem_t key_ignored, elem_t value, ioopm_eq_function eq, void *x) {
    elem_t *other_value_ptr = x;
    elem_t other_value = *other_value_ptr;
    return eq(value,other_value);
}

bool ioopm_hash_table_has_value(ioopm_hash_table_t *ht, elem_t value) {
    return ioopm_hash_table_any(ht, ioopm_value_equiv, &value);
}

bool ioopm_hash_table_all(ioopm_hash_table_t *ht, ioopm_predicate pred, void *arg) {

    bool result = true;
    entry_t *next;


    for (int i = 0; i < number_of_buckets[ht->no_buckets]; i++) {
        next = ht->buckets[i].next;
        while (next != NULL) {
            result = result && pred(next->key, next->value, ht->eq, arg);
            if (result == false) {
                return result;
            }
            next = next->next;
        }
    }
    return result; 
}

bool ioopm_hash_table_any(ioopm_hash_table_t *ht, ioopm_predicate pred, void *arg) {

    bool result = false;
    entry_t *next;


    for (int i = 0; i < number_of_buckets[ht->no_buckets]; i++) {
        next = ht->buckets[i].next;
        while (next != NULL) {
            result = pred(next->key, next->value, ht->eq, arg);
            if (result == true) {
                return result;
            }
            next = next->next;
        }
    }
    return result; 
}

void ioopm_hash_table_apply_to_all(ioopm_hash_table_t *ht, ioopm_apply_function apply_fun, void *arg) {
    for (int i = 0; i < number_of_buckets[ht->no_buckets]; i++) {
        entry_t *entry = ht->buckets[i].next; 

        while (entry != NULL) {
            elem_t key = entry->key;
            apply_fun(key, &entry->value, arg);
            entry = entry->next;
        }
    }
}

option_t ioopm_hash_table_entries_in_bucket(ioopm_hash_table_t *ht, unsigned int index) {

    int count = 0;

    if (index < 0 || index > number_of_buckets[ht->no_buckets]-1) {
        return Failure();
    }

    entry_t *entry = &ht->buckets[index];
    entry_t *next = entry->next;

    while (next != NULL) {  
        count++;
        next = next->next;
    }
    return Success(int_elem(count));
}
