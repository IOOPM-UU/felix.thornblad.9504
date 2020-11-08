#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

#include "db.h"

#define Dummy_User NULL

static bool ioopm_valid_question(char *str)
{
  char first_char = toupper(( unsigned char )str[0]);
  bool check = true;
  int length = strlen(str);
  for (int i=0; i<length; i++)
  {
    if (!(first_char == 'A' || first_char == 'L' || first_char == 'R' || first_char == 'E' || 
          first_char == 'S' || first_char == 'P'  || first_char == 'C' || first_char == 'V' ||
          first_char == 'T' || first_char == 'F' || first_char == 'O' || first_char == 'H' ||
          first_char == 'U' || first_char == 'Q'))
    {
      check = false;
    }
  }
  return check;
}  

static char *ioopm_ask_question_menu()
{
  char *choice = ask_question("[A]dd merch\n"
                              "[L]ist merch\n"
                              "[R]emove a piece of merch\n"
                              "[E]dit a piece of merch\n"
                              "[S]how merch\n"
                              "Re[P]lenish merch\n"
                              "[C]reate cart\n"
                              "Remo[V]e cart\n"
                              "Add [T]o cart\n"
                              "Remove [F]rom cart\n"
                              "Calculate C[O]st\n"
                              "C[H]eckout\n"
                              "[U]ndo\n"
                              "[Q]uit\n"
                              , ioopm_valid_question, (convert_func) strdup).string_value;
                                
  choice[0] = toupper(( unsigned char )choice[0]);
  return choice;
}


static void event_loop(ioopm_hash_table_t *merch_ht, ioopm_hash_table_t *user_ht)
{
  ioopm_user_t *user = Dummy_User;
  while (true)
  {
    char *choice = ioopm_ask_question_menu();
    if (choice[0] == 'A')
      ioopm_add_merch(merch_ht);
    else if (choice[0] == 'L')
      ioopm_list_merch(merch_ht);
    else if (choice[0] == 'R')
      ioopm_remove_merch(merch_ht, user_ht);
    else if (choice[0] == 'E')
      ioopm_edit_merch(merch_ht);
    else if (choice[0] == 'S')
      ioopm_show_stock(merch_ht);
    else if (choice[0] == 'P')
      ioopm_replenish_merch(merch_ht);
    else if (choice[0] == 'C')
    {
      user = ioopm_create_shopping_cart(user_ht,user);
    }
    else if (choice[0] == 'V')
      ioopm_remove_shopping_cart(user_ht,user, merch_ht);
    else if (choice[0] == 'T')
      ioopm_add_to_shopping_cart(user_ht, user, merch_ht);
    else if (choice[0] == 'F')
      ioopm_remove_from_shopping_cart(user_ht, user, merch_ht);
    else if (choice[0] == 'O')
      ioopm_calculate_cost(user_ht, user, merch_ht);
    else if (choice[0] == 'H')
      ioopm_checkout(user_ht, user, merch_ht);
    else if (choice[0] == 'U')
      ioopm_undo();
    else if (choice[0] == 'Q')
    {
      free(choice);
      return;
    }
    // if N choose user or create a new one
    puts("\n");
    free(choice);
  }
}




int main(int argc, char *argv[])
{
  ioopm_hash_table_t *merch_ht = ioopm_hash_table_create(ioopm_string_key_eq, ioopm_string_knr_hash);
  ioopm_hash_table_t *user_ht = ioopm_hash_table_create(ioopm_compare_username_user, ioopm_user_username_knr_hash);
  event_loop(merch_ht, user_ht);
  ioopm_free_all_resources(user_ht,merch_ht);
  
  
  
  
  return 0;
}
