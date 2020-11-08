#pragma once
#include "list_linked.h"
#include "utils.h"
#include "iterator.h"
#include "hash_table.h"

typedef struct user ioopm_user_t; 


/// @brief frees every memory allocated
/// @param user_ht hash table operated upon
/// @param merch_ht hash table operated upon
void ioopm_free_all_resources(ioopm_hash_table_t *user_ht, ioopm_hash_table_t *merch_ht);

/// @brief compares two string elements 
/// @param k1 first element string
/// @param k2 second element string
bool ioopm_string_key_eq(elem_t k1, elem_t k2);

/// @brief hashes the element string to a number
/// @param elem the element string to be hashed
int ioopm_string_knr_hash(elem_t elem);

/// @brief compares two usernames
/// @param a first element user
/// @param b second element user
bool ioopm_compare_username_user(elem_t a, elem_t b);

/// @brief hashes an username
/// @param elem element user
int ioopm_user_username_knr_hash(elem_t elem);

/// @brief add merch to hash table merch_ht
/// @param merch_ht hash table operated upon
void ioopm_add_merch(ioopm_hash_table_t *merch_ht);

/// @brief list all merch in hash table merch_ht
/// @param merch_ht hash table operated upon
void ioopm_list_merch(ioopm_hash_table_t *merch_ht);

/// @brief remove merch from hash table merch_ht
/// @param merch_ht hash table table operated upon
/// @param user_ht hash table operated upon
void ioopm_remove_merch(ioopm_hash_table_t *merch_ht, ioopm_hash_table_t *user_ht);

/// @brief edit merch in hash table merch_ht
/// @param merch_ht hash table table operated upon
void ioopm_edit_merch(ioopm_hash_table_t *merch_ht);

/// @brief show stock for merches in hash table merch_ht
/// @param merch_ht hash table table operated upon
void ioopm_show_stock(ioopm_hash_table_t *merch_ht);

/// @brief replenish a merch in hash table merch_ht
/// @param merch_ht hash table table operated upon
void ioopm_replenish_merch(ioopm_hash_table_t *merch_ht);

/// @brief create a shopping cart in user_ht
/// @param user_ht hash table operated upon
/// @param current_user owner of cart to be created
/// @return the new current user
ioopm_user_t *ioopm_create_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *current_user);

/// @brief remove a shopping cart
/// @param user_ht hash table operated upon
/// @param user owner of cart to be removed
/// @param merch_ht hash table operated upon
void ioopm_remove_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht);

/// @brief add merch to a shopping cart
/// @param user_ht hash table operated upon
/// @param user owner of cart to add merch to
/// @param merch_ht hash table operated upon
void ioopm_add_to_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht);

/// @brief remove merch from a shopping cart
/// @param user_ht hash table operated upon
/// @param user owner of cart to remove merch from
/// @param merch_ht hash table operated upon
void ioopm_remove_from_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht);

/// @brief calculate the combined cost of all merch in a shopping cart
/// @param user_ht hash table operated upon
/// @param user owner of cart to calculate cost of
/// @param merch_ht hash table operated upon
void ioopm_calculate_cost(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht); 

/// @brief removes a cart and the merch inside it
/// @param user_ht hash table operated upon
/// @param user owner of cart to be checkouted
/// @param merch_ht hash table operated upon
void ioopm_checkout(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht);

/// @brief NOT YET IMPLEMENTED! 
void ioopm_undo();