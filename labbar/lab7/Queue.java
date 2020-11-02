public class Queue<Elem> {
    private Node<Elem> first;
    private Node<Elem> last;
    private int length;

    public Queue() {
        first = null;
        last = null;
        length = 0;
    }

    public int length() {
        return length;
    }

    public void enqueue(Elem elem) {
        if(length == 0) {
            first = new Node<Elem>(elem);
            last = first;
        } else if (length == 1) {
            last = new Node<Elem>(elem);
            first.set(last);
        } else {
           Node<Elem> n = new Node<Elem>(elem); 
           last.set(n);
           last = n;
        }
        length++;
    }

    public Elem dequeue() {
        if(length == 0) {
            throw new EmptyQueueException();
        }
        Elem elem = first.get();
        first = first.next;
        length--;
        return elem;
    }

    public Elem first() {
        return first.get();
    }

    private class Node<T> {
        private Elem element;
        private Node<T> next;

        private Node(Elem element) {
            this.element = element;
            next = null;
        }

        private void set(Node<T> n) {
            next = n;
        }

        private Elem get() {
            return element;
        }
    }

} 

