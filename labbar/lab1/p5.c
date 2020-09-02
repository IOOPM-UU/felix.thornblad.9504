#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

int main(int argc, char *argv[])
{
  bool prime = true;
  char *tmp = argv[1];
  int number = atoi(tmp);
  float tmp2 = sqrt(number);
  int limit = floor(tmp2) + 1;
  for (int i = 2; i<=limit; i++)
  {
    for (int x = 2; x<=number; x++)
      {
        if(i*x == number)
        {
          prime = false;
        }
      }
  }
  if (prime)
  {
    puts("is a prime number");
  }
  else
  {
    puts("is not a prime numver");
  }
  return 0;
}