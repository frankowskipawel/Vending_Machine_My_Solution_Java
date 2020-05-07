package entity;

import java.security.InvalidParameterException;

public class Validators {

    public void productTypeValidation(VendingMachine vendingMachine, int shelfNumber, Product product) throws InvalidParameterException {
        Shelf shelfToWitchAdds = vendingMachine.getShelfsMap().get(shelfNumber);
        if (shelfToWitchAdds.getProduct() != null && !shelfToWitchAdds.getProduct().getName().equals(product.getName())) {
            throw new InvalidParameterException("Na półce jest już produkt innego typu. Nie można mieszać produktów na półkach");
        }
    }

    public void quantityValidation(VendingMachine vendingMachine, int shelfNumber, int quantity) throws IndexOutOfBoundsException {
        Shelf shelfToWitchAdds = vendingMachine.getShelfsMap().get(shelfNumber);
        if (quantity < 1) {
            throw new IndexOutOfBoundsException("Nieprawidłowa ilość.");
        }
        if (shelfToWitchAdds.quantity + quantity > vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF()) {
            throw new IndexOutOfBoundsException("Brak miesjaca na półce. Pozostało " +
                    (vendingMachine.getMAX_QUANTITY_ON_ONE_SHELF() - shelfToWitchAdds.quantity) +
                    " wolnych.");
        }
    }

    public void shelfNumberValidation(VendingMachine vendingMachine, int shelfNumber) throws IndexOutOfBoundsException {
        if (shelfNumber < 1 || shelfNumber > vendingMachine.getQUANTITY_OF_SHELFS()) {
            throw new IndexOutOfBoundsException("Brak półki z takim numerem. Dostępne numery (1-" +
                    vendingMachine.getQUANTITY_OF_SHELFS() + ").");
        }
    }
}