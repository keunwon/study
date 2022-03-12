package productimporter;

public record Product(String supplierName, String productCode, String productName,
                      Pricing pricing) {

    public String getSupplierName() {
        return supplierName;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public Pricing getPricing() {
        return pricing;
    }
}
