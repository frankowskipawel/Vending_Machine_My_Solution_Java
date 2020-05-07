package entity;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.InvalidParameterException;

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
    private Product product3;
    private Product product4;
    private Product product5;
    private Product product6;
    private Product product7;
    private Product product8;
    private Product product9;
    private Product product10;

    @BeforeEach
    void setUp() throws Exception {
        exception = null;
        product1 = new Product("Coca Cola 0.25l",1.5);
        product2 = new Product("Sprite 0.25l", 1.5);
        product3 = new Product("Fanta 0.25l", 1.5);
        product4 = new Product("Snickers 100g", 1);
        product5 = new Product("Prince Polo 50g", 1.2);
        product6 = new Product("Mineral Water 500ml", 1.3);
        product7 = new Product("Rogal 7-days", 2);
        product8 = new Product("Sok pomarańczowy Cappy 0,2l",2.5);
        product9 = new Product("Sok Jabłkowy Cappy 0,2l",2.5);
        product10 = new Product("Baton Twix 80g", 1);

        vendingMachine = new VendingMachine();
        vendingMachine.addProduct(1,product1,10);
        vendingMachine.addProduct(2,product2,5);
        vendingMachine.addProduct(3, product3,9);
        vendingMachine.addProduct(4, product4,10);
        vendingMachine.addProduct(5, product5,8);
        vendingMachine.addProduct(6, product6,10);
        vendingMachine.addProduct(7, product7,10);
        vendingMachine.addProduct(20, product8,9);
        vendingMachine.addProduct(21, product9,10);
        vendingMachine.addProduct(25, product10,10);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void schouldNoThrowExceptionWhenShelfFirstNumberValidationTrue() {
        //given
        IndexOutOfBoundsException exception = null;
        //when
        try {
            vendingMachine.shelfNumberValidation(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenShelfLastNumberValidationTrue() {
        //given
        //when
        try {
            vendingMachine.shelfNumberValidation(vendingMachine.getQUANTITY_OF_SHELFS());
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldThrowExceptionWhenShelfNumberValidationLeftSideRangeFalse() {
        //given
        //when
        try {
            vendingMachine.shelfNumberValidation(0);
            vendingMachine.shelfNumberValidation(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldThrowExceptionWhenShelfNumberValidationRightSideRangeFalse() {
        //given
        //when
        try {
            vendingMachine.shelfNumberValidation(vendingMachine.getQUANTITY_OF_SHELFS() + 1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenQuantityValidationLeftSideRangeTrue() {
        //given
        //when
        try {
            vendingMachine.quantityValidation(2, 1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenQuantityValidationRightSideRangeTrue() {
        //given
        //when
        try {
            vendingMachine.quantityValidation(8, vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF());
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenQuantityValidationLeftSideRangeFalse() {
        //given
        //when
        try {
            vendingMachine.quantityValidation(1, 0);
            vendingMachine.quantityValidation(1, -1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenQuantityValidationRightSideRangeFalse() {
        //given
        //when
        try {
            vendingMachine.quantityValidation(1, vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF() + 1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductTypeValidationTrue() {
        //given
        vendingMachine.getShelfsMap().get(1).setProduct(product1);
        //when
        try {
            vendingMachine.productTypeValidation(1, product1);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductTypeValidationFalse() {
        //given
        vendingMachine.getShelfsMap().get(1).setProduct(product2);
        //when
        try {
            vendingMachine.productTypeValidation(1, product1);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductIsNullTypeValidationTrue() {
        //given
        //when
        try {
            vendingMachine.productTypeValidation(1, product1);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
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