package strategy;

import entity.Display;
import entity.VendingMachine;

public class EqualAmount implements Request{
    @Override
    public boolean productReleaseRequest(VendingMachine vendingMachine, int shelfNumber) {
        vendingMachine.dispenseProduct(shelfNumber);
        Display display = new Display();
        display.showMessage("Towar wydany. Brak reszty.");
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        return true;
    }
}
