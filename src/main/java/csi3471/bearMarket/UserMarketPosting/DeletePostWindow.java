package csi3471.bearMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePostWindow extends JPanel implements ActionListener {

    protected static JFrame frame;

    public static void DeletePostWindow() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DeletePostWindow mainPanel = new DeletePostWindow();

        frame.setContentPane(mainPanel);

        frame.setVisible(true);
        frame.pack();
    }

    public DeletePostWindow() {

        // save the option result
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete?", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            // WIP
        }
        else if (dialogResult == JOptionPane.NO_OPTION) {
            // WIP
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
