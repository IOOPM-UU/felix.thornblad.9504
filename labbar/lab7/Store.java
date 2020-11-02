public class Store {
    private Register registers[];
    private Queue<Customer> doneCustomers;

    public Store(int quantity) {
        registers = new Register[quantity]; 
        doneCustomers = new Queue<Customer>();
        for(int i = 0; i < quantity; i++) {
            Queue<Customer> q = new Queue<Customer>();
            registers[i] = new Register(false, q);
        }
        registers[0].open();
    }


    public int getAverageQueueLength() {
        int length = 0;
        int openReg = 0;
        for(Register r : this.registers) {
            if(r.isOpen()) {
                length += r.getQueueLength();
                openReg++;
            }
        }
        int avergeLength = length/openReg;

        return avergeLength;
    }

    public void newCustomer(Customer c) {
        Register reg = registers[0];
        int shortestQueue = reg.getQueueLength();
        int queue = 0;

        for(Register r : this.registers) {
            if(r.isOpen()) {
                queue = r.getQueueLength();
                if(queue < shortestQueue) {
                    shortestQueue = queue;
                    reg = r;
                }
            }
        }
        reg.addToQueue(c);
    }

    //public Queue<Customer> step() {
    public void step(int time) {
       // Queue<Customer> q = new Queue<Customer>();
        for(Register r : this.registers) {
            if(r.isOpen() && r.hasCustomer()) {
                r.step();
                if(r.currentCustomerIsDone()) {
                    Customer c = r.removeCurrentCustomer();
                    //q.enqueue(c);
                    c.setWaitTime(time-c.getBornTime());
                    doneCustomers.enqueue(c);
                }
            }
        }
        //return q;
    }

    public void openNewRegister() {
        for(Register r : this.registers) {
            if(!r.isOpen()) {
                r.open();
                return;
            }
        }
    }
    
    public Queue<Customer> getDoneCustomers() {
        //To remember list between calls to step
        return doneCustomers;
    }
    
   
    public String toString() {
        String s = "";
        for(Register r : this.registers) {
            s += (r.toString() + "\n");
        }
        return s;
    }
    
}
