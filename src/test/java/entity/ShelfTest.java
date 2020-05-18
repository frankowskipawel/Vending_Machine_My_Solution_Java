package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    Exception exception;
    private Shelf shelf;
    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        shelf = new Shelf();
        vendingMachine = new VendingMachine();
    }

    @Test
    void schouldNoThrowExceptionWhenShelfFirstNumberValidationTrue() {
        //given
        IndexOutOfBoundsException exception = null;
        //when
        try {
            shelf.shelfNumberValidation(1);
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
            shelf.shelfNumberValidation( vendingMachine.getQUANTITY_OF_SHELFS());
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
            shelf.shelfNumberValidation(0);
            shelf.shelfNumberValidation(-1);
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
            shelf.shelfNumberValidation(vendingMachine.getQUANTITY_OF_SHELFS() + 1);
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
            shelf.quantityValidation( 2, 1);
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
            shelf.quantityValidation( 8, vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF());
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
            shelf.quantityValidation( 1, 0);
            shelf.quantityValidation( 1, -1);
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
            shelf.quantityValidation( 1, vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF() + 1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }
}