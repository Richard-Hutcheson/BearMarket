package csi3471.bearMarket.UserMarketPosting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

import csi3471.bearMarket.Logging.*;
import csi3471.bearMarket.MainScreenFiles.*;
import csi3471.bearMarket.ProductFiles.Product;

public class CreateMarketPostWindow extends JPanel implements ActionListener {

    protected static JFrame createMarketPostFrame;
    protected JLabel tempLabel[];
    protected JComboBox comboBox;
    protected JTextField tempTextField[];
    protected String[] productDescriptors = {"Product Name: ", "Category: ", "Description: ", "Quantity: ", "Price: "};
    protected Object categories[] = {"Health", "Kitchen", "Tools", "Entertainment", "Sports",
            "Home", "Clothing", "Electronics", "Education", "Music"};
    private boolean emptyErrFields[] = new boolean[]{false, false, false};
    protected JButton confirmChanges, cancelChanges;
    private File userFile;
    private FileOutputStream writePostStream;
    private GridBagConstraints c;
    private JLabel[] nonIntErrMsg = new JLabel[2];

    public static void createMarketPost() {
        createMarketPostFrame = new JFrame("Create Market Posting");
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CreateMarketPostWindow mainPanel = new CreateMarketPostWindow();

        createMarketPostFrame.setPreferredSize(new Dimension(600, 300));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
        createMarketPostFrame.setLocationRelativeTo(null);
        createMarketPostFrame.setVisible(true);
    }

    private String[] getStringArray() {
        return new String[]{tempTextField[0].getText(), (String)comboBox.getSelectedItem(),
                tempTextField[1].getText(), tempTextField[2].getText(), tempTextField[3].getText()};
    }

    public CreateMarketPostWindow() {
        setLayout(new GridBagLayout());

        c = new GridBagConstraints();
        tempLabel = new JLabel[productDescriptors.length];
        tempTextField = new JTextField[productDescriptors.length];
        int i;

        for (i = 0; i < productDescriptors.length; i++) {
            tempLabel[i] = new JLabel(productDescriptors[i]);
            c.gridx = 0;
            c.gridy = i;
            add(tempLabel[i], c);

            if (i == 1) {

                comboBox = new JComboBox(categories);
                c.gridx = 1;
                c.gridy = i;
                comboBox.setPreferredSize(new Dimension(150, 25));
                comboBox.addActionListener(this);
                add(comboBox, c);
            }
            else if (i < 1) {
                tempTextField[i] = new JTextField();
                tempTextField[i].setText("");
                c.gridx = 1;
                c.gridy = i;
                tempTextField[i].setPreferredSize(new Dimension(150, 20));
                add(tempTextField[i], c);
            }
            else {
                tempTextField[i -1] = new JTextField();
                tempTextField[i-1].setText("");
                c.gridx = 1;
                c.gridy = i;
                tempTextField[i-1].setPreferredSize(new Dimension(150, 20));
                add(tempTextField[i-1], c);
            }

        }

        i++;

        confirmChanges = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = i;
        confirmChanges.setPreferredSize(new Dimension(150, 20));
        add(confirmChanges, c);
        confirmChanges.addActionListener(this);

        cancelChanges = new JButton("Cancel");
        c.gridx = 1;
        c.gridy = i;
        cancelChanges.setPreferredSize(new Dimension(150, 20));
        add(cancelChanges, c);
        cancelChanges.addActionListener(this);

    }

