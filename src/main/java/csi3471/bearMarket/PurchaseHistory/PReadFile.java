package csi3471.bearMarket.PurchaseHistory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import csi3471.bearMarket.Logging.ProgLogger;

public class PReadFile {
    public static void readFile(String file, Vector<PurchaseProduct> pv) {
        ProgLogger.LOGGER.info("Purchase History file reader function called.");
        
        try {
            ProgLogger.LOGGER.info("Attempting to open file");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            br.readLine(); //Skip line with account information
            br.readLine(); //Skip Currently Selling line
            String line = br.readLine();
            String[] split = line.split(",");
            for(String id : split) {
                PurchaseProduct p = new PurchaseProduct(Integer.parseInt(id));
                pv.add(p);
            }
            br.close();
            ProgLogger.LOGGER.info("Successfully read in file");
        } catch(IOException e) {
            ProgLogger.LOGGER.info("Failed to read in file.");
            e.printStackTrace();
        }
    }
}
