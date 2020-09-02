#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
  int counter = 0;
  char *tmp = argv[1];
  int rows = atoi(tmp);
  tmp = argv[2];
  int growth = atoi(tmp);
  for (int i = 1; i<=rows; i++)
  {
    for (int x = 1; x<=i*growth; x++)
      {
        counter ++;
        printf("%s", "*"); 
      }
      printf("\n");
  }
  printf("Totalt: %d\n", counter);
  return 0;
}