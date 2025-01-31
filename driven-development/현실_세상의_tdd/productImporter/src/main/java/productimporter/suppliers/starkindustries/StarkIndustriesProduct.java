package productimporter.suppliers.starkindustries;

public record StarkIndustriesProduct(String code, String name, int listPrice, int discountAmount) {

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getListPrice() {
        return listPrice;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
