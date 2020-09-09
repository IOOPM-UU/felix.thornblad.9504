#include "utils.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
typedef int(*int_fold_func)(int, int);

/// Den intressanta delen av programmet
int fib(int num)
{
  int ppf = 0; // the two given fib values
  int pf  = 1;

  for (int i = 1; i < num; ++i)
  {
    int tmp = pf;
    pf = ppf + pf;
    ppf = tmp;
  }
  return pf;
}


int rec_sum(int n)
{
  if (n == 0)
  {
    return 0;
  }
  else if (n == 1)
  {
    return 1;
  }
  else
  {
    return (rec_sum(n-1) + rec_sum(n-2));
  }
}

int foldl_int_int(int numbers[], int numbers_siz, int_fold_func f)
{
  int result = 0;

  // Loopa över arrayen och för varje element e utför result = f(result, e)
  for (int i = 0; i < numbers_siz; ++i)
  {
    result = f(result, numbers[i]);
  }
  return result;
}


int add(int a, int b)
{
  return a + b;
}

long sum(int numbers[], int numbers_siz)
{
  return foldl_int_int(numbers, numbers_siz, add);
}


/// Den ointressanta main()-funktionen
int main(int argc, char *argv[])
{
  int numbers[255];
  int counter = 0;
  for (int i=1; i< argc; i++)
  {
    if(!is_number(argv[i]))
    {
      return 0;
    }
    else 
    {
      counter++;
      char *tmp = argv[i];
      numbers[i-1] = atoi(tmp);
    }
  }
  printf("%ld\n", sum(numbers, counter));
  /*
  if (argc != 2)
  {
    printf("Usage: %s number\n", argv[0]);
  }
  else
  {
    int n = atoi(argv[1]);
    if (n < 2)
    {
      printf("fib(%d) = %d\n", n, n);
    }
    else
    {
      printf("fib(%s) =%d, %d\n", argv[1],fib(n), rec_sum(n));
        
    }
  }
  /**/
  return 0;
}
