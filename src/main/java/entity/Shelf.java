package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shelf {
    private Product product;
    private int quantity;

    public Shelf() {
    }

    public void shelfNumberValidation(VendingMachine vendingMachine, int shelfNumber) throws IndexOutOfBoundsException {
        if (shelfNumber < 1 || shelfNumber > vendingMachine.getQUANTITY_OF_SHELFS()) {
            throw new IndexOutOfBoundsException("Brak półki z takim numerem. Dostępne numery (1-" +
                    vendingMachine.getQUANTITY_OF_SHELFS() + ").");
        }
    }

    public void quantityValidation(VendingMachine vendingMachine, int shelfNumber, int quantity) throws IndexOutOfBoundsException {
        Shelf shelfToWitchAdds = vendingMachine.getShelfsMap().get(shelfNumber);
        if (quantity < 1) {
            throw new IndexOutOfBoundsException("Nieprawidłowa ilość.");
        }
        if (shelfToWitchAdds.getQuantity() + quantity > vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF()) {
            throw new IndexOutOfBoundsException("Brak miejsca na półce. Pozostało " +
                    (vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF() - shelfToWitchAdds.getQuantity()) +
                    " wolnych.");
        }
    }
}
