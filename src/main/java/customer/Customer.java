package customer;

/**
 * Created by Aditya on 2017-05-16.
 */
public class Customer {
    private CustomerType customerType;
    private int timeArrived;
    private float numberOfItems;

    public Customer(CustomerType customerType, int timeArrived, int numberOfItems) {
        this.customerType = customerType;
        this.timeArrived = timeArrived;
        this.numberOfItems = numberOfItems;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    //public void setCustomerType(CustomerType customerType) { this.customerType = customerType; }

    public int getTimeArrived() {
        return timeArrived;
    }

    //public void setTimeArrived(int timeArrived) { this.timeArrived = timeArrived; }

    public float getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(float numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

}
