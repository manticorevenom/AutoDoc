/**
 * AutoDoc.java
 * @author manticorevenom (Hunter Leary)
 * @version mk1.0 (2022.09.02)
 * @description This is the AutoDoc GUI form, the purpose of this form
 * is to provide an easy-to-understand and interactive GUI for users.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AutoDoc class
 * Contains the buttons/labels and other GUI elements that appear in the form.
 */
class AutoDoc extends JFrame{
    /**
     * @req SFREQ AutoDoc.Generate.Button
     * generateButton is a button on the form
     * to allow users to generate software documents
     */
    private JButton generateButton;
    private JPanel mainPanel;
    private JLabel TitleLabel;
    private final ImageIcon icon;

    public AutoDoc(){
        icon = new ImageIcon("resources/autodoc.png");
        generateButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(generateButton, TitleLabel.getText());
            }
        });
    }

    public static void main(String[] args) {
        AutoDoc doc = new AutoDoc();
        doc.setContentPane(doc.mainPanel);
        doc.setTitle("AutoDoc");
        doc.setIconImage(doc.icon.getImage());
        doc.setSize(500,500);
        doc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doc.pack();
        doc.setVisible(true);
    }
}
