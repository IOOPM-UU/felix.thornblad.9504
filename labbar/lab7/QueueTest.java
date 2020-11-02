public class QueueTest {

    public static void main(String [] args) {

        Queue<Customer> q = new Queue<Customer>();
        Customer c1 = new Customer(0,1);
        Customer c2 = new Customer(0,2);
        Customer c3 = new Customer(0,3);
        Customer c4 = new Customer(0,4);

        q.enqueue(c1);
        q.enqueue(c4);
        q.enqueue(c3);
        q.enqueue(c2);

        c1 = q.dequeue();
        c2 = q.dequeue();
        c3 = q.dequeue();
        c4 = q.dequeue();

        System.out.println(c1.toString() + "\n" + c2.toString() + "\n" + c3.toString() + "\n" + c4.toString());

        q.dequeue();
    }

}
