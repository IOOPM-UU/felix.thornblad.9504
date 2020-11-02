public class Customer {
    private int bornTime;
    private int groceries;
    private int waitTime;

    public Customer () {
        this.bornTime = 0;
        this.groceries = 0;
        this.waitTime = 0;
    }

    public Customer (int bornTime, int groceries) {
        this.bornTime = bornTime;
        this.groceries = groceries;
    }

    public boolean serve() {
        if (!isDone()) {
            groceries--;
            return true;
        }
        return false;
    }

    public boolean isDone() {
        if (groceries == 0) {
            return true;
        }
        return false;
    }

    public int getBornTime() {
        return bornTime;
    }
    
    public int getWaitTime() {
        return waitTime;
    }
    
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
    
    
    public String toString() {
        return "[" + groceries + "]";
    }

    public static void main(String [] args) {
        Customer c = new Customer(0,3);
        c.serve();
        c.serve();
        c.serve();
        if(c.isDone()) {
             System.out.println("The customer is done, just as expected!");
        } else {
             System.out.println("The customer is not done, but should be...");
        }
    }
}
