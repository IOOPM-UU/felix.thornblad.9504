#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

#include "db.h"

typedef struct merch merch_t;
typedef struct location location_t; 
#define Print_Amount 20
#define Dummy_User NULL

struct merch
{
  char *name;
  char *desc;
  unsigned int price;
  ioopm_list_t *locations;
  unsigned int total_quantity;
  unsigned int reserved_quantity;
};


struct location
{
  char *shelf;
  unsigned int quantity;
};

struct user
{
  char *username;
  unsigned int shopping_cart_index;
};


static merch_t *create_merch(char *name, char* desc, unsigned int price, ioopm_list_t *list)
{
  merch_t *merch = calloc(1, sizeof(merch_t));
  merch->name = name;
  merch->desc = desc;
  merch->price = price;
  merch->locations = list;
  return merch;
}


static location_t *create_location(char *shelf, unsigned int quantity)
{
  location_t *location = calloc(1, sizeof(location_t));
  location->shelf = shelf;
  location->quantity = quantity;
  return location;
}

static ioopm_user_t *create_user_struct(char *username, unsigned int shopping_cart_index)
{
  ioopm_user_t *user = calloc(1,sizeof(ioopm_user_t));
  user->username = username;
  user->shopping_cart_index = shopping_cart_index;
  return user;
}

static void destroy_merch(merch_t *merch)
{
  ioopm_linked_list_destroy(merch->locations);
  free(merch->name);
  free(merch->desc);
  free(merch);
  merch = NULL;
}

static void destroy_location(location_t *location)
{
  free(location->shelf);
  free(location);
  location = NULL;
}


static void destroy_user(ioopm_user_t *user)
{
  free(user->username);
  free(user);
  user = NULL;
}

static void iterate_over_destroy_location(ioopm_list_t *locations)
{
  ioopm_list_iterator_t *iter_locations = ioopm_list_iterator(locations);
  option_t location_op = ioopm_iterator_current(iter_locations);
  while (Successful(location_op))
  {
    destroy_location(location_op.value.p);
    location_op = ioopm_iterator_next(iter_locations);
  }
  ioopm_iterator_destroy(iter_locations);
}


static void iterate_over_destroy_merch(ioopm_hash_table_t *merch_ht)
{
  ioopm_list_t *list = ioopm_hash_table_values(merch_ht);
  ioopm_list_iterator_t *iter_merch = ioopm_list_iterator(list);
  option_t merch_op = ioopm_iterator_current(iter_merch);
  while (Successful(merch_op))
  {
    merch_t *merch = merch_op.value.p;
    iterate_over_destroy_location(merch->locations);   
    destroy_merch(merch);
    merch_op = ioopm_iterator_next(iter_merch);
  }
  ioopm_iterator_destroy(iter_merch);
  ioopm_linked_list_destroy(list);
}

static void iterate_over_destroy_shopping_cart(ioopm_hash_table_t *shopping_cart_ht)
{
  ioopm_list_t *list = ioopm_hash_table_values(shopping_cart_ht);
  ioopm_list_iterator_t *iter_cart_items = ioopm_list_iterator(list);
  option_t cart_items_ht_op = ioopm_iterator_current(iter_cart_items);
  while (Successful(cart_items_ht_op))
  {
    ioopm_hash_table_t *cart_items_ht = cart_items_ht_op.value.p;
    ioopm_hash_table_destroy(cart_items_ht);
    cart_items_ht_op = ioopm_iterator_next(iter_cart_items);
  }
  ioopm_iterator_destroy(iter_cart_items);
  ioopm_linked_list_destroy(list);
  ioopm_hash_table_destroy(shopping_cart_ht);
}


static void iterate_over_destroy_user(ioopm_hash_table_t *user_ht)
{
  ioopm_list_t *list = ioopm_hash_table_keys(user_ht);
  ioopm_list_iterator_t *iter_user = ioopm_list_iterator(list);
  option_t user_op = ioopm_iterator_current(iter_user);
  while (Successful(user_op))
  {
    ioopm_user_t *user = user_op.value.p;
    option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
    iterate_over_destroy_shopping_cart(shopping_cart_op.value.p);
    destroy_user(user);
    user_op = ioopm_iterator_next(iter_user);
  }
  ioopm_iterator_destroy(iter_user);
  ioopm_linked_list_destroy(list);
}

