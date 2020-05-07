package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    Exception exception;

    @Test
    void schouldNoThrowExceptionUnknowCoinTrue() {
        //given
        double coinValue5 = 5;
        double coinValue2 = 2;
        double coinValue1 = 1;
        double coinValue05 = 0.5;
        double coinValue02 = 0.2;
        double coinValue01 = 0.1;
        //when
        try {
            Coin.coinValidation(coinValue5);
            Coin.coinValidation(coinValue2);
            Coin.coinValidation(coinValue1);
            Coin.coinValidation(coinValue05);
            Coin.coinValidation(coinValue02);
            Coin.coinValidation(coinValue01);
        } catch (Exception e) {
            exception = e;
        }
        //then
        assertNull(exception);
    }

    @Test
    void schouldThrowExceptionUnknowCoinFalse() {
        //given
        double coinValue = 3;
        //when
        try {
            Coin.coinValidation(coinValue);

        } catch (Exception e) {
            exception = e;
        }
        //then
        assertNotNull(exception);
    }

}