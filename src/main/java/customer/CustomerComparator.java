package customer;

import java.util.Comparator;

/**
 * Created by Aditya on 2017-05-17.
 */
public class CustomerComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer c1, Customer c2) {
        //This Comparator is used to compare two customers objects based on their ArrivedTime, Number of Items and CustomerType.
        if (c1 != null && c2 != null) {
            if (c1.getTimeArrived() != c2.getTimeArrived()) {
                return c1.getTimeArrived() < c2.getTimeArrived() ? -1 : 1;
            }
            if (c1.getNumberOfItems() != c2.getNumberOfItems()) {
                return c1.getNumberOfItems() < c2.getNumberOfItems() ? -1 : 1;
            }
            if (c1.getCustomerType() != c2.getCustomerType()) {
                return c1.getCustomerType() == CustomerType.TYPEA ? -1 : 1;
            }
        }
        return c1 != null ? -1 : 1;
    }
}