void ioopm_free_all_resources(ioopm_hash_table_t *user_ht, ioopm_hash_table_t *merch_ht)
{
  iterate_over_destroy_merch(merch_ht);
  ioopm_hash_table_destroy(merch_ht);
  iterate_over_destroy_user(user_ht);
  ioopm_hash_table_destroy(user_ht);
}

static int cmpstringp(const void *p1, const void *p2)
{
  return strcmp(* (char * const *) p1, * (char * const *) p2);
}


static void sort_keys(char *keys[], size_t no_keys) 
{
  qsort(keys, no_keys, sizeof(char *), cmpstringp);
}


static void merch_ht_to_char(ioopm_hash_table_t *merch_ht, char *keys[])
{
  ioopm_list_t *keys_tmp = ioopm_hash_table_keys(merch_ht);
  int counter = 0;
  while(counter < ioopm_linked_list_size(keys_tmp))
	{
	  keys[counter] = (char *)ioopm_linked_list_get(keys_tmp, counter).value.p;
		counter++;
	}
	ioopm_linked_list_destroy(keys_tmp);
}

static void cart_ht_to_int(ioopm_hash_table_t *cart_ht, int keys[])
{
  ioopm_list_t *keys_tmp = ioopm_hash_table_keys(cart_ht);
  int counter = 0;
  while(counter < ioopm_linked_list_size(keys_tmp))
	{
	  keys[counter] = ioopm_linked_list_get(keys_tmp, counter).value.i;
		counter++;
	}
	ioopm_linked_list_destroy(keys_tmp);
}


bool ioopm_string_key_eq(elem_t k1, elem_t k2)
{
  return strcmp(k1.p, k2.p) == 0; // if elem_t has field char *string
}


int ioopm_string_knr_hash(elem_t elem)
{
  char *str = elem.p;
  int result = 0;
  do
    {
      result = result * 31 + *str;
    }
  while (*++str != '\0');
  return result;
}


bool ioopm_compare_username_user(elem_t a, elem_t b)
{
  ioopm_user_t *user1 = a.p;
  ioopm_user_t *user2 = b.p;
  bool result = ioopm_string_key_eq(pointer_elem(user1->username), pointer_elem(user2->username));
  return result;
}


int ioopm_user_username_knr_hash(elem_t elem)
{
 ioopm_user_t *user = elem.p;
  int result = ioopm_string_knr_hash(pointer_elem(user->username));
  return result;
}

static bool compare_elem_int(elem_t a, elem_t b) 
{
    return a.i == b.i;
}


static int int_hash(elem_t key) 
{
    return key.i;
}


static bool valid_choice(char *str)
{
  char first_char = toupper(( unsigned char )str[0]);
  bool check = true;
  int length = strlen(str);
  for (int i=0; i<length; i++)
  {
    if (!(first_char == 'Y' || first_char == 'N'))
    {
      check = false;
    }
  }
  return check;
}  


static bool ask_question_continue()
{
  char *choice = ask_question("Continue? [Y]es/[N]o"
                              , valid_choice, (convert_func) strdup).string_value;
                                
  choice[0] = toupper(( unsigned char )choice[0]);
  bool result = choice[0]=='Y';
  free(choice);
  return result;
}


void ioopm_add_merch(ioopm_hash_table_t *merch_ht)
{
  char *name;
  bool equal;
  do
  {
    name = ask_question_string("Name of item?");
    equal = ioopm_hash_table_has_key(merch_ht, pointer_elem(name));
    if (equal)
      free(name);
  }
  while (equal);
  
  char *desc = ask_question_string("Describe the item");
  int price = ask_question_int("Price of item?");
  ioopm_list_t *list = ioopm_linked_list_create(NULL);
  merch_t *info = create_merch(name, desc, price, list);
  ioopm_hash_table_insert(merch_ht, pointer_elem(name), pointer_elem(info));
}

//Förbättring gör att list_merch retunerar sorterade listan
void ioopm_list_merch(ioopm_hash_table_t *merch_ht)
{
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);
  
  if(size == 0)
  {
    puts("Add an item first!");
    return;
  }
  
  bool see_more = true;
  int counter = 0;
  while(see_more)
  {
    for(int i = counter; i<(counter+Print_Amount) && i<size; i++)
    {
      printf("%d. %s\n",i+1,keys[i]);
    }
    counter = counter+Print_Amount;
    if(counter>=size)
      break;
    see_more = ask_question_continue();
  }
  printf("\n");
}


