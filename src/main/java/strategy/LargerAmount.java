package strategy;

import entity.Coin;
import entity.Display;
import entity.VendingMachine;

import java.util.List;

public class LargerAmount implements Request {
    @Override
    public boolean productReleaseRequest(VendingMachine vendingMachine, int shelfNumber) {
        Double price = vendingMachine.getShelfsMap().get(shelfNumber).getProduct().getPrice();
        Double sumOfCoinInTempCashBox = vendingMachine.getCashBox().getAmountInTempCoinBox();
        List<Coin> restCoinList = vendingMachine.getCashBox().spendTheRest(sumOfCoinInTempCashBox - price);
        Display display = new Display();

        if (restCoinList == null) {
            display.showMessage("Brak możliwości wydania reszty. ");
            vendingMachine.returnCoinsFromTempCoinBox();
            return false;
        } else {
            display.showMessage("Oto twoja reszta / monety " + restCoinList);
            vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
            return true;
        }
    }
}
