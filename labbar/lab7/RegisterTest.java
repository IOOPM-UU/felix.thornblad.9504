public class RegisterTest {

  public static void main(String [] args) {
      Queue<Customer> q = new Queue<Customer>();
      Customer c1 = new Customer(0,2);
      Customer c2 = new Customer(0,3);
      Customer c3 = new Customer(0,5);
      Register r = new Register(true, q);
      r.addToQueue(c1);
      System.out.println("True: " + r.hasCustomer() + "\n" + "Length 1: " +  
              r.getQueueLength() + "\n" + "False: " + r.currentCustomerIsDone()
              + "\n" + "True: " + r.isOpen() + "\n");

      r.addToQueue(c2);
      r.addToQueue(c3);
      r.step();
      r.step();
      System.out.println(c1.toString() + "\n" + "True: " + r.currentCustomerIsDone());
    
  }
}
