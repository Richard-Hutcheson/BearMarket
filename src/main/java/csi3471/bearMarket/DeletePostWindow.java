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