static option_t valid_list_choice(ioopm_hash_table_t *merch_ht, int size, char *question)
{
  ioopm_list_merch(merch_ht);

  if(size == 0)
  {
    return Failure();
  }
  
  int choice;
  do
  {
    choice = ask_question_int(question);
  }
  while (choice <= 0 || choice > size);

  return Success(int_elem(choice));
}


void ioopm_remove_merch(ioopm_hash_table_t *merch_ht, ioopm_hash_table_t *user_ht)
{
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);

  char *question = "Which merch do you want to remove?"; 
  option_t choice = valid_list_choice(merch_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    return;
  }
  
  int index = choice.value.i;

  elem_t merch = pointer_elem(keys[index-1]);
  
  ioopm_list_t *list_users = ioopm_hash_table_keys(user_ht);
  ioopm_list_iterator_t *iter_users = ioopm_list_iterator(list_users);
  option_t user_op = ioopm_iterator_current(iter_users);
  while(Successful(user_op))
  {
    option_t cart_ht_op = ioopm_hash_table_lookup(user_ht, user_op.value);
    
    ioopm_list_t *list_cart_ht = ioopm_hash_table_values(cart_ht_op.value.p);
    ioopm_list_iterator_t *iter_cart_ht = ioopm_list_iterator(list_cart_ht);
    cart_ht_op = ioopm_iterator_current(iter_cart_ht);
    while(Successful(cart_ht_op))
    {
      if(Successful(ioopm_hash_table_lookup(cart_ht_op.value.p, merch)))
      {
        printf("This merch is in a cart!");
        ioopm_iterator_destroy(iter_cart_ht);
        ioopm_linked_list_destroy(list_cart_ht);
        ioopm_iterator_destroy(iter_users);
        ioopm_linked_list_destroy(list_users);
        return;
      }
      cart_ht_op = ioopm_iterator_next(iter_cart_ht);
    }
    ioopm_iterator_destroy(iter_cart_ht);
    ioopm_linked_list_destroy(list_cart_ht);
    
    user_op = ioopm_iterator_next(iter_users);
  }
  ioopm_iterator_destroy(iter_users);
  ioopm_linked_list_destroy(list_users);
  
  option_t merch_op = ioopm_hash_table_lookup(merch_ht, merch);
  ioopm_hash_table_remove(merch_ht, merch);
  merch_t *merch_struct = merch_op.value.p;
  iterate_over_destroy_location(merch_struct->locations);
  destroy_merch(merch_struct);
}


void ioopm_edit_merch(ioopm_hash_table_t *merch_ht)
{
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);

  char *question = "Which merch do you want to edit?"; 
  option_t choice = valid_list_choice(merch_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    return;
  }
  
  int index = choice.value.i;

    
  option_t merch_op = ioopm_hash_table_lookup(merch_ht,pointer_elem(keys[index-1]));
  merch_t *merch = merch_op.value.p;
 
  char name_question[255];
  snprintf(name_question, 255, "Old name was %s, new name?", merch->name);
  
  bool equal;
  char *name;
  do
  {
    name = ask_question_string(name_question);
    equal = (ioopm_hash_table_has_key(merch_ht, pointer_elem(name)) && strcmp(name, keys[index-1]));
    if (equal)
      free(name);
  }
  while (equal);
  
  char desc_question[255];
  snprintf(desc_question, 255, "Old description was %s, new description?", merch->desc);
  char *desc = ask_question_string(desc_question);
  char int_question[255];
  snprintf(int_question, 255, "Old price was %d, new price?", merch->price);
  int price = ask_question_int(int_question);
  ioopm_list_t *old_list = merch->locations;
  
  merch_t *new_merch = create_merch(name, desc, price, old_list);
  ioopm_hash_table_remove(merch_ht, pointer_elem(keys[index-1]));
  ioopm_hash_table_insert(merch_ht, pointer_elem(name), pointer_elem(new_merch));
  free(merch->name);
  free(merch->desc);
  free(merch);
  merch = NULL;
}

static bool valid_shelf(char *str){

    int length = strlen(str);
    
    if(length != 3){
        return false;
    }
    
    if(str[0] < 'A' || str[0] > 'Z'){
        return false;
    }
    
    for(int i = 1; i < length; i++){
        if(!isdigit(str[i])){
            return false;
        }
    }
    
    return true;
}


