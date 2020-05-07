package entity;

public enum Coin {
    PLN_5(5.0), PLN_2(2.0), PLN_1(1.0), PLN_05(0.5), PLN_02(0.2), PLN_01(0.1);

    double value;

    Coin(double value) {
        this.value = value;
    }
}
