package apps;

import controllers.PMController;
import entities.Product;
import enums.Category;
import models.Order;
import models.ProductManager;
import models.SaleLineItem;
import utilities.FileServices;
import java.util.List;

import java.io.File;

public class TestUnit {
    public static void main(String[] args) {
        Product p = new Product();
        p.setId("1234");
        p.setName("Pizza Meet Lover Big Size");
        p.setPrice(125);
        System.out.println(p);
        ProductManager productManager = new ProductManager();
        //menggunakan controller
        PMController pmController = new PMController(productManager);
        pmController.registerProduct(p);
        pmController.registerProduct(new Product("5555","Pizza Tuna",123,Category.PastaRice.toString(),null));
        Order order = new Order();
//        order.addItem(new SaleLineItem(pmController.findProductById("1234"),3));
//        order.addItem(new SaleLineItem(pmController.findProductById("5555"),4));
//        order.addItem(new SaleLineItem(pmController.getProductManager().findProductById("1234"),5));
//        order.checkOut();
        List<String> data = FileServices.readTxtFile("ProductList.txt");
        for(String s : data){
            System.out.println(s.split("/")[1]);
        }

        System.out.println();
    }
}
