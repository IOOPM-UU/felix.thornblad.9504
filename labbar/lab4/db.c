#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>
struct item
{
  char *name;
  char *desc;
  int price;
  char *shelf;
};
typedef struct item item_t;


void print_item(item_t *i)
{
  printf("Name: %s \nDesc: %s \nPrice: %d.%02d SEK \nShelf: %s \n", i->name, i->desc, (i->price)/100, (i->price) % 100, i->shelf);
}

item_t make_item(char *name, char *desc, int price, char *shelf)
{
    item_t i = {.name = name, .desc = desc, .price = price, .shelf = shelf};
    return i;
}

bool shelf_format(char *str)
{
  int length = strlen(str);
  for (int i=1; i<length; i++)
  {
    if (isdigit(str[i]) == false)
    {
    return false;
    }
  }
  return true;
}


char *ask_question_shelf(char *question)
{
  return ask_question(question, shelf_format, (convert_func) strdup).string_value;
}

item_t input_item()
{
  char *name = ask_question_string("Name of item?");
  char *desc = ask_question_string("Describe the item");
  int price = ask_question_int("Price of item?");
  char *shelf = ask_question_shelf("On which shelf can the item be found?");
  item_t i = {.name = name, .desc = desc, .price = price, .shelf = shelf};
  return i;
}

char *magick(char **array1, char **array2, char **array3, int n)
{
  char buf[255];
  memset(buf, 0, 255);
  int rnd = random() % n;
  strncat(buf, array1[rnd], strlen(array1[rnd]));
  char tmp = '-';
  strncat(buf, &tmp, 1);
  rnd = random() % n;
  strncat(buf, array2[rnd], strlen(array2[rnd]));
  tmp = ' ';
  strncat(buf, &tmp, 1);
  rnd = random() % n;
  strncat(buf, array3[rnd], strlen(array3[rnd]));
  tmp = '\0';
  strncat(buf, &tmp, 1);
  return strdup(buf);
}

void list_db(item_t *items, int no_items)
{
  for(int i = 0; i < no_items; i++)
  {
  printf("%d. %s \n", i+1, items[i].name);
  }
}

void edit_db(item_t *items, int no_items)
{
  printf("%d", no_items);
  int i = 0;
  do
  {
    i = ask_question_int("Vilken vara skall editeras? (1-16)")-1;
  }
  while (i >= no_items || i < 0);
  print_item(&items[i]);
  items[i] = input_item();
}

bool valid_question(char *str)
{
  bool check = true;
  int length = strlen(str);
  for (int i=0; i<length; i++)
  {
    if (!(str[i] == 'L' || str[i] == 'l' || str[i] == 'T' || str[i] == 't' || str[i] == 'R' || str[i] == 'r' || str[i] == 'G' || str[i] == 'g' || str[i] == 'H' || str[i] == 'h' || str[i] == 'A' || str[i] == 'a'))
    {
    check = false;
    }
  }
  return check;
}  


void print_menu()
{
  printf("[L]ägga till en vara\n[T]a bort en vara\n[R]edigera en vara\nÅn[g]ra senaste ändringen\nLista [h]ela varukatalogen\n[A]vsluta");
}

char *ask_question_menu()
{
  char *choice = ask_question("[L]ägga till en vara\n[T]a bort en vara\n[R]edigera en vara\nÅn[g]ra senaste ändringen\nLista [h]ela varukatalogen\n[A]vsluta", valid_question, (convert_func) strdup).string_value;
  choice[0] = toupper(( unsigned char )choice[0]);
  return choice;
}

void add_item_to_db(item_t *items, int *no_items)
{
  items[*no_items] = input_item();
   *(no_items) = *no_items +1;
}

void remove_item_from_db(item_t *items, int *no_items)
{
  printf("%d", *no_items);
  int i = 0;
  do
  {
    char question[255];
    snprintf(question, 255, "Vilken vara skall tas bort? (1-%d)", *no_items);
    i = ask_question_int(question)-1;
  }
  while (i >= *no_items || i < 0);
  for(; i<*no_items; i++)
  {
    items[i] = items[i+1];
  }
  *(no_items) = *no_items - 1;
}

void event_loop(item_t *items, int no_items)
{
  while (true)
  {
    char *choice = ask_question_menu(items, no_items);
    if (choice[0] == 'L')
      add_item_to_db(items, &no_items);
    else if (choice[0] == 'T')
      remove_item_from_db(items, &no_items);
    else if (choice[0] == 'R')
      edit_db(items, no_items);
    else if (choice[0] == 'G')
      puts("Not yet implemented!");
    else if (choice[0] == 'H')
      list_db(items, no_items);
    else if (choice[0] == 'A')
      return;
  }
  
}
int main(int argc, char *argv[])
{
  srand(time(NULL));
  char *array1[] = { "Laser",        "Polka",    "Extra" }; // TODO: Lägg till!
  char *array2[] = { "förnicklad",   "smakande", "ordinär" }; // TODO: Lägg till!
  char *array3[] = { "skruvdragare", "kola",     "uppgift" }; // TODO: Lägg till!

  if (argc < 2)
  {
    printf("Usage: %s number\n", argv[0]);
  }
  else
  {
    item_t db[16]; // Array med plats för 16 varor
    int db_siz = 0; // Antalet varor i arrayen just nu

    int items = atoi(argv[1]); // Antalet varor som skall skapas
    if (items > 0 && items <= 16)
    {
      for (int i = 0; i < items; ++i)
      {
        // Läs in en vara, lägg till den i arrayen, öka storleksräknaren
        item_t item = input_item();
        db[db_siz] = item;
        ++db_siz;
      }
    }
    else
    {
      puts("Sorry, must have [1-16] items in database.");
      return 1; // Avslutar programmet!
    }

    for (int i = db_siz; i < 16; ++i)
      {
        char *name = magick(array1, array2, array3, 3); // TODO: Lägg till storlek
        char *desc = magick(array1, array2, array3, 3); // TODO: Lägg till storlek
        int price = random() % 200000;
        char shelf[] = { random() % ('Z'-'A') + 'A',
                         random() % 10 + '0',
                         random() % 10 + '0',
                         '\0' };
        item_t item = make_item(name, desc, price, shelf);

        db[db_siz] = item;
        ++db_siz;
      }
      
      
      event_loop(db, db_siz);

     // Skriv ut innehållet
     /*
     for (int i = 0; i < db_siz; ++i)
     {
      
       print_item(&db[i]);
     }
  
*/
  }
  return 0;
}