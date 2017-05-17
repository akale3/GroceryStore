package grocerystore;

import customer.Customer;
import customer.CustomerType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by Aditya on 2017-05-16.
 */
class ProcessFile {

    /**
     * This method is used to read all lines of a file and store them in ArrayList.
     *
     * @param fileName
     * @return ArrayList<String>
     * @throws IOException
     */
    protected ArrayList<String> readInputFile(final String fileName) throws IOException {
        final ArrayList<String> inputLines = new ArrayList<>();
        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> {
                inputLines.add(s);
            });
        } catch (Exception e) {
            System.out.println("Unable to read input file. " +
                    "Please provide valid input file path.");
            throw e;
        }
        return inputLines;
    }

    /**
     * This method is used to create a dynamic Registers by parsing first line of input file.
     *
     * @param totalRegisters
     * @return Map
     */
    protected Map<Integer, Deque<Customer>> createRegisterQueues(final int totalRegisters) {
        final Map<Integer, Deque<Customer>> registerMap = new HashMap<>();
        for (int i = 1; i <= totalRegisters; i++) {
            registerMap.put(i, new LinkedList<>());
        }
        return registerMap;
    }

    /**
     * This method is used to create a list of all customers by parsing all input lines.
     *
     * @param inputLines
     * @return ArrayList<Customer>
     */
    protected ArrayList<Customer> getCustomerList(final ArrayList<String> inputLines) throws Exception{
        final ArrayList<Customer> customerList = new ArrayList<>();
        try {
            inputLines.stream().forEach(input -> {
                final String[] values = input.split(" ");
                final Customer customer = new Customer(Objects.equals(values[0], CustomerType.TYPEA.getType()) ? CustomerType.TYPEA : CustomerType.TYPEB,
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]));
                customerList.add(customer);
            });
        } catch (Exception e) {
            throw e;
        }
        return customerList;
    }

}
