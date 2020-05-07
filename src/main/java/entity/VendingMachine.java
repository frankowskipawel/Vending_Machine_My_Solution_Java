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
        Validators validators = new Validators();
        validators.productTypeValidation(this,shelfNumber,product);
        validators.quantityValidation(this,shelfNumber, quantity);
        validators.shelfNumberValidation(this,shelfNumber);
        Shelf shelfToWitchAdds = shelfsMap.get(shelfNumber);
        shelfToWitchAdds.setProduct(product);
        shelfToWitchAdds.setQuantity(shelfToWitchAdds.quantity + quantity);
    }

}