void ioopm_show_stock(ioopm_hash_table_t *merch_ht)
{
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);

  char *question = "Which merch do you want to show stock for?"; 
  option_t choice = valid_list_choice(merch_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    printf("This merch has no stock!\n");
    return;
  }
  
  int index = choice.value.i;

  option_t merch_op = ioopm_hash_table_lookup(merch_ht,pointer_elem(keys[index-1]));
  merch_t *merch = merch_op.value.p;
  ioopm_list_t *locations_list = merch->locations;
  ioopm_list_iterator_t *iter = ioopm_list_iterator(locations_list);
  option_t location_op = ioopm_iterator_current(iter);

  while (Successful(location_op))
  {
    location_t *location = location_op.value.p;
    printf("Shelf: %s, quantity: %d\n",location->shelf,location->quantity);
    location_op = ioopm_iterator_next(iter);
  }
  ioopm_iterator_destroy(iter);
}

static char *ask_question_shelf()
{
  char *choice = ask_question("Which shelf?", valid_shelf, (convert_func) strdup).string_value;
  return choice;
}

void ioopm_replenish_merch(ioopm_hash_table_t *merch_ht)
{
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);

  char *question = "Which merch do you want to replenish?"; 
  option_t choice = valid_list_choice(merch_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    return;
  }
  
  int index = choice.value.i;

  char *shelf = ask_question_shelf();
  int add_amount = ask_question_int("How much merch do you want to add to stock?");
  
  option_t merch_op = ioopm_hash_table_lookup(merch_ht,pointer_elem(keys[index-1]));
  merch_t *merch = merch_op.value.p;
  ioopm_list_t *locations = merch->locations;
  
  ioopm_list_iterator_t *iter = ioopm_list_iterator(locations);
  option_t shelf_iter = ioopm_iterator_current(iter);
  bool new_shelf = true;
  
  while (Successful(shelf_iter))
  {
    location_t *location = shelf_iter.value.p;
    if(strcmp(location->shelf, shelf) == 0)
    {
      location->quantity += add_amount;
      merch->total_quantity += add_amount;
      new_shelf = false;
      free(shelf);
      break;
    }
    shelf_iter = ioopm_iterator_next(iter);
  }
  ioopm_iterator_destroy(iter);
  
  if(new_shelf)
  {
    location_t *new_location = create_location(shelf, add_amount);
    merch->total_quantity += add_amount;
    ioopm_linked_list_append(locations, pointer_elem(new_location));
  }
}

static bool valid_username(char *str)
{
  int length = strlen(str);
  for (int i = 0; i < length; i++) 
  {
    if((str[i] < 'A' || str[i] > 'Z') && (str[i] < 'a' || str[i] > 'z'))
    {
        return false;
    }
  }
  
  return true;
}

static char *ask_question_username()
{
  char *choice = ask_question("What do you want to be called?",
                              valid_username, (convert_func) strdup).string_value;
  return choice;
}

static ioopm_user_t *create_user(ioopm_hash_table_t *user_ht)
{
  bool equal;
  option_t found_user;
  do
  {
    char *username = ask_question_username();
    ioopm_user_t *user = create_user_struct(username, 1);
    found_user = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
    if(Unsuccessful(found_user))
    {
      return user;
    }
    free(user);
    equal = (Successful(found_user));
    if (equal)
      free(username);
  }
  while(equal);
  return Dummy_User;
}

option_t check_existing_user(ioopm_hash_table_t *user_ht, ioopm_user_t *current_user)
{
  ioopm_user_t *new_user;
  if(current_user == Dummy_User)
  {
    new_user = create_user(user_ht);
    return Success(pointer_elem(new_user));
  }
  
  return Failure();
}


