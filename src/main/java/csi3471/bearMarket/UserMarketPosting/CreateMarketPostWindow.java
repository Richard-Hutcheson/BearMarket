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
import java.util.Hashtable;
import java.util.Scanner;

import csi3471.bearMarket.Logging.*;
import csi3471.bearMarket.MainScreenFiles.*;
import csi3471.bearMarket.ProductFiles.Product;

public class CreateMarketPostWindow extends JPanel implements ActionListener {

    protected static JFrame createMarketPostFrame;
    protected JLabel tempLabel[];
    protected JComboBox comboBox;
    protected JTextField tempTextField[];
    protected String[] productDescriptors = {"Product Name: ", "Category: ", "Description: ", "Quantity: ", "Price: "};
    private boolean errFields[] = new boolean[]{false, false, false, false, false};
    protected JButton confirmChanges, cancelChanges;
    private File userFile;
    private FileOutputStream writePostStream;
    private Product newProduct;
    private GridBagConstraints c;

    public static void createMarketPost() {
        createMarketPostFrame = new JFrame();
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CreateMarketPostWindow mainPanel = new CreateMarketPostWindow();

        createMarketPostFrame.setPreferredSize(new Dimension(500, 250));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
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
                Object categories[] = {"Health", "Kitchen", "Tools", "Entertainment", "Sports",
                        "Home", "Clothing", "Electronics", "Education", "Music"};
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

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == confirmChanges) {

            Path path = Paths.get("./users/" + MainScreen.currentAccount.getUsername() + ".csv");
            byte[] buffer = null;

            try {
                buffer = (Files.readAllBytes(path));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try {
                userFile = new File("./users/" + MainScreen.currentAccount.getUsername() + ".csv");
                writePostStream = new FileOutputStream(userFile, true);

                if (buffer[buffer.length - 1] != ',') {
                    writePostStream.write(new byte[]{'\n'});
                }

                String userInputArray[] = getStringArray();

                boolean hasEmptyFields = false;

                for (int i = 0; i < userInputArray.length; i++) {
                    if (userInputArray[i].isEmpty()) {
                        errFields[i] = true;
                        hasEmptyFields = true;
                    }
                }

                if (!hasEmptyFields) {
                    newProduct = new Product();
                    newProduct.setProductName(getStringArray()[0]);
                    newProduct.setCategory(getStringArray()[1]);
                    newProduct.setDescription(getStringArray()[2]);
                    newProduct.setQuantity(Integer.parseInt(getStringArray()[3]));
                    newProduct.setPrice(Double.parseDouble(getStringArray()[4]));
                    newProduct.setID(newProduct.hashCode());

                    String ID = Integer.toString(newProduct.getID());
                    System.out.println(ID);

                    writePostStream.write(ID.getBytes());
                    writePostStream.write(new byte[]{','});

                    ProductTable.addItem(newProduct);
                }



            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


            createMarketPostFrame.dispose();
        }
        else if (e.getSource() == cancelChanges) {
            createMarketPostFrame.dispose();
        }

    }
}
