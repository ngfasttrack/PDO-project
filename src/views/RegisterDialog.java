package views;

import entities.Product;

import javax.swing.*;
import java.awt.event.*;

public class RegisterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idInput;
    private JTextField nameInput;
    private JTextField priceInput;
    private final MainFrame parent;

    public RegisterDialog(MainFrame parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener( e-> onCancel());

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
        Product product = new Product();
        product.setId(idInput.getText());
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        if(product !=null){
            parent.getPmController().registerProduct(product);
            parent.sendMessage("Product id "+idInput.getText()+" is added to PM Collection");
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