ioopm_user_t *ioopm_create_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *current_user)
{ 
  option_t user_op = check_existing_user(user_ht, current_user);
  if (Successful(user_op))
  {
    ioopm_user_t *user = user_op.value.p;
    ioopm_hash_table_t *shopping_cart_ht = ioopm_hash_table_create(compare_elem_int, int_hash);
    ioopm_hash_table_t *shopping_cart_items_ht = ioopm_hash_table_create(ioopm_string_key_eq, ioopm_string_knr_hash);
    ioopm_hash_table_insert(shopping_cart_ht, int_elem(user->shopping_cart_index), pointer_elem(shopping_cart_items_ht));
    ioopm_hash_table_insert(user_ht, pointer_elem(user), pointer_elem(shopping_cart_ht));
    printf("Created a shopping cart!\n");
    return user;
  }
  else 
  {
    current_user->shopping_cart_index = current_user->shopping_cart_index + 1;
    option_t user_op = ioopm_hash_table_lookup(user_ht, pointer_elem(current_user));
    ioopm_hash_table_t *user_shopping_cart = user_op.value.p;
    ioopm_hash_table_t *shopping_cart_items_ht = ioopm_hash_table_create(ioopm_string_key_eq, ioopm_string_knr_hash);
    ioopm_hash_table_insert(user_shopping_cart, int_elem(current_user->shopping_cart_index), pointer_elem(shopping_cart_items_ht));
    printf("Created shopping cart nr %d\n", current_user->shopping_cart_index);
    return current_user;
  }
  
}


static option_t choose_shopping_cart(ioopm_hash_table_t *shopping_cart_ht, char *question)
{
  int size = ioopm_hash_table_size(shopping_cart_ht);
  int keys[size]; 
  cart_ht_to_int(shopping_cart_ht,keys);
 
  if(size == 0)
  {
    puts("Create a cart first!");
    return Failure();
  }

  for (int i = 0; i < size; i++) 
  {
    printf("Cart: %d\n", keys[i]);
  }

  int index;
  do
  {
    index = ask_question_int(question);
  }
  while (!ioopm_hash_table_has_key(shopping_cart_ht, int_elem(index)));
  
  return Success(int_elem(index)); 
}


static void iterate_remove_shopping_cart(ioopm_hash_table_t *shopping_cart_ht, ioopm_hash_table_t *merch_ht, int remove_index)
{
  option_t shopping_cart_items_op = ioopm_hash_table_lookup(shopping_cart_ht,int_elem(remove_index));
  ioopm_hash_table_t *shopping_cart_items_ht = shopping_cart_items_op.value.p;
  
  ioopm_list_t *list_keys = ioopm_hash_table_keys(shopping_cart_items_ht);
  ioopm_list_t *list_values = ioopm_hash_table_values(shopping_cart_items_ht);
  ioopm_list_iterator_t *iter_keys = ioopm_list_iterator(list_keys);
  ioopm_list_iterator_t *iter_values = ioopm_list_iterator(list_values);
  
  option_t keys_op = ioopm_iterator_current(iter_keys);
  option_t values_op = ioopm_iterator_current(iter_values);
  while (Successful(keys_op) && Successful(values_op))
  {
    option_t merch_op = ioopm_hash_table_lookup(merch_ht, pointer_elem(keys_op.value.p));
    merch_t *merch = merch_op.value.p;
    merch->reserved_quantity -= values_op.value.i;
    keys_op = ioopm_iterator_next(iter_keys);
    values_op = ioopm_iterator_next(iter_values);
  } 
  
  ioopm_iterator_destroy(iter_keys);
  ioopm_iterator_destroy(iter_values);
  ioopm_linked_list_destroy(list_keys);
  ioopm_linked_list_destroy(list_values);
  ioopm_hash_table_destroy(shopping_cart_items_ht);
  ioopm_hash_table_remove(shopping_cart_ht, int_elem(remove_index));
}

void ioopm_remove_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht) 
{
  if (user == Dummy_User)
  {
    printf("Create a cart first!");
    return;
  }
  
  option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
  ioopm_hash_table_t *shopping_cart_ht = shopping_cart_op.value.p;
  char *question = "Which shopping cart do you want to remove?";
  option_t remove_index_op = choose_shopping_cart(shopping_cart_ht,question); 
  if(Unsuccessful(remove_index_op))
  {
    return; 
  }
  int remove_index = remove_index_op.value.i;
  
 
  iterate_remove_shopping_cart(shopping_cart_ht, merch_ht, remove_index);
}


