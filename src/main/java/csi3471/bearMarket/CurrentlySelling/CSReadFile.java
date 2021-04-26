package csi3471.bearMarket.CurrentlySelling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import csi3471.bearMarket.Logging.ProgLogger;

/**
 * Helper function to read in the user's currently selling information from the file.
 * Note: Antiquated function that is no longer used.
 * @author Josh McKone
 *
 */
public class CSReadFile {
    public static void readFile(String file, Vector<CSProduct> pv) {
        ProgLogger.LOGGER.info("Currently Selling file reader function called.");
        
        try {
            ProgLogger.LOGGER.info("Attempting to open file");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            br.readLine(); //Skip line with account information
            String line = br.readLine();
            String[] split = line.split(",");
            for(String id : split) {
                CSProduct p = new CSProduct(Integer.parseInt(id));
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
