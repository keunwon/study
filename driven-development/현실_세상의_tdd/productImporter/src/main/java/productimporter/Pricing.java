package productimporter;

import java.math.BigDecimal;

public record Pricing(BigDecimal listPrice, BigDecimal discount) {

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