void ioopm_add_to_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht)
{
  if (user == Dummy_User)
  {
    printf("Create a cart first!");
    return;
  }
  
  option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
  ioopm_hash_table_t *shopping_cart_ht = shopping_cart_op.value.p;
  
  char *question = "To which shopping cart do you want to add merch to?";
  option_t cart_index_op = choose_shopping_cart(shopping_cart_ht, question);
  if(Unsuccessful(cart_index_op))
  {
    return;
  }
  int size = ioopm_hash_table_size(merch_ht);
  char *keys[size]; 
  merch_ht_to_char(merch_ht, keys);
  sort_keys(keys, size);
  
  question = "Which merch do you want add?"; 
  option_t choice = valid_list_choice(merch_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    return;
  }
  
  int index = choice.value.i;
  
  option_t merch_op = ioopm_hash_table_lookup(merch_ht, pointer_elem(keys[index-1]));
  merch_t *merch = merch_op.value.p;
  
  int limit =  merch->total_quantity - merch->reserved_quantity;
  
  if (limit == 0)
  {
    puts("This merch is out of stock!");
    return; 
  }
  
  char int_question[255];
  snprintf(int_question, 255, "How many? (%d is the limit)", limit);
  int quantity;
  do
  {
    quantity = ask_question_int(int_question);
  }
  while (quantity > limit);
  merch->reserved_quantity += quantity;
 
  int cart_index = cart_index_op.value.i;
  
  option_t cart_op = ioopm_hash_table_lookup(shopping_cart_ht,int_elem(cart_index));
  ioopm_hash_table_t *shopping_cart_items_ht = cart_op.value.p;
  option_t found_merch = ioopm_hash_table_lookup(shopping_cart_items_ht, pointer_elem(merch->name));
  
  if(Successful(found_merch))
  {
    ioopm_hash_table_insert(shopping_cart_items_ht,pointer_elem(merch->name),int_elem((quantity + found_merch.value.i)));
  }
  else
  {
    ioopm_hash_table_insert(shopping_cart_items_ht,pointer_elem(merch->name), int_elem(quantity));
  }
  
}


void ioopm_remove_from_shopping_cart(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht)
{
  if (user == Dummy_User)
  {
    printf("Create a cart first!");
    return;
  }

  option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
  ioopm_hash_table_t *shopping_cart_ht = shopping_cart_op.value.p;
  
  char *question = "In which shopping cart do you want to remove merch?";
  option_t cart_index_op = choose_shopping_cart(shopping_cart_ht, question);
  if(Unsuccessful(cart_index_op))
  {
    return;
  }
  
  option_t cart_items_op = ioopm_hash_table_lookup(shopping_cart_ht,cart_index_op.value);
  ioopm_hash_table_t *shopping_cart_items_ht = cart_items_op.value.p;
  
  int size = ioopm_hash_table_size(shopping_cart_items_ht);
  char *keys[size]; 
  merch_ht_to_char(shopping_cart_items_ht, keys);
  sort_keys(keys, size);
  
  question = "Which merch do you want to remove?";
  option_t choice = valid_list_choice(shopping_cart_items_ht, size, question); 
  if (Unsuccessful(choice)) 
  {
    return;
  }
  
  int index = choice.value.i;
  
  elem_t merch_elem = pointer_elem(keys[index-1]);
  
  option_t merch_cart_op = ioopm_hash_table_lookup(shopping_cart_items_ht, merch_elem);
  option_t merch_op = ioopm_hash_table_lookup(merch_ht, merch_elem);
  merch_t *merch = merch_op.value.p;
  
  char int_question[255];
  snprintf(int_question, 255, "How many do you want to remove (out of %d)?", merch_cart_op.value.i);
  int to_remove;
  do
  {
    to_remove = ask_question_int(int_question);
  }
  while(to_remove > merch_cart_op.value.i || to_remove < 0);
  
  merch->reserved_quantity -= to_remove;
  ioopm_hash_table_remove(shopping_cart_items_ht, merch_elem);
}


