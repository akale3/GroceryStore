package grocerystore;

import customer.Customer;
import customer.CustomerType;

import java.util.Deque;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Aditya on 2017-05-16.
 */
class GroceryStoreProcessor {

    /**
     * This method is used to reduce customer items based on time difference between previous and current time.
     * It will reduce number of items of customer based on Trainee or Normal Cashier.
     *
     * @param registerMap
     * @param timeDifference
     */
    protected void processCustomersInQueue(final Map<Integer, Deque<Customer>> registerMap, final int timeDifference) {
        final int noOfRegisters = registerMap.entrySet().size();
        for (Map.Entry<Integer, Deque<Customer>> entry : registerMap.entrySet()) {
            // Trainee Cashier processing customer will take double the time than original.
            if (entry.getKey() == noOfRegisters) {
                reduceItemSize(entry, true, timeDifference);
            } else {
                reduceItemSize(entry, false, timeDifference);
            }
        }

    }

    private void reduceItemSize(final Map.Entry<Integer, Deque<Customer>> entry, final boolean reducedByHalf, final int timeDiff) {
        Deque<Customer> customerQueue = entry.getValue();
        for (int i = 0; i < timeDiff; i++) {
            if (!Objects.isNull(customerQueue) && customerQueue.size() > 0) {
                // Trainee Cashier processing customer will take double the time than original.
                if (reducedByHalf)
                    customerQueue.peekFirst().setNumberOfItems((float) (customerQueue.peekFirst().getNumberOfItems() - 0.5));
                else
                    customerQueue.peekFirst().setNumberOfItems(customerQueue.peekFirst().getNumberOfItems() - 1);

                if (customerQueue.peekFirst().getNumberOfItems() <= 0) {
                    customerQueue.pollFirst();
                }
            }
        }
    }

    /**
     * This method is used to add a Customer to a register based on the Customer Type.
     *
     * @param registerMap
     * @param customer
     */
    protected void addToRegister(final Map<Integer, Deque<Customer>> registerMap, final Customer customer) {
        if (customer.getCustomerType() == CustomerType.TYPEA)
            addTypeACustomer(registerMap, customer);
        else if (customer.getCustomerType() == CustomerType.TYPEB)
            addTypeBCustomer(registerMap, customer);
    }

    private void addTypeACustomer(final Map<Integer, Deque<Customer>> registerMap, final Customer customer) {
        final int[] val = new int[2];
        val[0] = Integer.MAX_VALUE;
        val[1] = 1;
        registerMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> {
                    if (val[0] > entry.getValue().size()) {
                        val[0] = entry.getValue().size();
                        val[1] = entry.getKey();
                    }
                });
        registerMap.get(val[1]).add(customer);
    }

    private void addTypeBCustomer(final Map<Integer, Deque<Customer>> registerMap, final Customer customer) {
        boolean isAddedToEmptyQueue = addToEmptyQueue(registerMap, customer);
        if (isAddedToEmptyQueue) return;

        final float[] val = new float[2];
        val[0] = Integer.MAX_VALUE;
        val[1] = 1;
        registerMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(entry -> entry.getValue().size() > 0)
                .forEachOrdered(entry -> {
                    if (val[0] > entry.getValue().peekLast().getNumberOfItems()) {
                        val[0] = entry.getValue().peekLast().getNumberOfItems();
                        val[1] = entry.getKey();
                    }
                });
        registerMap.get((int) val[1]).add(customer);
    }

    private boolean addToEmptyQueue(final Map<Integer, Deque<Customer>> registerMap, final Customer customer) {
        final boolean[] isEntryAdded = new boolean[1];
        isEntryAdded[0] = false;
        registerMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(entry -> entry.getValue().size() == 0)
                .forEachOrdered(entry -> {
                    if (!isEntryAdded[0]) {
                        isEntryAdded[0] = true;
                        registerMap.get(entry.getKey()).add(customer);
                    }
                });
        return isEntryAdded[0];
    }

    /**
     * This method is used to find the Maximum amount of time required to process all customers which are queued in Registers.
     *
     * @param registerMap
     * @return Integer
     */
    protected int getAdditionalTimeToProcessRestCustomers(final Map<Integer, Deque<Customer>> registerMap) {
        final int noOfRegisters = registerMap.entrySet().size();
        int maxAdditionalTime = 0;
        for (Map.Entry<Integer, Deque<Customer>> entry : registerMap.entrySet()) {
            int additionalTime = 0;
            Deque<Customer> customerQueue = entry.getValue();
            while (customerQueue.size() > 0) {
                additionalTime += entry.getKey() != noOfRegisters
                        ? customerQueue.poll().getNumberOfItems()
                        : (customerQueue.poll().getNumberOfItems() * 2);
            }
            maxAdditionalTime = Math.max(maxAdditionalTime, additionalTime);
        }
        return maxAdditionalTime;
    }
}
