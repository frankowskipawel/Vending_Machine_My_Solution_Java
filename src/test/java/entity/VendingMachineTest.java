package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Product productOne;
    private Product productTwo;

    @BeforeEach
    void setUp() {
        productOne = new Product("Coca Cola 0,25l", 2.0);
        productTwo = new Product("Snickers 100g", 2.0);
        vendingMachine = new VendingMachine();
    }

    @Test
    public void schouldAddProductTrue() {
        //given
        //when
        vendingMachine.addProduct(productOne);
        //then
        assertTrue(vendingMachine.getProductsList().contains(productOne));
    }

    @Test
    public void schouldAddTwoProductTrue() {
        //given
        //when
        vendingMachine.addProduct(productOne);
        vendingMachine.addProduct(productTwo);
        //then
        assertTrue(vendingMachine.getProductsList().contains(productOne) &&
                vendingMachine.getProductsList().contains(productTwo));
    }

    @Test
    public void schouldAddProductFalse() {
        //given
        //when
        //then
        assertFalse(vendingMachine.getProductsList().contains(productOne));
    }


}