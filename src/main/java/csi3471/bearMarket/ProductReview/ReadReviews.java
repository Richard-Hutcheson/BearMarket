package csi3471.bearMarket.ProductReview;

import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.ProductFiles.Product;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Open reviews file and read them into Review objects
 * @author Richard Hutcheson
 */
public class ReadReviews {
    /**
     * read in product reviews and create Review objects out of them, assign them to respective Products
     * @param productMap map containing product and its unique id number
     */
    public static void readInReviews(Map<Integer, Product> productMap){
        ProgLogger.LOGGER.info("Read Reviews Function Called");
        final String REVIEW_FILE = "src/main/java/csi3471/bearMarket/ProductReview/productReviews.txt";
        try {
            ProgLogger.LOGGER.info("Trying to read in file");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(REVIEW_FILE), StandardCharsets.UTF_8));
            String line = "";
            //each line is a different product
            while ((line = bufferedReader.readLine())!= null){
                String[] parsedLine = line.split("\\|");
                int tempID = Integer.parseInt(parsedLine[0]);
                //loop through review details for product
                int t = 1;
                String tempUsername = "", tempRating = "", tempDesc = "";
                ProgLogger.LOGGER.info("Parsing Review");
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
                            try{
                                productMap.get(tempID).getReviews().add(review);
                                t = 1;
                            }catch(NullPointerException e){
                                ProgLogger.LOGGER.warning("product was deleted so ignore this review and do not add to map");
                                break;
                            }
                            break;
                        default:
                            ProgLogger.LOGGER.warning("switch value in ReadReviews on wrong value. this can result in improper parsing of reviews");
                    }
                }
            }
            bufferedReader.close();
            ProgLogger.LOGGER.info("Finished Reading Review file");

        } catch (IOException e) {
            ProgLogger.LOGGER.severe("IO Exception, error reading in review file");
            e.printStackTrace();
        }

    }
}
