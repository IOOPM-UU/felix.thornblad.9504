#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

void print_number(int num)
{
    if (num % 3 == 0 && num % 5 == 0)
    {
      printf("Fizz Buzz");
    }
    else if (num % 3 == 0)
    {
      printf("Fizz");
    }
    else if (num % 5 == 0)
    {
      printf("Buzz");
    }
    else
    {
      printf("%d", num);
    }
}

int main(int argc, char *argv[])
{
  char *tmp = argv[1];
  int limit = atoi(tmp);
  for(int i = 1; i<=limit; i++)
  {
      print_number(i);
      if(i<limit)
      {
          printf(", ");
      }
  }
  printf("\n");
  return 0;
} 