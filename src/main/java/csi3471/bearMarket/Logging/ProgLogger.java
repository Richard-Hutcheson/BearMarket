package csi3471.bearMarket.Logging;

import java.io.IOError;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ProgLogger {

    static private FileHandler fileHandler;
    static private SimpleFormatter txtFormatter;
    public final static Logger LOGGER = Logger.getLogger("ProgLogger");

    static public void setup() throws IOException{
        LOGGER.setLevel(Level.ALL);
        fileHandler = new FileHandler("src/main/java/csi3471/bearMarket/Logging/LoggerData.log");
        txtFormatter = new SimpleFormatter();
        fileHandler.setFormatter(txtFormatter);
        LOGGER.addHandler(fileHandler);
    }
}
