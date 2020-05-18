package entity;

import lombok.Data;
import strategy.EqualAmount;
import strategy.LargerAmount;
import strategy.Request;
import strategy.SmallerAmount;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class VendingMachine {

    private static VendingMachine vendingMachine;

    private final int QUANTITY_OF_SHELFS = 25;
    private final int MAX_QUANTITY_ON_ONE_SHELF = 10;

    private Map<Integer, Shelf> shelfsMap;
    private CashBox cashBox = new CashBox();
    private Request requestStrategy;

    public VendingMachine() {
        initializeShelfs();
    }

    public static VendingMachine getInstance() {
        if (vendingMachine == null) {
            vendingMachine = new VendingMachine();
            return vendingMachine;
        }
        return vendingMachine;
    }

    private void initializeShelfs() {
        this.shelfsMap = new LinkedHashMap<>();
        for (int i = 1; i <= QUANTITY_OF_SHELFS; i++) {
            shelfsMap.put(i, new Shelf(null, 0));
        }
    }

    public void addProduct(int shelfNumber, Product product, int quantity) throws Exception {
        Shelf shelf = new Shelf();
        product.productTypeValidation(shelfNumber, product);
        shelf.quantityValidation(shelfNumber, quantity);
        shelf.shelfNumberValidation(shelfNumber);
        Shelf shelfToWitchAdds = shelfsMap.get(shelfNumber);
        shelfToWitchAdds.setProduct(product);
        shelfToWitchAdds.setQuantity(shelfToWitchAdds.getQuantity() + quantity);
    }

    public boolean isShelfNotEmpty(int shelfNumber) {
        Product shelfToWitchAdds = shelfsMap.get(shelfNumber).getProduct();
        if (shelfToWitchAdds == null) {
            return false;
        } else {
            return true;
        }
    }

    public void putCoinToTempCoinBox(Coin coin) {
        cashBox.getTempCoinBox().add(coin);
    }

    public void putCoinsFromTempCoinBoxToCashInStock() {
        cashBox.getCashInStock().addAll(cashBox.getTempCoinBox());
        cashBox.getTempCoinBox().clear();
    }

    public void returnCoinsFromTempCoinBox() {
        System.out.println("Zwracem monety!");
        System.out.println(cashBox.getTempCoinBox());
        cashBox.getTempCoinBox().clear();
    }

    public double sumCoinsFromTempCoinBox() {
        double sum = 0;
        for (Coin coin : cashBox.getTempCoinBox()) {
            sum += coin.getValue();
        }
        return sum;
    }

    public double sumCoinsFromCashInStock() {
        double sum = 0;
        for (Coin coin : cashBox.getCashInStock()) {
            sum += coin.getValue();
        }
        return sum;
    }

    public boolean requestProduct(int shelfNumber) {
        double price = shelfsMap.get(shelfNumber).getProduct().getPrice();
        if (sumCoinsFromTempCoinBox() == price) {
            requestStrategy = new EqualAmount();
            requestStrategy.productReleaseRequest( shelfNumber);
            return true;
        }
        if (sumCoinsFromTempCoinBox() < price) {
            requestStrategy = new SmallerAmount();
            requestStrategy.productReleaseRequest(shelfNumber);
            return false;
        }
        if (sumCoinsFromTempCoinBox() > price) {
            requestStrategy = new LargerAmount();
            requestStrategy.productReleaseRequest(shelfNumber);
            return true;
        }
        requestStrategy.productReleaseRequest(shelfNumber);
        return false;
    }

    public void dispenseProduct(int shelfNumber) {
        Shelf shelf = shelfsMap.get(shelfNumber);
        int shelfQuantity = shelf.getQuantity();
        if (shelfQuantity <= 1) {
            shelf.setProduct(null);
        } else {
            shelf.setQuantity(shelf.getQuantity() - 1);
        }
    }
}
