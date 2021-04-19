package csi3471.bearMarket.ProductReview;

import csi3471.bearMarket.ProductFiles.Product;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ReadReviews {

    public static void readInReviews(Map<Integer, Product> productMap){
        final String REVIEW_FILE = "src/main/java/csi3471/bearMarket/ProductReview/productReviews.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(REVIEW_FILE), StandardCharsets.UTF_8));
            String line = "";
            //each line is a different product
            while ((line = bufferedReader.readLine())!= null){
                String[] parsedLine = line.split("\\|");
                int tempID = Integer.parseInt(parsedLine[0]);
                //loop through review details for product
                int t = 1;
                String tempUsername = "", tempRating = "", tempDesc = "";
                for (int i = 1; i < parsedLine.length; ++i){

                    switch (t){
                        case 1:
                            tempRating = parsedLine[i];
                            t = 2;
                            break;
                        case 2:
                            tempUsername = parsedLine[i];
                            t = 3;
                            break;
                        case 3:
                            tempDesc = parsedLine[i];
                            Review review = new Review(tempUsername, tempRating, tempDesc);
                            productMap.get(tempID).getReviews().add(review);
                            t = 1;
                            break;
                        default:
                            System.out.println("Error: switch value in ReadReviews ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
