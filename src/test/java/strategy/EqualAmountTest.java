package strategy;

import entity.Coin;
import entity.Product;
import entity.VendingMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EqualAmountTest {

    @Test
    void schouldProductReleaseRequestTrue() throws Exception {
        //given
        Product product1 = new Product("Coca Cola 0.25l", 1.5);
        Product product2 = new Product("Sprite 0.25l", 1.5);

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addProduct(1, product1, 10);
        vendingMachine.addProduct(2, product2, 5);

        vendingMachine.putCoinToTempCoinBox(Coin.PLN_1);
        vendingMachine.putCoinToTempCoinBox(Coin.PLN_05);
        EqualAmount equalAmount = new EqualAmount();

        //when
        equalAmount.productReleaseRequest(1);
        //then
        assertEquals(9,vendingMachine.getShelfsMap().get(1).getQuantity());

    }
}