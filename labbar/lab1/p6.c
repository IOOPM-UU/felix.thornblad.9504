#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

bool is_number(char *str)
{
  bool check = true;
  int length = strlen(str);
  for (int i=0; i<length; i++)
  {
      if (isdigit(str[i]) == false)
      {
          check = false;
      }
  }
  return check;
}
bool is_positive(char *str)
{
  int x = atoi(str);
  if (x>0)
  {
    return true;
  }
  else 
  {
    return false;
  }
}

int main(int argc, char *argv[])
{
  if(!is_number(argv[1]))
  {
    printf("%s is not a number\n", argv[1]);
    return 0;
  }
  if (!is_number(argv[2]))
  {
    printf("%s is not a number\n", argv[2]);
    return 0;
  }
  if(!is_positive(argv[1]))
  {
    printf("%s is not a positive number\n", argv[1]);
    return 0;
  }
  if(!is_positive(argv[2]))
  {
    printf("%s is not a positive number\n", argv[2]);
    return 0;
  }
  if (!(argc == 3))
  {
    printf("Please provide two command line arguments!\n");
    return 0;
  }
  else
  {
  char *tmp = argv[1];
  int a = atoi(tmp);
  int print_a = a;
  tmp = argv[2];
  int b = atoi(tmp);
  int print_b = b;
  while (a!=b && a>0 && b>0)
  {
    if(a<b)
    {
      b-=a;
    }
    else 
    {
      a-=b;
    }
  }
  printf("gcd(%d, %d) = %d\n", print_a, print_b, a);
  return 0;
  }
}