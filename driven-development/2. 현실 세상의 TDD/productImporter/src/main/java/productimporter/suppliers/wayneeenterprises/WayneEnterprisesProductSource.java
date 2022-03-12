package productimporter.suppliers.wayneeenterprises;

public interface WayneEnterprisesProductSource {
    Iterable<WayneEnterprisesProduct> fetchProducts();
}
