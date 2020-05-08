package strategy;

import entity.Display;
import entity.VendingMachine;

public class SmallerAmount implements Request{
    @Override
    public void productReleaseRequest(VendingMachine vendingMachine, int shelfNumber) {
        Display display = new Display();
        display.showMessage("Nie wystarczające środki. Uzupełnij lub naciśnij ANULUJ w celu zwrotu pieniędzy");
    }
}
