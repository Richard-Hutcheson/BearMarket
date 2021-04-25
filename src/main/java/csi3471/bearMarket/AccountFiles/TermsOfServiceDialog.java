//Created by Noah Lambaria
package csi3471.bearMarket.AccountFiles;

import csi3471.bearMarket.Logging.ProgLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * This class implements the Terms of Service window
 * @author Noah Lambaria
 */
public class TermsOfServiceDialog {

    /**
     * This function creates the ToS dialog
     */
    public void createDialog(){
        ProgLogger.LOGGER.info("Creating ToS Dialog");
        //CREATE DIALOG
        JPanel mainPanel = new JPanel();
        JDialog dialog = new JDialog(new JFrame("Terms of Service"));
        BoxLayout boxLayout = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        //TEXT AREA
        JTextArea textArea = new JTextArea(30,50);
        textArea.setEditable(false);
        ProgLogger.LOGGER.info("Calling ToS file reading function");
        textArea.append(getTOSText());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        ProgLogger.LOGGER.info("Created Text Area for ToS");

        //SCROLL PANE and TEXT PANEL
        JScrollPane sp = new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel textPanel = new JPanel();
        textPanel.add(sp);
        mainPanel.add(textPanel);
        ProgLogger.LOGGER.info("Created ToS Scroll Pane");

        //BACK JPANEL and BACK BUTTON
        JPanel backPanel = new JPanel();
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProgLogger.LOGGER.info("ToS Back Button Pressed");
                dialog.dispose();
            }
        });
        backPanel.add(back);
        mainPanel.add(backPanel);
        ProgLogger.LOGGER.info("ToS Back Button Created");


        dialog.add(mainPanel);
        dialog.setPreferredSize(new Dimension(700,580));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        ProgLogger.LOGGER.info("ToS dialog created");

    }

    /**
     * This function returns the ToS Text from a text file
     */
    private String getTOSText(){


        try {
            ProgLogger.LOGGER.info("Reading ToS txt file");
            return new String(Files.readAllBytes(Path.of("src/main/java/csi3471/bearMarket/AccountFiles/TermsAndConditions.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            ProgLogger.LOGGER.severe("ToS file was not able to be read properly!");
            return "";
        }
    }
}
