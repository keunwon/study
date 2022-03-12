package productimporter.suppliers.starkindustries;

import productimporter.Product;
import productimporter.ProductImporter;

import java.util.ArrayList;

public class StarkIndustriesProductImporter implements ProductImporter {

    private StarkIndustriesProductSource productSource;
    private StarkIndustriesProductTranslator translator;

    public StarkIndustriesProductImporter(StarkIndustriesProductSource productSource, StarkIndustriesProductTranslator translator) {
        this.productSource = productSource;
        this.translator = translator;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        var products = new ArrayList<Product>();
        for (StarkIndustriesProduct source : productSource.getAllProducts()) {
            products.add(translator.translate(source));
        }
        return products;
    }
}
