package productimporter;

import java.util.ArrayList;
import java.util.List;

public class ProductInventorySpy implements ProductInventory {
    private final List<Product> log;

    public ProductInventorySpy() {
        this.log = new ArrayList<>();
    }

    public List<Product> getLog() {
        return log;
    }

    @Override
    public void upsertProduct(Product product) {
        log.add(product);
    }
}
