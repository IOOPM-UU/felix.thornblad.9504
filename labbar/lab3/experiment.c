//#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
extern char *strdup(const char *);
typedef union { 
  int   int_value;
  float float_value;
  char *string_value;
} answer_t;
typedef bool(*check_func)(char*);
typedef answer_t(*convert_func)(char*);


int read_string(char *buf, int buf_siz)
{
  int counter = 0;
  int c = 0;
  for(int i = 0; i<buf_siz; i++)
  {
    buf[i] = 0;
  }
  
  do 
    {
      c = getchar();
      if (c != '\n')
      {
        buf[counter] = c;
        counter++;
      }
    } 
  while(c != '\n' && counter < buf_siz-1);
  return counter;
}


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
bool not_empty(char *str)
{
  return strlen(str) > 0;
}

answer_t ask_question(char *question, check_func check, convert_func convert)
{
    int buf_siz = 255;
    char buf[buf_siz];
    do
    {
    printf("%s\n", question);
    read_string(buf, buf_siz);
    }
    while (!check(buf));
    return convert(buf);
}

int ask_question_int(char *question)
{
  answer_t answer = ask_question(question, is_number, (convert_func) atoi);
  return answer.int_value; // svaret som ett heltal
}

char *ask_question_string(char *question)
{
  return ask_question(question, not_empty, (convert_func) strdup).string_value;
}

int main(int argc, char *argv[])
{
    char *string = ask_question_string(argv[1]);
    printf("%s\n", string);
    int num = ask_question_int(argv[2]);
    printf("%d\n", num);
    return 0;
}