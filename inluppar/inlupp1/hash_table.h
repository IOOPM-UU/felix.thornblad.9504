#pragma once

/**
 * @file hash_table.h
 * @author Anton Ohlsson, Felix Thörnblad
 * @date 14 Sep 2020
 * @brief Simple hash table that maps integer keys to string values.
 *
 * Here typically goes a more extensive explanation of what the header
 * defines. Doxygens tags are words preceeded by either a backslash @\
 * or by an at symbol @@.
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment1.html
 */
 
typedef struct hash_table ioopm_hash_table_t;


///TEST FUNCTION
///void print_bucket(ioopm_hash_table_t *ht, int bucket);

/// @brief Create a new hash table
/// @return A new empty hash table
ioopm_hash_table_t *ioopm_hash_table_create();

/// @brief Delete a hash table and free its memory
/// param ht a hash table to be deleted
void ioopm_hash_table_destroy(ioopm_hash_table_t *ht);

/// @brief add key => value entry in hash table ht
/// @param ht hash table operated upon
/// @param key key to insert
/// @param value value to insert
void ioopm_hash_table_insert(ioopm_hash_table_t *ht, int key, char *value);

/// @brief lookup value for key in hash table ht
/// @param ht hash table operated upon
/// @param key key to lookup
/// @returns pointer to the value mapped by key, if the key is not in the table: pointer == NULL
char **ioopm_hash_table_lookup(ioopm_hash_table_t *ht, int key);

/// @brief remove any mapping from key to a value
/// @param ht hash table operated upon
/// @param key key to remove
/// @return pointer to the value mapped to by key, if the key is not in the table: pointer == NULL (return pointer needs to be freed)
char **ioopm_hash_table_remove(ioopm_hash_table_t *ht, int key);