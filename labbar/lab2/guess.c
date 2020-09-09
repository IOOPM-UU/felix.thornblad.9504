#include "utils.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

int main(int argc, char *argv[])
{
  int limit = 15;
  srandom(time(NULL));
  int num = random() % 1024;
  int guess;
  int counter = 1;
    
  char* name = ask_question_string("Skriv in ditt namn: ");
    
  printf("Du %s, jag tänker på ett tal ... kan du gissa vilket?\n", name);
 
  guess = ask_question_int("");
  if(guess != num)
  {
    do
    {
      if(guess<num)
      {
      guess = ask_question_int("För litet!");
      }
      else 
      {
        guess = ask_question_int("För stort!");
      }
      counter++;
    }
    while (guess != num && counter <= limit);
  }
  if(counter >= limit)
  {
    printf("Nu har du slut på gissningar! Jag tänkte på %d!\n", num);
  }
  else
  {
    printf("Bingo! \nDet tog %s %d gissningar att komma fram till %d\n", name, counter, num);
  }
    
 return 0;
}