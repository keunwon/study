package productimporter.suppliers.wayneeenterprises;

import productimporter.Pricing;
import productimporter.Product;

import java.math.BigDecimal;

public class WayneEnterprisesProductTranslator {

    public Product translateProduct(WayneEnterprisesProduct source) {
        final var pricing = getPricing(source);
        return new Product("WAYNE", source.getId(), source.getTitle(), pricing);
    }

    private Pricing getPricing(WayneEnterprisesProduct source) {
        final var listPrice = new BigDecimal(source.getListPrice());
        final var discount = new BigDecimal(source.getListPrice() - source.getSellingPrice());
        return new Pricing(listPrice, discount);
    }
}
