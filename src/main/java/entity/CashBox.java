package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class CashBox {
    private List<Coin> cashInStock = new ArrayList<>();
    private List<Coin> tempCoinBox = new ArrayList<>();


    public List<Coin> spendTheRest(double a) {

        int amount = (int)(a*100);

        List<Coin> coinList = new LinkedList<>();

        int quantity5 = (int) (amount / 500);
        if (quantity5 > 0 && getQuantityCoin(Coin.PLN_5)>=quantity5) {
            amount=amount-(500*quantity5);
            addCoinsToList(coinList,Coin.PLN_5,quantity5);
        } else if (quantity5 >0 && getQuantityCoin(Coin.PLN_5)<quantity5 && getQuantityCoin(Coin.PLN_5)!=0){
            amount=amount-(500*getQuantityCoin(Coin.PLN_5));
            addCoinsToList(coinList,Coin.PLN_5,getQuantityCoin(Coin.PLN_5));
        }

        int quantity2 = (int) (amount / 200);
        if (quantity2 > 0 && getQuantityCoin(Coin.PLN_2)>=quantity2) {
            amount=amount-(200*quantity2);
            addCoinsToList(coinList,Coin.PLN_2,quantity2);
        } else if (quantity2 >0 && getQuantityCoin(Coin.PLN_2)<quantity2 && getQuantityCoin(Coin.PLN_2)!=0){
            amount=amount-(200*getQuantityCoin(Coin.PLN_2));
            addCoinsToList(coinList,Coin.PLN_2,getQuantityCoin(Coin.PLN_2));
        }

        int quantity1 = (int) (amount / 100);
        if (quantity1 > 0 && getQuantityCoin(Coin.PLN_1)>=quantity1) {
            amount=amount-(100*quantity1);
            addCoinsToList(coinList,Coin.PLN_1,quantity1);
        } else if (quantity1 >0 && getQuantityCoin(Coin.PLN_1)<quantity1 && getQuantityCoin(Coin.PLN_1)!=0){
            amount=amount-(100*getQuantityCoin(Coin.PLN_1));
            addCoinsToList(coinList,Coin.PLN_1,getQuantityCoin(Coin.PLN_1));
        }

        int quantity05 = (int) (amount / 50);
        if (quantity05 > 0 && getQuantityCoin(Coin.PLN_05)>=quantity05) {
            amount=amount-(50*quantity05);
            addCoinsToList(coinList,Coin.PLN_05,quantity05);
        } else if (quantity05 >0 && getQuantityCoin(Coin.PLN_05)<quantity05 && getQuantityCoin(Coin.PLN_05)!=0){
            amount=amount-(50*getQuantityCoin(Coin.PLN_05));
            addCoinsToList(coinList,Coin.PLN_05,getQuantityCoin(Coin.PLN_05));
        }

        int quantity02 = (int) (amount / 20);
        if (quantity02 > 0 && getQuantityCoin(Coin.PLN_02)>=quantity02) {
            amount=amount-(20*quantity02);
            addCoinsToList(coinList,Coin.PLN_02,quantity02);
        } else if (quantity02 >0 && getQuantityCoin(Coin.PLN_02)<quantity02 && getQuantityCoin(Coin.PLN_02)!=0){
            amount=amount-(20*getQuantityCoin(Coin.PLN_02));
            addCoinsToList(coinList,Coin.PLN_02,getQuantityCoin(Coin.PLN_02));
        }

        int quantity01 = (int) (amount / 10);
        if (quantity01 > 0 && getQuantityCoin(Coin.PLN_01)>=quantity01) {
            amount=amount-(10*quantity01);
            addCoinsToList(coinList,Coin.PLN_01,quantity01);
        } else if (quantity01 >0 && getQuantityCoin(Coin.PLN_01)<quantity01 && getQuantityCoin(Coin.PLN_01)!=0){
            amount=amount-(10*getQuantityCoin(Coin.PLN_01));
            addCoinsToList(coinList,Coin.PLN_01,getQuantityCoin(Coin.PLN_01));
        }

        if (amount==0){return coinList;}
        else {return null;}
    }

    public int getQuantityCoin(Coin coin) {
        int count = 0;
        for (Coin coinInStock : cashInStock) {
            if (coinInStock == coin) {
                count++;
            }
        }
        return count;
    }

    private void addCoinsToList(List<Coin> list, Coin coin, int quantity){
        for (int i = 0; i < quantity ; i++) {
            list.add(coin);
        }




    }
}
