package csi3471.bearMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePostWindow implements ActionListener {

    JFrame frame;


    public DeletePostWindow() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

//        GridBagConstraints c = new GridBagConstraints();
//
//        JLabel prompt = new JLabel("Are you sure?");
//        c.gridx = 1;
//        c.gridy = 0;
//        frame.add(prompt, c);
//
//        JButton confirmYes = new JButton("Yes");
//        c.gridx = 0;
//        c.gridy = 1;
//        confirmYes.setPreferredSize(new Dimension(150, 50));
//        frame.add(confirmYes, c);
//
//        JButton confirmNo = new JButton("Cancel");
//        c.gridx = 2;
//        c.gridy = 1;
//        confirmNo.setPreferredSize(new Dimension(150, 50));
//        frame.add(confirmNo, c);

        // save the option result
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete?", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            // WIP
        }
        else if (dialogResult == JOptionPane.NO_OPTION) {
            // WIP
        }

        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
