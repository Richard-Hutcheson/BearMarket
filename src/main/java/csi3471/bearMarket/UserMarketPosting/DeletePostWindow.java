package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class DeletePostWindow extends JPanel implements ActionListener {

    protected static JFrame frame;

    public static void DeletePostWindow(int row) throws IOException {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DeletePostWindow mainPanel = new DeletePostWindow(row);

        frame.setContentPane(mainPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DeletePostWindow(int row) throws IOException {

        // save the option result
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete?", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            File productSearch = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
            Scanner in = new Scanner(productSearch);
            PrintWriter deleteLine = new PrintWriter(productSearch);

            int count = 0;
            while (in.hasNextLine() && count < (row - 1)) {
                count++;
            }

            deleteLine.write("");

            in.close();
        }
        else if (dialogResult == JOptionPane.NO_OPTION) {
            // WIP
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
