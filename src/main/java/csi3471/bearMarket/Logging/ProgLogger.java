package csi3471.bearMarket.Logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/** @author Richard Hutcheson
 *  This class initializes the Logger and assigns its level and where to write data out to
 */
public class ProgLogger {
    public final static Logger LOGGER = Logger.getLogger("ProgLogger");
    /**
     * @throws IOException
     * if the log file is unable to opened, it will throw exception
     */
    static public void setup() throws IOException{
        LOGGER.setLevel(Level.ALL);
        FileHandler fileHandler = new FileHandler("src/main/java/csi3471/bearMarket/Logging/LogData.log");
        SimpleFormatter txtFormatter = new SimpleFormatter();
        fileHandler.setFormatter(txtFormatter);
        LOGGER.addHandler(fileHandler);
    }
}
