package csi3471.bearMarket;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMarketPostWindow implements ActionListener {

    JFrame frame;

    public EditMarketPostWindow() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        String productDescrptors[] = {"Product Name: ",
                "Category: ", "Description: ",
                "Quantity: ", "Rating: ", "Price: ", "ID: "};

        for (int i = 0; i < productDescrptors.length; i++) {
            JLabel tempLabel = new JLabel(productDescrptors[i]);
            c.gridx = 0;
            c.gridy = i;
            frame.add(tempLabel, c);

            if (i == 1) {
                Object categories[] = {"Health", "Kitchen", "Tools", "Entertainment", "Sports",
                        "Home", "Clothing", "Electronics", "Education", "Music"};
                JComboBox comboBox = new JComboBox(categories);
                c.gridx = 1;
                c.gridy = i;
                comboBox.setPreferredSize(new Dimension(150, 25));
                comboBox.addActionListener(this);
                frame.add(comboBox, c);
            }
            else {
                JTextField tempTextField = new JTextField();
                c.gridx = 1;
                c.gridy = i;
                tempTextField.setPreferredSize(new Dimension(150, 20));
                frame.add(tempTextField, c);
            }

        }

        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