void ioopm_calculate_cost(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht)
{
  if (user == Dummy_User)
  {
    printf("Create a cart first!");
    return;
  }

  option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
  ioopm_hash_table_t *shopping_cart_ht = shopping_cart_op.value.p;
  
  char *question = "Which shopping cart do you want to know the cost of?";
  option_t cart_index_op = choose_shopping_cart(shopping_cart_ht, question);
  if(Unsuccessful(cart_index_op))
  {
    return;
  }
  int cart_index = cart_index_op.value.i;
  
  option_t cart_items_op = ioopm_hash_table_lookup(shopping_cart_ht, int_elem(cart_index));
  ioopm_hash_table_t *shopping_cart_items_ht = cart_items_op.value.p;
  
  ioopm_list_t *list_keys = ioopm_hash_table_keys(shopping_cart_items_ht);
  ioopm_list_t *list_values = ioopm_hash_table_values(shopping_cart_items_ht);
  ioopm_list_iterator_t *iter_keys = ioopm_list_iterator(list_keys);
  ioopm_list_iterator_t *iter_values = ioopm_list_iterator(list_values);
  
  int cost = 0;
  option_t keys_op = ioopm_iterator_current(iter_keys);
  option_t values_op = ioopm_iterator_current(iter_values);
  while (Successful(keys_op) && Successful(values_op))
  {
    option_t merch_op = ioopm_hash_table_lookup(merch_ht, pointer_elem(keys_op.value.p));
    merch_t *merch = merch_op.value.p;
    int price = merch->price;
    cost += price*values_op.value.i;
    keys_op = ioopm_iterator_next(iter_keys);
    values_op = ioopm_iterator_next(iter_values);
  } 
  
  ioopm_iterator_destroy(iter_keys);
  ioopm_iterator_destroy(iter_values);
  ioopm_linked_list_destroy(list_keys);
  ioopm_linked_list_destroy(list_values);
  
  printf("The total cost of cart %d, is %d\n",cart_index, cost);
}


void ioopm_checkout(ioopm_hash_table_t *user_ht, ioopm_user_t *user, ioopm_hash_table_t *merch_ht)
{
  if (user == Dummy_User)
  {
    printf("Create a cart first!");
    return;
  }

  option_t shopping_cart_op = ioopm_hash_table_lookup(user_ht, pointer_elem(user));
  ioopm_hash_table_t *shopping_cart_ht = shopping_cart_op.value.p;
  
  char *question = "Which shopping cart do you want to checkout?";
  option_t cart_index_op = choose_shopping_cart(shopping_cart_ht, question);
  if(Unsuccessful(cart_index_op))
  {
    return;
  }
  int cart_index = cart_index_op.value.i;
  
  option_t cart_items_op = ioopm_hash_table_lookup(shopping_cart_ht, int_elem(cart_index));
  ioopm_hash_table_t *shopping_cart_items_ht = cart_items_op.value.p;
  
  ioopm_list_t *list_keys = ioopm_hash_table_keys(shopping_cart_items_ht);
  ioopm_list_t *list_values = ioopm_hash_table_values(shopping_cart_items_ht);
  ioopm_list_iterator_t *iter_keys = ioopm_list_iterator(list_keys);
  ioopm_list_iterator_t *iter_values = ioopm_list_iterator(list_values);
  
  option_t keys_op = ioopm_iterator_current(iter_keys);
  option_t values_op = ioopm_iterator_current(iter_values);
  while (Successful(keys_op) && Successful(values_op))
  {
    option_t merch_op = ioopm_hash_table_lookup(merch_ht, pointer_elem(keys_op.value.p));
    merch_t *merch = merch_op.value.p;
    int to_remove = values_op.value.i;
    merch->total_quantity -= to_remove;
    
    ioopm_list_t *list_locations = merch->locations;
    ioopm_list_iterator_t *iter_locations = ioopm_list_iterator(list_locations);
    option_t locations_op = ioopm_iterator_current(iter_locations);
    while (Successful(locations_op) && to_remove > 0)
    {
      location_t *location = locations_op.value.p;
      if(location->quantity > to_remove)
      {
       location->quantity -= to_remove;
       break;
      }
      else
      {
        to_remove -= location->quantity;
        location->quantity = 0;
      }
      locations_op = ioopm_iterator_next(iter_locations);
    }
    ioopm_iterator_destroy(iter_locations);
    keys_op = ioopm_iterator_next(iter_keys);
    values_op = ioopm_iterator_next(iter_values);
  } 
  ioopm_iterator_destroy(iter_keys);
  ioopm_iterator_destroy(iter_values);
  ioopm_linked_list_destroy(list_keys);
  ioopm_linked_list_destroy(list_values);
  
  iterate_remove_shopping_cart(shopping_cart_ht, merch_ht, cart_index);
}


void ioopm_undo()
{
  printf("NOT YET IMPLEMENTED!\n");
}


