#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include "utils.h"



bool not_empty(char *str)
{
  return strlen(str) > 0;
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


