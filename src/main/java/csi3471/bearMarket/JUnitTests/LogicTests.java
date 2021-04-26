package csi3471.bearMarket.JUnitTests;

import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.ProductFiles.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class LogicTests {
    @BeforeEach
    void init(){
        /*
        init here, if needed
        */
    }
    @Test
    void testProductCreation(){
        try{
            String[] arr1 = {"Alpha", "Health", "Big Elbow and Nose", "1", "10.0",	"$88.88",	"3" };
            Product p1 = new Product(arr1);
            if (p1.getID() != 3 || !p1.getProductName().equals("Alpha") || !p1.getCategory().equals("Health")
                || !p1.getDescription().equals("Big Elbow and Nose") || p1.getQuantity() != 1 || p1.getRating() != 10.0
                || p1.getPrice() != 88.88){
                System.out.println(p1.getPrice());
                System.out.println(p1.getProductName());
                System.out.println(p1.getCategory());
                System.out.println(p1.getDescription());
                Assertions.fail("Incorrect product creation");
            }
        }
        catch(Exception e){
            Assertions.fail("Exception encountered, product was unable to be created");
        }
    }
    @Test
    void testReadProducts(){
        try{
            final String file = "src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            bufferedReader.readLine(); //waste first line of csv
            String line = "";
            Vector<String> s = new Vector<>();
            while ((line = bufferedReader.readLine()) != null){
                s.add(line);
            }
            if (s.isEmpty()){
                Assertions.fail("Did not read in any products");
            }
            bufferedReader.close(); //close file
        }catch(IOException e){
            Assertions.fail("Failed to read in products");
        }
    }
    @Test
    void testReadReviews(){
        try{
            final String file = "src/main/java/csi3471/bearMarket/ProductReview/productReviews.txt";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            bufferedReader.readLine(); //waste first line of csv
            String line = "";
            Vector<String> s = new Vector<>();
            while ((line = bufferedReader.readLine()) != null){
                s.add(line);
            }
            if (s.isEmpty()){
                Assertions.fail("Did not read in any reviews");
            }
            bufferedReader.close(); //close file
        }catch(IOException e){
            Assertions.fail("Failed to read in reviews");
        }
    }
}
