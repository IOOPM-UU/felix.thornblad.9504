#include "hash_table.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>

#define No_Buckets 17

typedef struct entry entry_t; //CONFUSED: here or in hash_table.h 

struct entry  //DODGE: only supports int/char
{
	int key;       // holds the key
	char *value;   // holds the value
	entry_t *next; // points to the next entry (possibly NULL)
};

struct hash_table //DODGE: constant 17 size
{
	entry_t buckets[No_Buckets];
};

ioopm_hash_table_t *ioopm_hash_table_create()  
{
	/// Allocate space for a ioopm_hash_table_t = 17 pointers to
	/// entry_t's, which will be set to NULL
	ioopm_hash_table_t *result = calloc(1, sizeof(ioopm_hash_table_t));
	return result;
}


static entry_t *find_previous_entry_for_key(entry_t *current, int key)
{
	/// Checks if there is a next key
	while(current->next != NULL)
	{
		/// Checks if the next key is larger than the key to be inserted
		if (current->next->key >= key)
		{
			/// Returns the entry with key smaller or equal to key to be inserted
			return current;
		}
		// Traverses the list
		current = current->next;
	}
	/// Returns the last entry
	return current;
}


static entry_t *entry_create(int key, char *value, entry_t *next)
{
	entry_t *new_entry = malloc(sizeof(entry_t));
	new_entry->key = key;
	new_entry->value = value;
	new_entry->next = next;
	return new_entry;
}

void ioopm_hash_table_insert(ioopm_hash_table_t *ht, int key, char *value) /// DODGE: only int key, with static 17 buckets
{
	/// Calculate the bucket for this entry
	int bucket = key % No_Buckets;
	/// Search for an existing entry for a key
	entry_t *entry = find_previous_entry_for_key(&ht->buckets[bucket], key);
	entry_t *next = entry->next;

	/// Check if the next entry should be updated or not
	if (next != NULL && next->key == key)
		{
			next->value = value;
		}
	else
		{
			entry->next = entry_create(key, value, next);
		}
}


char **ioopm_hash_table_lookup(ioopm_hash_table_t *ht, int key)  ///DODGE: Assume 17 buckets
{
	/// Find the previous entry for key
	entry_t *tmp = find_previous_entry_for_key(&ht->buckets[key % No_Buckets], key);
	entry_t *next = tmp->next;
	if (next != NULL && next->key == key)
	{
		return &next->value;
	}
	return NULL;
} 


static void entry_destroy(entry_t *next)
{
	free(next);
	next = NULL; ///Onödigt ?
}

char **ioopm_hash_table_remove(ioopm_hash_table_t *ht, int key) ///DODGE: Assume 17 buckets
{
	entry_t *prev = find_previous_entry_for_key(&ht->buckets[key % No_Buckets], key);
	entry_t *next = prev->next;
	if (next != NULL && next->key == key) 
	{
		char **result = malloc(sizeof(char *));
		*result = next->value;  ///FIXME: kan behöva kolla om value = NULL samt free ? ta bort strdup ?
		prev->next = next->next;
		entry_destroy(next);
		return result;
	}
	return NULL;
}

void ioopm_hash_table_destroy(ioopm_hash_table_t *ht)
{
	for (int i = 0; i < No_Buckets; ++i) ///DODGE: fixed number of buckets
	{
		entry_t *prev = &ht->buckets[i];
		entry_t *next = prev->next;
		//entry_destroy(prev);
		entry_t *tmp = next;
		
		while (tmp != NULL)
		{
		next = tmp;
		tmp = next->next;
	  entry_destroy(next); 
		}
	}
	free(ht);
}

/*
void print_bucket(ioopm_hash_table_t *ht, int bucket)
{
	entry_t *current = &ht->buckets[bucket];
	if (current != NULL)
	{
		do
		{
			printf("[%d, %s],", current->key ,current->value);
			current = current->next;
		} while(current != NULL);
		printf("\n");
		}
	else 
	{
		printf("THE BUCKET IS EMPTY!\n");
	}
}

int main(void)
{
  int key = 5;
	ioopm_hash_table_t *ht = ioopm_hash_table_create();
//	print_bucket(ht, 0);
	ioopm_hash_table_insert(ht, key, "42");
	ioopm_hash_table_insert(ht, key+17, "47");
	ioopm_hash_table_insert(ht, key+34, "50");
	//print_bucket(ht, 5);
	ioopm_hash_table_destroy(ht);
	
	//print_bucket(ht, 5);
	return 0;
}
*/