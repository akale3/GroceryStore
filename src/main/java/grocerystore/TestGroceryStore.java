package grocerystore;

import customer.Customer;
import customer.CustomerComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;

/**
 * Created by Aditya on 2017-05-16.
 */
public class TestGroceryStore {

    public static void main(String[] args) {
        try {
            TestGroceryStore testGroceryStore = new TestGroceryStore();
            int processingTime = testGroceryStore.timeRequiredToProcessCustomers(args[0]);
            System.out.println("Time Required to process all Customers is t = " + processingTime);
        } catch (Exception e) {
            System.out.println("Unable to calculate time. Error in input file.");
            e.printStackTrace();
        }
    }

    public int timeRequiredToProcessCustomers(final String fileName) throws Exception {

        final ProcessFile processFile = new ProcessFile();
        ArrayList<String> inputLines = processFile.readInputFile(fileName);

        if (inputLines.size() == 0 || inputLines.size() == 1)
            return 0;

        final int totalRegisters = Integer.parseInt(inputLines.get(0));
        final Map<Integer, Deque<Customer>> registerMap = processFile.createRegisterQueues(totalRegisters);
        inputLines.remove(0);

        final ArrayList<Customer> customerList = processFile.getCustomerList(inputLines);
        inputLines = null; // Allow GC to do its work.

        final GroceryStoreProcessor storeProcessor = new GroceryStoreProcessor();
        int previousTime = 0;
        int finalTime = 0;
        //This will sort all customers based on their arrival time, number of items and their Type to handle given scenarios.
        Collections.sort(customerList, new CustomerComparator());

        //Process all customers in First Come First Serve Basis.
        for (Customer customer : customerList) {
            int timeDifference = customer.getTimeArrived() - previousTime;
            storeProcessor.processCustomersInQueue(registerMap, timeDifference);
            storeProcessor.addToRegister(registerMap, customer);
            previousTime = customer.getTimeArrived();
            finalTime += timeDifference;
        }

        //Get additional time to process all customers which already joined Registers.
        int additionalRequiredTime = storeProcessor.getAdditionalTimeToProcessRestCustomers(registerMap);
        return (finalTime + additionalRequiredTime);
    }


}
