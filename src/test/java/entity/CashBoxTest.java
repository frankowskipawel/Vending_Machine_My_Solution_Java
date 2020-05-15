package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashBoxTest {

    private CashBox cashBox;

    @BeforeEach
    void setUp() {
        cashBox = new CashBox();
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_02);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_1);
        cashBox.getCashInStock().add(Coin.PLN_2);
        cashBox.getCashInStock().add(Coin.PLN_5);


    }

    @Test
    public void schouldSpendTheRestTrue(){
        //given
        List<Coin> returnedCoins;
        //when
        returnedCoins = cashBox.spendTheRest(2.7);
        //then
        assertTrue(returnedCoins.contains(Coin.PLN_2));
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.contains(Coin.PLN_02));
        assertTrue(returnedCoins.size()==3);

    }

    @Test
    public void schouldSpendTheRestTrue2(){
        //given
        List<Coin> returnedCoins;
        //when
        returnedCoins = cashBox.spendTheRest(5.5);
        //then
        assertTrue(returnedCoins.contains(Coin.PLN_5));
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.size()==2);

    }

    @Test
    public void schouldSpendTheRestTrue3(){
        //given
        List<Coin> returnedCoins;
        //when
        returnedCoins = cashBox.spendTheRest(8.8);
        //then
        assertTrue(returnedCoins.contains(Coin.PLN_5));
        assertTrue(returnedCoins.contains(Coin.PLN_2));
        assertTrue(returnedCoins.contains(Coin.PLN_01));
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.contains(Coin.PLN_02));
        assertTrue(returnedCoins.contains(Coin.PLN_01));
        assertTrue(returnedCoins.size()==6);
    }

    @Test
    public void schouldSpendTheRestTrue4(){
        //given
        List<Coin> returnedCoins;
        CashBox cashBox = new CashBox();
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        //when
        returnedCoins = cashBox.spendTheRest(1.5);
        //then
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.size()==3);
    }

    @Test
    public void schouldSpendTheRestTrue5(){
        //given
        List<Coin> returnedCoins;
        CashBox cashBox = new CashBox();
        cashBox.getCashInStock().add(Coin.PLN_5);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        //when
        returnedCoins = cashBox.spendTheRest(7);
        //then
        assertTrue(returnedCoins.contains(Coin.PLN_5));
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.contains(Coin.PLN_01));
        assertTrue(!returnedCoins.contains(Coin.PLN_02));
        assertTrue(!returnedCoins.contains(Coin.PLN_2));
        assertTrue(returnedCoins.size()==9);
    }

    @Test
    public void schouldSpendTheRestTrue6(){
        //given
        List<Coin> returnedCoins;
        CashBox cashBox = new CashBox();
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_05);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        cashBox.getCashInStock().add(Coin.PLN_01);
        //when
        returnedCoins = cashBox.spendTheRest(2.5);
        //then
        assertTrue(!returnedCoins.contains(Coin.PLN_5));
        assertTrue(returnedCoins.contains(Coin.PLN_05));
        assertTrue(returnedCoins.contains(Coin.PLN_01));
        assertTrue(!returnedCoins.contains(Coin.PLN_02));
        assertTrue(!returnedCoins.contains(Coin.PLN_2));
        assertTrue(returnedCoins.size()==9);
    }

    @Test
    public void schouldSpendTheRestFalse2(){
        //given
        List<Coin> returnedCoins;
        //when
        returnedCoins = cashBox.spendTheRest(2.9);
        //then
        assertTrue(returnedCoins==null);
    }
}