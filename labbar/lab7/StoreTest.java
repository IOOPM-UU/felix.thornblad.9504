public class StoreTest {

    public static void main(String [] args) {
      Store s = new Store(2);
      
      Customer c1 = new Customer(0,1);
      Customer c2 = new Customer(0,2);
      s.newCustomer(c1);
      s.newCustomer(c2);
      System.out.println("1 register, 2 customers, avg lenght: " + s.getAverageQueueLength());
      s.openNewRegister();
      System.out.println("2 register, 2 customers, avg length: " + s.getAverageQueueLength());
      Customer c3 = new Customer(0,3);
      Customer c4 = new Customer(0,4);
      s.newCustomer(c3);
      s.newCustomer(c4);
      System.out.println("2 register, 4 customers, avg length: " + s.getAverageQueueLength());
      System.out.println(s);
      s.step();
      System.out.println(s);
      s.step();
      System.out.println(s);
      s.step();
      System.out.println(s);
      Queue<Customer> q = s.getDoneCustomers();
      c1 = q.dequeue();
      c2 = q.dequeue();
      //System.out.println(c1.toString() + "\n" + c2.toString());
      
      
      
      
    }

}