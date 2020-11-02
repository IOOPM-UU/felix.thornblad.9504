public class Register {
    private boolean open;
    private Queue<Customer> queue;

    public Register(boolean open, Queue<Customer> queue) {
        this.open = open;
        this.queue = queue;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public boolean isOpen() {
        if (open == true) {
            return true;
        }
        return false;
    }

    public void step() {
        Customer c = queue.first();
        c.serve();
    }
    
    public boolean hasCustomer() {
        if(queue.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean currentCustomerIsDone() {
        Customer c = queue.first();
        if(c.isDone()) {
            return true;
        }
        return false;
    }

    public void addToQueue(Customer c) {
        queue.enqueue(c);
    }

    public Customer removeCurrentCustomer() {
        return queue.dequeue();
    }

    public int getQueueLength() {
        return queue.length();
    }
    
    public Customer getFirst() {
        return queue.first();
    }
    
    public String toString() {
        if(getQueueLength() == 0)
        {
            return "X [ ]";
        }
        else
        {
            Customer c = getFirst();
            String s = "  " + c.toString();
          
            for(int i = 0; i < getQueueLength()-1; i++)
            {
                s += "@";
            }
            return s;
        }
    }
}
