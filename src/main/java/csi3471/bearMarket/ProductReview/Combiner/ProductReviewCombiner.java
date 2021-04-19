package csi3471.bearMarket.ProductReview.Combiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProductReviewCombiner {
    public static void main(String[] args) throws IOException {
        //REVIEWS
        File reviewsFile = new File("src/main/java/csi3471/bearMarket/ProductReview/Combiner/reviews.txt");
        File productsFile = new File("src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
        File usernames = new File("src/main/java/csi3471/bearMarket/ProductReview/Combiner/usernames.txt");
        File productReviews = new File("src/main/java/csi3471/bearMarket/ProductReview/productReviews.txt");
        ArrayList<String> reviews = new ArrayList<>();
        ArrayList<String> usernameList = new ArrayList<>();

        try{
            //READ IN FILE WITH REVIEWS
            Scanner scanner = new Scanner(reviewsFile);
            while (scanner.hasNextLine()){
                reviews.add(scanner.nextLine());
            }
            //READ IN FILE OF USERNAMES
            scanner = new Scanner(usernames);
            while (scanner.hasNextLine()){
                usernameList.add(scanner.nextLine());
            }
            //WRITING TO FILE
            FileWriter fileWriter = new FileWriter(productReviews);
            scanner = new Scanner(productsFile);
            scanner.nextLine(); //skip 1st line heading
            Random rand = new Random();
            int randNdx = 0, randLoopNum = 0;
            double randomRating = 0;

            //prevent reuse of same username or review in same product
            ArrayList<String> usedUserNames = new ArrayList<>();
            ArrayList<String> usedReviews = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("###.#"); //rounds to 1 decimal place

            //loop through products
            while (scanner.hasNextLine()){
                //PRODUCT ID
                String line = scanner.nextLine();
                String[] splitLine = line.split("\t");
                fileWriter.write(splitLine[6] + "|");

                randLoopNum = rand.nextInt(7) + 1; //how many reviews to make
                for (int i = 0; i < randLoopNum; ++i){
                    //USERNAME
                    randNdx = rand.nextInt(usernameList.size() - 1);
                    String tempUser = usernameList.get(randNdx);
                    usedUserNames.add(tempUser);
                    usernameList.remove(randNdx);

                    //RATING
                    randNdx = rand.nextInt(9) + 1;
                    randomRating = rand.nextDouble();
                    randomRating += randNdx;

                    fileWriter.write( df.format(randomRating) + "|"); //rounds to 1 decimal place

                    //REVIEW
                    randNdx = rand.nextInt(reviews.size() - 1);
                    String tempReview = reviews.get(randNdx);
                    usedReviews.add(tempReview);
                    reviews.remove(randNdx);

                    fileWriter.write(tempUser + "|" + tempReview);
                    //end of line
                    if (i == randLoopNum - 1){ fileWriter.write("\n"); }
                    //not end of line
                    else{ fileWriter.write("|"); }
                }
                usernameList.addAll(usedUserNames);
                reviews.addAll(usedReviews);
                usedUserNames.clear();
                usedReviews.clear();
            }
            fileWriter.close();
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