    public void showErrFields() {
        c.gridx = 3;
        JLabel errMsg[] = new JLabel[emptyErrFields.length];
        for (int j = 0; j < emptyErrFields.length; j++) {
            if (errMsg[j] != null) {
                remove(errMsg[j]);
            }

            if (emptyErrFields[j]) {
                errMsg[j] = new JLabel("ERROR: Msg field empty");
                errMsg[j].setForeground(Color.RED);

                c.gridy = j;
                add(errMsg[j], c);
            }
        }


        createMarketPostFrame.revalidate();
        createMarketPostFrame.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == confirmChanges) {

            Path path = Paths.get("./users/" + MainScreen.currentAccount.getUsername() + ".csv");
            byte[] buffer = null;
            boolean hasEmptyFields = false;
            boolean hasNonIntFields = false;

            try {
                buffer = (Files.readAllBytes(path));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try {
                userFile = new File("./users/" + MainScreen.currentAccount.getUsername() + ".csv");
                writePostStream = new FileOutputStream(userFile, true);

                if (buffer[buffer.length - 1] != ',' && buffer[buffer.length - 1] != '\n') {
                    writePostStream.write(new byte[]{'\n'});
                }

                String userInputArray[] = getStringArray();

                for (int i = 0; i < emptyErrFields.length; i++) {
                    if (userInputArray[i].isEmpty()) {
                        emptyErrFields[i] = true;
                        hasEmptyFields = true;
                    }

                }

                if (!hasEmptyFields || !hasNonIntFields) {
                    Product tempProduct = ProductTable.productVector.get(ProductTable.productVector.size() - 1);
                    String tempArr[] = new String[7];
                    tempArr[0] = userInputArray[0]; //product name
                    tempArr[1] = userInputArray[1]; //category
                    tempArr[2] = userInputArray[2]; //desc
                    tempArr[3] = userInputArray[3]; //quantity
                    tempArr[4] = "5"; //no rating exists for it yet
                    tempArr[5] = userInputArray[4]; //price
                    tempArr[6] = String.valueOf(tempProduct.getID() + 1); //get greates id, and increment it

                    Product newProduct;

                    c.gridx = 3;
                    try {
                        if (nonIntErrMsg[0] != null) {
                            remove(nonIntErrMsg[0]);
                        }
                        Integer.parseInt(tempArr[3]);
                    } catch (NumberFormatException err) {
                        c.gridy = 3;
                        nonIntErrMsg[0] = new JLabel("ERROR: Field is not a whole number");
                        nonIntErrMsg[0].setForeground(Color.RED);
                        add(nonIntErrMsg[0], c);
                        hasNonIntFields = true;
                        ProgLogger.LOGGER.info("User inputted invalid quantity field");
                    }

                    try {
                        if (nonIntErrMsg[1] != null) {
                            remove(nonIntErrMsg[1]);
                        }
                        Double.parseDouble(tempArr[5]);
                    } catch (NumberFormatException err) {
                        c.gridy = 4;
                        nonIntErrMsg[1] = new JLabel("ERROR: Field is not a number");
                        nonIntErrMsg[1].setForeground(Color.RED);
                        add(nonIntErrMsg[1], c);
                        hasNonIntFields = true;
                        ProgLogger.LOGGER.info("User inputted invalid price field");
                    }

                    createMarketPostFrame.revalidate();
                    createMarketPostFrame.repaint();

                    if (!hasNonIntFields) {
                        newProduct = new Product(tempArr);
                        System.out.println(newProduct.toString());
                        writePostStream.write(Integer.toString(newProduct.getID()).getBytes());
                        //writePostStream.write(ID.getBytes());
                        writePostStream.write(new byte[]{','});

                        ProductTable.addItem(newProduct);

                        File productFile = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
                        FileOutputStream productFileStream = new FileOutputStream(productFile, true);

                        String tsvFormat = "\n";
                        for (int i = 0; i < tempArr.length; i++) {

                            // price parsing
                            if (i == 5) {
                                String subStrDec = tempArr[i];

                                if (subStrDec.contains(".")) {
                                    subStrDec = tempArr[i].substring(tempArr[i].indexOf("."));

                                    if (subStrDec.length() < 3) {
                                        if (subStrDec.length() == 2) {
                                            newProduct.setPrice(Double.parseDouble(tempArr[i] + "0"));
                                            tempArr[i] = "$" + newProduct.getPrice() + "0";
                                        } else if (subStrDec.length() == 1) {
                                            newProduct.setPrice(Double.parseDouble(tempArr[i] + "00"));
                                            tempArr[i] = "$" + newProduct.getPrice() + "0";
                                        }
                                    } else {
                                        newProduct.setPrice(Double.parseDouble(String.format("%.2f", Double.parseDouble(tempArr[i]))));
                                        tempArr[i] = "$" + newProduct.getPrice();
                                    }

                                } else {
                                    newProduct.setPrice(Double.parseDouble(tempArr[i]));
                                    tempArr[i] = "$" + newProduct.getPrice() + "0";
                                }
                            }

                            tsvFormat += (tempArr[i] + "\t");
                        }

                        try {
                            productFileStream.write(tsvFormat.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException ioErr) {
                            ioErr.printStackTrace();
                        }
                        productFileStream.close();
                    }
                }

                writePostStream.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (hasEmptyFields) {
                ProgLogger.LOGGER.info("User inputted invalid field");
                showErrFields();
            }
            else if (!hasNonIntFields) {
                ProgLogger.LOGGER.info("Market Post Successfully Created");
                createMarketPostFrame.dispose();
            }
        }
        else if (e.getSource() == cancelChanges) {
            ProgLogger.LOGGER.info("Market Post Creation Cancelled");
            createMarketPostFrame.dispose();
        }

    }
}
