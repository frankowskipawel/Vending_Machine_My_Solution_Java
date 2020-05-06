package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Product productOne;
    private Product productTwo;
    private Exception exception;

    @BeforeEach
    void setUp() {
        exception = null;
        productOne = new Product("Coca Cola 0,25l", 2.0);
        productTwo = new Product("Snickers 100g", 1.5);
        vendingMachine = new VendingMachine();
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
            vendingMachine.quantityValidation(1, 1);
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
            vendingMachine.quantityValidation(1, vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF());
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
        vendingMachine.getShelfsMap().get(1).setProduct(productOne);
        //when
        try {
            vendingMachine.productTypeValidation(1, productOne);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldNoThrowExceptionWhenProductTypeValidationFalse() {
        //given
        vendingMachine.getShelfsMap().get(1).setProduct(productTwo);
        //when
        try {
            vendingMachine.productTypeValidation(1, productOne);
        } catch (InvalidParameterException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }
}