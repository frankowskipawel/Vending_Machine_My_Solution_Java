package entity;


import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Exception exception;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() throws Exception {
        exception = null;
        product1 = new Product("Coca Cola 0.25l",1.5);
        product2 = new Product("Sprite 0.25l", 1.5);

        vendingMachine = new VendingMachine();
        vendingMachine.addProduct(1,product1,10);
        vendingMachine.addProduct(2,product2,5);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void schouldAddedProductTrue() throws Exception {
        //given
        //when
        vendingMachine.addProduct(15, product1,5);
        //then
        assertTrue(vendingMachine.getShelfsMap().get(15).getProduct().equals(product1));
    }

    @Test
    void schouldAddedProductQuantityTrue() throws Exception {
        //given
        //when
        vendingMachine.addProduct(15, product1,5);
        //then
        assertTrue(vendingMachine.getShelfsMap().get(15).getQuantity()==5);
    }

    @Test
    void schouldAddedProductFalse() throws Exception {
        //given
        //when
        //then
        assertFalse(vendingMachine.getShelfsMap().get(16).getProduct()!=null);
    }

    @Test
    void schouldThrowExceptionWhenWrongAddParametersProduct() throws Exception {
        //given
        //when
        try {
            vendingMachine.addProduct(vendingMachine.getQUANTITY_OF_SHELFS()+1, product1, vendingMachine.getQUANTITY_OF_SHELFS()+1);
        } catch (Exception e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}