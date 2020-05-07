package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CashBox {
    private List<Coin> cashInStock = new ArrayList<>();
    private List<Coin> tempCoinBox= new ArrayList<>();


}
