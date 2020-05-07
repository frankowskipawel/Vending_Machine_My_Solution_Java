package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Exception exception;
    private Product productOne;
    private Product productTwo;
    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() throws Exception {
        vendingMachine = new VendingMachine();
        productOne = new Product("Coca Cola 0.25l", 1.5);;
        productTwo = new Product("Sprite 0.25l", 1.5);
        vendingMachine.addProduct(1, productOne, 4);
        vendingMachine.addProduct(2, productTwo, 5);
    }

    @Test
    void schouldNoThrowExceptionWhenProductTypeValidationTrue() {
        //given
//        vendingMachine.getShelfsMap().get(1).setProduct(productOne);
        //when
        try {
            productOne.productTypeValidation(vendingMachine, 1, productOne);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductTypeValidationFalse() {
        //given
//        vendingMachine.getShelfsMap().get(1).setProduct(productTwo);
        //when
        try {
            productOne.productTypeValidation(vendingMachine, 1, productOne);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductIsNullTypeValidationTrue() {
        //given
        //when
        try {
            productOne.productTypeValidation(vendingMachine, 1, productOne);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

}