package views;

import entities.Product;
import utilities.ConvertFileToByte;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private Product product;

    public RegisterDialog(MainFrame parent, Product product){
        this(parent);
        this.product = product;
        setProduct(product);
    }
        private void setProduct(Product product){
            idInput.setEnabled(false);
            idInput.setText(product.getId());
            nameInput.setText(product.getName());
            cbCategory.getModel().setSelectedItem(product.getCategory());
            priceInput.setText(""+product.getPrice());
            Image image = new ImageIcon(product.getImage()).getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
            buttonOK.setText("Edit");
        }

    public RegisterDialog(MainFrame parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setLocation(parent.getX()+100,parent.getY()+100);
        setSize(340,420);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> {
            if(e.getActionCommand().equals("OK")) onOK();
            if(e.getActionCommand().equals("Edit")){
                alterProduct(product);
            }
        });

        buttonCancel.addActionListener( e-> onCancel());
        imageLabel.setSize(150,150);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
            imageFile = chooser.getSelectedFile();
            //scale image
            //imageLabel.setIcon(new ImageIcon(filename));
            imageLabel.setSize(150,150);
            imageLabel.setBackground(Color.WHITE);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        });
    }

    private void alterProduct(Product product){
        parent.getPmController().removeProductById(product.getId());
        onOK();
    }
    private void onOK() {
        // add your code here
        Product product = new Product();
        product.setId(idInput.getText());
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setCategory(cbCategory.getSelectedItem().toString());
        try{
            System.out.println(imageFile);
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
