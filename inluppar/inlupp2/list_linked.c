#include "list_linked.h"
#include "iterator.h"

struct link {
    elem_t value;
    ioopm_link_t *next;
};

struct list {
    ioopm_link_t *first;
    ioopm_link_t *last;
    size_t size;
    ioopm_eq_function eq; 
};

struct iterator {
    ioopm_link_t *current;
    ioopm_list_t *list;
};

static ioopm_link_t *link_create(elem_t value, ioopm_link_t *next) {
    ioopm_link_t *link = calloc(1, sizeof(ioopm_link_t));
    link->value = value;
    link->next = next;
    return link;
}

static void link_destroy(ioopm_link_t *link) {
    free(link);
    link = NULL;
}

ioopm_list_t *ioopm_linked_list_create(ioopm_eq_function eq) {
    ioopm_list_t *list = calloc(1, sizeof(ioopm_list_t));
    list->eq = eq;
    return list;
}

void ioopm_linked_list_destroy(ioopm_list_t *list) {
    ioopm_linked_list_clear(list);
    free(list); 
    list = NULL;
}

void ioopm_linked_list_append(ioopm_list_t *list, elem_t value) {
    ioopm_link_t *next = list->last;
    ioopm_link_t *new_link;
    if (next == NULL) {
        new_link = link_create(value, NULL);
        list->first = new_link;
    } else {  
        new_link = link_create(value, NULL);
        next->next = new_link;
    }
    list->last = new_link; 
    list->size++;
}

void ioopm_linked_list_prepend(ioopm_list_t *list, elem_t value) {
    ioopm_link_t *next = list->first;
    ioopm_link_t *new_link;
    if (next == NULL) {
        new_link = link_create(value, NULL);
        list->last = new_link;
    } else {
        new_link = link_create(value, next);
    }
    list->first = new_link; 
    list->size++;
}

ioopm_link_t *ioopm_linked_list_insert(ioopm_list_t *list, int index, elem_t value) {
    ioopm_link_t *next = list->first;
    ioopm_link_t *new_link;
    ioopm_link_t *before_next;

    if (index < 0 || index > list->size) {
        return NULL;
    }

    if (next == NULL) {
        new_link = link_create(value, NULL);
    } else {

        for (int i = 0; i < index; i++) {
            before_next = next;
            next = next->next;
        }

        new_link = link_create(value, next);
        
        if (index > 0) {
            before_next->next = new_link;
        }
    }

    list->size++;

    if (index == 0) {
        list->first = new_link;     
    }

    if (next == NULL) {
        list->last = new_link;
    }

    return new_link;
}

option_t ioopm_linked_list_remove(ioopm_list_t *list, int index) {
    ioopm_link_t *next = list->first;
    ioopm_link_t *before_next;

    if (index < 0 || index+1 > list->size) {
        return Failure();
    }

    for (int i = 0; i < index; i++) {
        before_next = next;
        next = next->next;
    }

    elem_t value = next->value;
    list->size--;

    if (index > 0) {
        before_next->next = next->next;
    }

    if (index == 0) {
        list->first = next->next;     
    }

    if (next->next == NULL) {
        list->last = before_next;
    }

    if (list->size == 0) {
        list->first = NULL;
        list->last = NULL;
    }

    link_destroy(next);
    return Success(value);
}

option_t ioopm_linked_list_get(ioopm_list_t *list, int index) {
    ioopm_link_t *next = list->first;

    if (index < 0 || index+1 > list->size) {
        return Failure();
    }

    for (int i = 0; i < index; i++) {
        if (next->next != NULL) {
            next = next->next;
        }
    }

    return Success(next->value);
}

bool ioopm_linked_list_contains(ioopm_list_t *list, elem_t element) {
    ioopm_link_t *next = list->first;
    size_t size = ioopm_linked_list_size(list);
    for (int i = 0; i < size; i++) {
        if (list->eq(next->value,element)) {
            return true;
        }
        next = next->next;
    }
    return false;
}

size_t ioopm_linked_list_size(ioopm_list_t *list) {
    return list->size;
}

bool ioopm_linked_list_is_empty(ioopm_list_t *list) {
    size_t size = ioopm_linked_list_size(list);
    return size == 0;
}

void ioopm_linked_list_clear(ioopm_list_t *list) {
    ioopm_link_t *next = list->first;
    ioopm_link_t *tmp;

    while (next != NULL) {
        tmp = next->next;
        link_destroy(next);
        next = tmp;
    }

    list->first = NULL;
    list->last = NULL;
    list->size = 0;
}

bool ioopm_linked_list_all(ioopm_list_t *list, ioopm_predicate prop, void *extra) {
    ioopm_link_t *next = list->first;
    bool result = true;

    while (next != NULL) {
        result = result && prop(pointer_elem(NULL),next->value,list->eq ,extra);
        if (result == false) {
            return result;
        }
        next = next->next;
    }
    return result;
}

bool ioopm_linked_list_any(ioopm_list_t *list, ioopm_predicate prop, void *extra) {
    ioopm_link_t *next = list->first;
    bool result = false;

    while (next != NULL) {
        result = prop(pointer_elem(NULL),next->value,list->eq, extra);
        if (result == true) {
            return result;
        }
        next = next->next;
    }
    return result;
}

void ioopm_linked_apply_to_all(ioopm_list_t *list, ioopm_apply_function fun, void *extra) {
    ioopm_link_t *next = list->first;

    while (next != NULL) {
        fun(pointer_elem(NULL),&next->value, extra);
        next = next->next;
    }
}


ioopm_list_iterator_t *ioopm_list_iterator(ioopm_list_t *list) {
    ioopm_list_iterator_t *i = calloc(1, sizeof(ioopm_list_iterator_t));
    i->current = list->first;
    i->list = list;
    return i;
}

bool ioopm_iterator_has_next(ioopm_list_iterator_t *iter) {
    if (iter->current == NULL) {
        return false;
    }

    return iter->current->next != NULL;
}

option_t ioopm_iterator_next(ioopm_list_iterator_t *iter) {
    bool has_next = ioopm_iterator_has_next(iter);
    elem_t result;

    if (has_next) {
        iter->current = iter->current->next;
        result = iter->current->value;
        return Success(result);
    }

    return Failure();
}

elem_t ioopm_iterator_remove(ioopm_list_iterator_t *iter) {
     
    ioopm_link_t *to_remove = iter->current;
    elem_t result = to_remove->value;
    option_t next; 
    int count = 0;

    do {
        next = ioopm_iterator_next(iter);
        count++;
    }
    while (Successful(next));
    
    iter->current = to_remove->next;
    ioopm_linked_list_remove(iter->list, iter->list->size - count);
    return result;
}

void ioopm_iterator_insert(ioopm_list_iterator_t *iter, elem_t element) {
    
    option_t next;  
    int count = 0;

    do {
        next = ioopm_iterator_next(iter);
        count++;
    }
    while (Successful(next));

    if (count == 1) { count = 0;
    }

    ioopm_link_t *insert = ioopm_linked_list_insert(iter->list, iter->list->size - count, element);

    if (insert != NULL) {
        iter->current = insert;
    }
}

void ioopm_iterator_reset(ioopm_list_iterator_t *iter) {
    iter->current = iter->list->first;
}

option_t ioopm_iterator_current(ioopm_list_iterator_t *iter) {
    if (iter->current == NULL) {
        return Failure();
    }
    return Success(iter->current->value);
}

void ioopm_iterator_destroy(ioopm_list_iterator_t *iter) {
    free(iter);
    iter = NULL;
}




