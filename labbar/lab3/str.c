#include "utils.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


void print(char *str)
{
  int counter = 0;
  do 
    {
      putchar(str[counter]);
      counter++;
    } 
  while(str[counter] != '\0' && counter < 255);
}

void println(char *str)
{
  print(str);
  putchar('\n');
}


int string_length(char *str)
{
  int counter = 0;
  do 
    {
      counter++;
    } 
  while(str[counter] != '\0' && counter < 255);
  return counter;
}


int main(int argc, char *argv[])
{
  
  println(argv[1]);
  /*if (argc < 2)
  {
    printf("Usage: %s words or string", argv[0]);
  }
  else
  {
    for (int i = 1; i < argc; ++i)
    {
      int expected = strlen(argv[i]);
      int actual   = string_length(argv[i]);
      printf("strlen(\"%s\")=%d\t\tstring_length(\"%s\")=%d\n",
             argv[i], expected, argv[i], actual);
    }
  }
 */
  return 0;
}