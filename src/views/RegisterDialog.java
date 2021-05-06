package views;

import entities.Product;
import utilities.ConvertFileToByte;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class RegisterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idInput;
    private JTextField nameInput;
    private JTextField priceInput;
    private JComboBox cbCategory;
    private JButton uploadButton;
    private JLabel imageLabel;
    private File imageFile = null;
    private final MainFrame parent;

    public RegisterDialog(MainFrame parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setLocation(parent.getX()+100,parent.getY()+100);
        setSize(340,420);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        imageLabel.setSize(150,150);
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
        uploadButton.addActionListener(e ->{
            JFileChooser chooser = new JFileChooser();
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("PDO file system","pdo");
            //chooser.setFileFilter(filter);
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            //scale image
            //imageLabel.setIcon(new ImageIcon(filename));
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        });
    }

    private void onOK() {
        // add your code here
        Product product = new Product();
        product.setId(idInput.getText());
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setCategory(cbCategory.getSelectedItem().toString());
        try{
            if (imageFile == null){
                imageFile = new File(getClass().getResource("/images/img_nopic.png").getFile());
            }
            byte[] byteImage = ConvertFileToByte.getBytes(imageFile);
            product.setImage(byteImage);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(product !=null){
            parent.getPmController().registerProduct(product);
            System.out.println(product);
            parent.sendMessage("Product id "+idInput.getText()+" is added to PM Collection");
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
