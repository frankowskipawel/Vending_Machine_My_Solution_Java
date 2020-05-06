package entity;

import lombok.Data;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class VendingMachine {

    private final int QUANTITY_OF_SHELFS = 25;
    private final int MAX_QUANTITY_ON_ONE_SHELF = 10;

    private Map<Integer, Shelf> shelfsMap;

    public VendingMachine() {
        initializeShelfs();
    }

    private void initializeShelfs() {
        this.shelfsMap = new LinkedHashMap<>();
        for (int i = 1; i <= QUANTITY_OF_SHELFS; i++) {
            shelfsMap.put(i, new Shelf(null, 0));
        }
    }

    public void addProduct(int shelfNumber, Product product, int quantity) throws Exception {
        shelfNumberValidation(shelfNumber);
        quantityValidation(shelfNumber, quantity);
        productTypeValidation(shelfNumber, product);
        Shelf shelfToWitchAdds = shelfsMap.get(shelfNumber);
        shelfToWitchAdds.setProduct(product);
        shelfToWitchAdds.setQuantity(shelfToWitchAdds.quantity + quantity);
    }

    public void productTypeValidation(int shelfNumber, Product product) throws InvalidParameterException {
        Shelf shelfToWitchAdds = shelfsMap.get(shelfNumber);
        if (!shelfToWitchAdds.getProduct().getName().equals(product.getName())) {
            throw new InvalidParameterException("Na półce jest już produkt innego typu. Nie można mieszać produktów na półkach");
        }
    }

    public void quantityValidation(int shelfNumber, int quantity) throws IndexOutOfBoundsException {
        Shelf shelfToWitchAdds = shelfsMap.get(shelfNumber);
        if (quantity < 1) {
            throw new IndexOutOfBoundsException("Nieprawidłowa ilość.");
        }
        if (shelfToWitchAdds.quantity + quantity > MAX_QUANTITY_ON_ONE_SHELF) {
            throw new IndexOutOfBoundsException("Brak miesjaca na półce. Pozostało " +
                    (MAX_QUANTITY_ON_ONE_SHELF - shelfToWitchAdds.quantity) +
                    " wolnych.");
        }
    }

    public void shelfNumberValidation(int shelfNumber) throws IndexOutOfBoundsException {
        if (shelfNumber < 1 || shelfNumber > QUANTITY_OF_SHELFS) {
            throw new IndexOutOfBoundsException("Brak półki z takim numerem. Dostępne numery (1-" + MAX_QUANTITY_ON_ONE_SHELF + ").");
        }
    }
}
