import grocerystore.TestGroceryStore;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Aditya on 2017-05-17.
 */
public class ProcessFileInputs {
    final static String path = "D:/Intellij/GroceryStore/src/test/resources/";

    @Test
    public void Test_Given_Input_File_One() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                7,
                testGroceryStore.timeRequiredToProcessCustomers(path + "inputFile1.txt"));

    }

    @Test
    public void Test_Given_Input_File_Two() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                13,
                testGroceryStore.timeRequiredToProcessCustomers(path + "inputFile2.txt"));

    }

    @Test
    public void Test_Given_Input_File_Three() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                6,
                testGroceryStore.timeRequiredToProcessCustomers(path + "inputFile3.txt"));

    }

    @Test
    public void Test_Given_Input_File_Four() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                9,
                testGroceryStore.timeRequiredToProcessCustomers(path + "inputFile4.txt"));

    }

    @Test
    public void Test_Given_Input_File_Five() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                11,
                testGroceryStore.timeRequiredToProcessCustomers(path + "inputFile5.txt"));

    }

    @Test
    public void Test_Invalid_Input_File_Name() throws Exception {
        try {
            TestGroceryStore testGroceryStore = new TestGroceryStore();
            testGroceryStore.timeRequiredToProcessCustomers(path + "file_not_present.txt");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IOException);
        }
    }

    @Test
    public void Read_Invalid_Input_File_Test() throws Exception {
        try {
            TestGroceryStore testGroceryStore = new TestGroceryStore();
            testGroceryStore.timeRequiredToProcessCustomers(path + "invalidInput.txt");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
        }
    }

    @Test
    public void Read_Empty_Input_File_Test() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                0,
                testGroceryStore.timeRequiredToProcessCustomers(path + "emptyInputFile.txt"));
    }

    @Test
    public void No_Customer_Input_File_Test() throws Exception {
        TestGroceryStore testGroceryStore = new TestGroceryStore();
        Assert.assertEquals("Time doesn't match for given input file",
                0,
                testGroceryStore.timeRequiredToProcessCustomers(path + "noCustomer.txt"));
    }
}
