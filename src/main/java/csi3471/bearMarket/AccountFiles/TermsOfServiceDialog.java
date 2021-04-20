package csi3471.bearMarket.AccountFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TermsOfServiceDialog {

    public void createDialog(){

        JPanel mainPanel = new JPanel();

        JDialog dialog = new JDialog(new JFrame("Terms of Service"));
        BoxLayout boxLayout = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        JTextArea textArea = new JTextArea(30,50);
        textArea.setEditable(false);
        textArea.append(getTOSText());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        JScrollPane sp = new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel textPanel = new JPanel();
        textPanel.add(sp);

        mainPanel.add(textPanel);

        JPanel backPanel = new JPanel();
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        backPanel.add(back);
        mainPanel.add(backPanel);

        dialog.add(mainPanel);
        dialog.setPreferredSize(new Dimension(700,580));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    private String getTOSText(){


        try {
            return new String(Files.readAllBytes(Path.of("src/main/java/csi3471/bearMarket/AccountFiles/TermsAndConditions.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
