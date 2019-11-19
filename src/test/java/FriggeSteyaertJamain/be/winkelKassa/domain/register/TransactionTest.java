package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {
/*
    private Product product1;
    private Product product2;
    private Product product11;
    private Transaction transaction;


    @Before
    public void setUp(){
        product1 = new Product(1, "choco", 2.99, new Btw(1, 21), "chocolate paste", "back of the store", "store1", null);
        product11 = new Product(1, "choco", 2.99, new Btw(1, 21), "chocolate paste", "back of the store", "store1", null);
        product2 = new Product(2, "choco", 2.99, new Btw(1, 21), "chocolate paste", "back of the store", "store1", null);
        transaction = new Transaction(1,  "store1", null, null);
    }

    @Test
    public void addFirstProduct() {
        transaction.addProduct(product1);
        assertEquals("is one product added to the products list", 1, transaction.getProducts().size());
        assertEquals("the frequency table has been incremented on the right key", 1, (int) transaction.getProductsFrequency().get(product1.getId()));
    }

    @Test
    public void addTwoSameProducts(){
        transaction.addProduct(product1);
        transaction.addProduct(product11);
        assertEquals("is one product added to the products list", 1, transaction.getProducts().size());
        assertEquals("the frequency table has been incremented on the right key", 2, (int) transaction.getProductsFrequency().get(product1.getId()));
    }

    @Test
    public void addTwoDifferentProducts(){
        transaction.addProduct(product1);
        transaction.addProduct(product2);
        assertEquals("is one product added to the products list", 2, transaction.getProducts().size());
        assertEquals("the frequency table has been incremented on the right key", 1, (int) transaction.getProductsFrequency().get(product1.getId()));
        assertEquals("the frequency table has been incremented on the right key", 1, (int) transaction.getProductsFrequency().get(product2.getId()));

    }

    @Test
    public void removeOneProduct() {
        transaction.addProduct(product2);
        transaction.removeProduct(product2);
        assertEquals("is one product added to the products list", 0, transaction.getProducts().size());
        assertTrue("frequency table is empty", transaction.getProductsFrequency().isEmpty());
    }

    @Test
    public void removeTwoSameProduct(){
        transaction.addProduct(product2);
        transaction.addProduct(product2);
        transaction.removeProduct(product2);
        transaction.removeProduct(product2);
        assertTrue("products is empty", transaction.getProducts().isEmpty());
        assertTrue("frequency table is empty", transaction.getProductsFrequency().isEmpty());
    }

    @Test
    public void removeAll(){
        transaction.addProduct(product2);
        transaction.addProduct(product1);
        transaction.addProduct(product11);
        transaction.addProduct(product2);
        transaction.addProduct(product2);
        transaction.addProduct(product1);
        transaction.addProduct(product11);
        transaction.addProduct(product2);
        transaction.addProduct(product1);
        transaction.addProduct(product11);
        transaction.removeAllProducts();
        assertTrue("products is empty", transaction.getProducts().isEmpty());
    }


 */
}