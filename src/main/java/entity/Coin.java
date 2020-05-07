package entity;

import lombok.Data;


public enum Coin {
    PLN_5(5.0), PLN_2(2.0), PLN_1(1.0), PLN_05(0.5), PLN_02(0.2), PLN_01(0.1);

    public double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static void coinValidation(double coinValue) throws Exception {
        boolean isContains = false;
        for (Coin coin : Coin.values()) {
            if (coin.getValue() == coinValue) {
                isContains = true;
            }
        }
        if (!isContains) {
            throw new Exception("Niepoprawna moneta!");
        }
    }

    public static Coin getCoinByValue(double coinValue) {
        for (Coin coin : Coin.values()) {
            if (coin.getValue() == coinValue) {
                return coin;
            }
        }
        return null;
    }


}
