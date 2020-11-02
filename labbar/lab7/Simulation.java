import java.util.Random;

public class Simulation {
    private Store store;
    private int time;
    private int intensity;
    private int maxGroceries;
    private int thresholdForNewRegister;
    private Queue<Customer> doneCustomers;
    private int customersServed;
    private int maxWait;
    private float averageWait;
    
    
    public Simulation(Store store, int intensity, int maxGroceries, int thresholdForNewRegister) {
        this.store = store;
        this.intensity = intensity;
        this.maxGroceries = maxGroceries;
        this.thresholdForNewRegister = thresholdForNewRegister;
        time = 0;
        customersServed = 0;
        maxWait = 0;
        averageWait = 0;
    }
    
    public void step() {
        store.step(time);
        time++;
        Random random = new Random();
        if(random.nextInt(100) < intensity)
        {
            Customer c = new Customer(time, random.nextInt(maxGroceries));
            store.newCustomer(c);
        }
        if(store.getAverageQueueLength() > thresholdForNewRegister)
        {
            store.openNewRegister();
        }
        doneCustomers = store.getDoneCustomers();
    }
    
    public int getCustomersServed() {
            customersServed = doneCustomers.length();
            return customersServed;
    }
    
    
    public int getMaxWait() {
        int waitTime = 0;
        for(int i = 0; i < doneCustomers.length(); i++)
        {
            Customer customer = doneCustomers.dequeue();
            doneCustomers.enqueue(customer);
            waitTime = customer.getWaitTime();
            if(waitTime > maxWait)
            {
                maxWait = waitTime;
            }
        }
        return maxWait;
    }
    
    
    public float getAverageWait() {
        float waitSum = 0;
        int waitTime = 0;
        int length = doneCustomers.length();
        for(int i = 0; i < length; i++)
        {
            Customer customer = doneCustomers.dequeue();
            doneCustomers.enqueue(customer);
            waitTime = customer.getWaitTime();
            waitSum += waitTime;
        }
        if(getCustomersServed() != 0)
        {
            averageWait = waitSum / getCustomersServed();
        }
        return averageWait;
    }
        
    
    public String toString() {
        return (store.toString() + "\nNumber of customers served: " + getCustomersServed() + "\nMax wait-time: " 
        + getMaxWait() + "\nAverage wait-time: " + getAverageWait());
    }
   
    
}