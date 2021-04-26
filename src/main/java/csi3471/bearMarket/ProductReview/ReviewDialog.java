package csi3471.bearMarket.ProductReview;

import csi3471.bearMarket.ProductFiles.Product;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** Creates the review JDialog for a Product object
 * @author Richard Hutcheson
 */
public class ReviewDialog {
    /**
     * creates the review JDialog for a Product
     * @param product Product item the JDialog grabs information from and handles user input for
     */
    public void createDialog(Product product){
        final int WIDTH = 400, HEIGHT = 400;
        //JDIALOG
        JDialog dialog = new JDialog(new JFrame(), "");
        //JPANEL
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Reviews for '" + product.getProductName() + "'"));
        //TEXT AREA
        JTextArea textArea = new JTextArea(30, 24);
        textArea.setEditable(false);
        textArea.setLineWrap(true); //enable line wrapping by the word
        textArea.setWrapStyleWord(true);
        for (int i = 0; i < product.getReviews().size(); ++i){
            String text = "[" + product.getReviews().get(i).getUsername() + "](" + product.getReviews().get(i).getRating() +")- \"" + product.getReviews().get(i).getDescription() + "\"\n\n";
            textArea.append(text);
        }

        //SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ); //always show scroll bar
        scrollPane.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT - 100));
        //BACK BUTTON
        JButton backButton = new JButton("BACK");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(scrollPane);
        panel.add(backButton);
        dialog.add(panel);

        dialog.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        dialog.pack();// try to arrange window so that it fits preferred size
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
