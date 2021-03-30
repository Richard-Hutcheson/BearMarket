package csi3471.bearMarket.UserMarketPosting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMarketPostWindow extends JPanel implements ActionListener {

    protected static JFrame createMarketPostFrame;
    protected JLabel tempLabel[];
    protected JComboBox comboBox;
    protected JTextField tempTextField[];
    protected String[] productDescriptors = {"Product Name: ", "Category: ", "Description: ", "Quantity: ", "Rating: ", "Price: "};

    public static void createMarketPost() {
        createMarketPostFrame = new JFrame();
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CreateMarketPostWindow mainPanel = new CreateMarketPostWindow();

        createMarketPostFrame.setPreferredSize(new Dimension(400, 200));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
        createMarketPostFrame.setVisible(true);
    }

    public CreateMarketPostWindow() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        tempLabel = new JLabel[productDescriptors.length];
        tempTextField = new JTextField[productDescriptors.length];

        for (int i = 0; i < productDescriptors.length; i++) {
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
            else {
                tempTextField[i] = new JTextField();
                c.gridx = 1;
                c.gridy = i;
                tempTextField[i].setPreferredSize(new Dimension(150, 20));
                add(tempTextField[i], c);
            }

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
