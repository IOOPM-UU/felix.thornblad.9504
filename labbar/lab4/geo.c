#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
struct point
{
  int x;
  int y;
};
typedef struct point point_t;
struct rectangle
{
  point_t ul;
  point_t dr;
};
typedef struct rectangle rectangle_t;



void print_point(point_t *p)
{
  printf("point(%d,%d)", p->x, p->y);
}

point_t make_point(int x, int y)
{
  point_t p = {.x = x, .y = y};
  return p;
}


void print_rect(rectangle_t *r)
{
  point_t ul = r->ul;
  point_t dr = r->dr;
  printf("rectangle(upper_left=");
  print_point(&ul);
  printf(", lower_right=");
  print_point(&dr);
  printf("\n");
}

rectangle_t make_rect(int x1, int y1, int x2, int y2)
{
  point_t ul = make_point(x1,y1);
  point_t dr = make_point(x2,y2);
  rectangle_t r = {.ul = ul, .dr = dr};
  return r;
}

int area_rect(rectangle_t *r)
{
  point_t ul = r->ul;
  point_t dr = r->dr;
  int base = (dr.x) - (ul.x);
  int height = (ul.y) - (dr.y);
  return (base*height);
}

bool intersects_rect(rectangle_t *r1, rectangle_t *r2)
{
  point_t ul = r1->ul;
  point_t dr = r1->dr;
  point_t ul2 = r2->ul;
  point_t dr2 = r2->dr;
  int ulx = ul.x;
  int uly = ul.y;
  int drx = dr.x;
  int dry = dr.y;
  int ulx2 = ul2.x;
  int uly2 = ul2.y;
  int drx2 = dr2.x;
  int dry2 = dr2.y;
   
  return !(ulx > drx2 || drx < ulx2 || uly < dry2 || dry > uly2);
}

rectangle_t intersection_rect(rectangle_t *r1, rectangle_t *r2)
{
  rectangle_t r;
  point_t ul = r1->ul;
  point_t dr = r1->dr;
  point_t ul2 = r2->ul;
  point_t dr2 = r2->dr;
  int ulx = ul.x;
  int uly = ul.y;
  int drx = dr.x;
  int dry = dr.y;
  int ulx2 = ul2.x;
  int uly2 = ul2.y;
  int drx2 = dr2.x;
  int dry2 = dr2.y;
  
  
  
  if(ulx<=drx2 && ulx>=ulx2 && uly<=uly2 && uly>=dry2) 
  {
    r = make_rect(ulx, uly, drx2, dry2);     
  }
  else if (drx<=drx2 && drx>=ulx2 && dry<=uly2 && dry>=dry2)
  {
    r = make_rect(ulx2, uly2, drx, dry);
  }
  else if(ulx<ulx2)
  {
    r = make_rect(ulx2, uly, drx, dry2);
  }
  else //if(ulx>ulx2)
  {
    r = make_rect(ulx, uly2, drx2, dry); 
  }
  return r;
}

int main(void)
{
  rectangle_t target = make_rect(0, 4, 4, 0);
  rectangle_t target2 = make_rect(-2, 2, 2, -2);
  rectangle_t inter = intersection_rect(&target, &target2);
  print_rect(&inter);
  //bool cross = intersects_rect(&target, &target2);
  //printf("%d\n", cross);
  /*rectangle_t target = make_rect(0, 4, 4, 0);
  int area = area_rect(&target);
  printf("%d", area);
  print_rect(&target);
  point_t target = make_point(2,4);
  print_point(&target);
  /**/
  return 0;
}