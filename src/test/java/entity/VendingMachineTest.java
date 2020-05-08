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
        product1 = new Product("Coca Cola 0.25l", 1.5);
        product2 = new Product("Sprite 0.25l", 1.5);

        vendingMachine = new VendingMachine();
        vendingMachine.addProduct(1, product1, 10);
        vendingMachine.addProduct(2, product2, 5);

        vendingMachine.putCoinToTempCoinBox(Coin.PLN_1);
        vendingMachine.putCoinToTempCoinBox(Coin.PLN_2);
        vendingMachine.putCoinToTempCoinBox(Coin.PLN_05);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void schouldAddedProductTrue() throws Exception {
        //given
        //when
        vendingMachine.addProduct(15, product1, 5);
        //then
        assertTrue(vendingMachine.getShelfsMap().get(15).getProduct().equals(product1));
    }

    @Test
    void schouldAddedProductQuantityTrue() throws Exception {
        //given
        //when
        vendingMachine.addProduct(15, product1, 5);
        //then
        assertTrue(vendingMachine.getShelfsMap().get(15).getQuantity() == 5);
    }

    @Test
    void schouldAddedProductFalse() throws Exception {
        //given
        //when
        //then
        assertFalse(vendingMachine.getShelfsMap().get(16).getProduct() != null);
    }

    @Test
    void schouldThrowExceptionWhenWrongAddParametersProduct() throws Exception {
        //given
        //when
        try {
            vendingMachine.addProduct(vendingMachine.getQUANTITY_OF_SHELFS() + 1, product1, vendingMachine.getQUANTITY_OF_SHELFS() + 1);
        } catch (Exception e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

    @Test
    void schouldIsShelfNotEmptyTrue() {
        //given
        //when
        boolean isShelfNotEmpty = vendingMachine.isShelfNotEmpty(1);
        //then
        assertTrue(isShelfNotEmpty);
    }

    @Test
    void schouldIsShelfNotEmptyFalse() {
        //given
        //when
        boolean isShelfNotEmpty = vendingMachine.isShelfNotEmpty(10);
        //then
        assertFalse(isShelfNotEmpty);
    }

    @Test
    void schouldPutCoinToTempCoinBoxTrue() {
        //when
        //given
        //then
        assertTrue(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_1));
        assertTrue(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_2));
        assertTrue(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_05));
    }

    @Test
    void schouldPutCoinToTempCoinBoxFalse() {
        //when
        //given
        //then
        assertFalse(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_5));
    }

    @Test
    void schouldPutCoinsFromTempCoinBoxToCashInStockTrue() {
        //given
        //when
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        //then
        assertTrue(vendingMachine.getCashBox().getCashInStock().contains(Coin.PLN_1));
        assertTrue(vendingMachine.getCashBox().getCashInStock().contains(Coin.PLN_2));
        assertTrue(vendingMachine.getCashBox().getCashInStock().contains(Coin.PLN_05));
    }

    @Test
    void schouldPutCoinsFromTempCoinBoxToCashInStockFalse() {
        //given
        //when
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        //then
        assertFalse(vendingMachine.getCashBox().getCashInStock().contains(Coin.PLN_5));
    }

    @Test
    void schouldReturnCoinsFromTempCoinBoxTrue() {
        //given
        //when
        vendingMachine.returnCoinsFromTempCoinBox();
        //then
        assertTrue(!vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_1));
        assertTrue(!vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_2));
        assertTrue(!vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_05));
    }

    @Test
    void schouldReturnCoinsFromTempCoinBoxFalse() {
        //given
        //when
        vendingMachine.returnCoinsFromTempCoinBox();
        //then
        assertFalse(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_1));
        assertFalse(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_2));
        assertFalse(vendingMachine.getCashBox().getTempCoinBox().contains(Coin.PLN_05));
    }

    @Test
    void schouldSumCoinsFromTempCoinBoxTrue() {
        //given
        //when
        Double sum = vendingMachine.sumCoinsFromTempCoinBox();
        //then
        assertEquals(3.5, sum);
    }

    @Test
    void schouldSumCoinsFromTempCoinBoxFalse() {
        //given
        //when
        Double sum = vendingMachine.sumCoinsFromTempCoinBox();
        //then
        assertFalse(sum < 3.5 || sum < 3.5);
    }

    @Test
    void schouldSumCoinsFromCashInStockTrue() {
        //given
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        //when
        Double sum = vendingMachine.sumCoinsFromCashInStock();
        //then
        assertEquals(3.5, sum);
    }

    @Test
    void schouldSumCoinsFromCashInStockFalse() {
        //given
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        //when
        Double sum = vendingMachine.sumCoinsFromCashInStock();
        //then
        assertFalse(sum < 3.5 || sum < 3.5);
    }

    @Test
    void schouldDispenseProductTrue() {
        //given

        //when
        vendingMachine.dispenseProduct(1);
        vendingMachine.dispenseProduct(1);
        int newQuantity = vendingMachine.getShelfsMap().get(1).getQuantity();
        //then
        assertEquals(8,newQuantity);
    }

    @Test
    void schouldDispenseProductFalse() {
        //given
        //when
        vendingMachine.dispenseProduct(1);
        int newQuantity = vendingMachine.getShelfsMap().get(1).getQuantity();
        //then
        assertFalse(10==newQuantity);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}