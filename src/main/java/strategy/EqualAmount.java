package strategy;

import entity.Display;


public class EqualAmount implements Request{
    @Override
    public boolean productReleaseRequest(int shelfNumber) {
        vendingMachine.dispenseProduct(shelfNumber);
        Display display = new Display();
        display.showMessage("Towar wydany. Brak reszty.");
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        return true;
    }
}
