package productimporter.suppliers.wayneeenterprises;

public record WayneEnterprisesProduct(String id, String title, int listPrice, int sellingPrice) {

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getListPrice() {
        return listPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }
}
