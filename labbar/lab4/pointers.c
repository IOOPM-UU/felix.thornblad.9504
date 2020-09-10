#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

 
void print(char *str)
{
  char *start = str;
  char *end = start + strlen(str)-1;
  for (; start <= end; start++)
    {
      putchar(*start);
    }
  putchar('\n');
}

void swap(int *a, int *b)
{
  int tmp = *a;
  *a = *b;
  *b = tmp;
}

int main(int argc, char *argv[])
{
  print(argv[1]);
  int x = 7;
  int y = 42;
  swap(&x, &y);
  printf("%d, %d\n", x, y);
  return 0;
}

