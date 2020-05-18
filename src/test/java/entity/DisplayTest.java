package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {
    private VendingMachine vendingMachine;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Product product1;
    private Product product2;
    Display display;

    @BeforeEach
    void setUp() throws Exception {
        product1 = new Product("Coca Cola 0.25l",1.5);
        product2 = new Product("Sprite 0.25l", 1.5);

        vendingMachine = new VendingMachine();
        vendingMachine.addProduct(1,product1,10);
        vendingMachine.addProduct(2,product2,5);
        display = new Display();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @Test
    void schouldShowAllProductsOnDisplay() {
        //given
        //when
        display.showAllProducts(vendingMachine);
        //then
        assertTrue(outContent.toString().contains("Coca Cola 0.25l"));
        assertTrue(outContent.toString().contains("Sprite 0.25l"));
    }

    @Test
    void schouldShowMessageTrue(){
        //given
        //when
        display.showMessage("Wybierz produkt");
        //then
        assertTrue(outContent.toString().contains("Wybierz produkt"));
    }
}