package views;

import entities.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailProductDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel productNameLabel;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JPanel detailPanel;
    private JLabel idLabel;
    private JLabel priceLabel;
    private JLabel categoryLabel;
    private Product product;

    public DetailProductDialog(Product product) {
        this.product = product;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(this);
        displayData();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void displayData(){
        System.out.println(product.getImage());
        byte[] image = product.getImage();
        //ImageIcon imgIcon = new ImageIcon(new ImageIcon(image).getImage());
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imgIcon);
        idLabel.setText(""+product.getId());
        nameLabel.setText(""+product.getName());
        priceLabel.setText(""+product.getPrice());
        categoryLabel.setText(""+product.getCategory());
    }
}
