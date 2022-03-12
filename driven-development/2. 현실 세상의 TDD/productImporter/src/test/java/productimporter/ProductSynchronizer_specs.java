package productimporter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import productimporter.suppliers.wayneeenterprises.WayneEnterprisesProduct;
import productimporter.suppliers.wayneeenterprises.WayneEnterprisesProductImporter;
import productimporter.suppliers.wayneeenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductSynchronizer_specs {

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_saves_products(WayneEnterprisesProduct[] products) {
        var stub = new WayneEnterprisesProductSourceStub(products);
        var importer = new WayneEnterprisesProductImporter(stub);
        var validator = new ListPriceFilter(BigDecimal.ZERO);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        Iterable<Product> expected = importer.fetchProducts();
        assertThat(spy.getLog()).usingRecursiveFieldByFieldElementComparator().containsAll(expected);
    }

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_does_not_save_invalid_product(WayneEnterprisesProduct product) {
        // Arrange
        var lowerBound = new BigDecimal(product.getListPrice() + 10000);
        var validator = new ListPriceFilter(lowerBound);

        var stub = new WayneEnterprisesProductSourceStub(product);
        var importer = new WayneEnterprisesProductImporter(stub);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        // Act
        sut.run();

        // Assert
        assertThat(spy.getLog()).isEmpty();
    }

    @Test
    void sut_really_does_not_save_invalid_product() {
        // Arrange
        var pricing = new Pricing(BigDecimal.TEN, BigDecimal.ONE);
        var product = new Product("supplierName", "productCode", "productName", pricing);

        var importer = mock(ProductImporter.class);
        when(importer.fetchProducts()).thenReturn(List.of(product));

        var validator = mock(ProductValidator.class);
        when(validator.isValid(product)).thenReturn(false);

        var inventory = mock(ProductInventory.class);

        var sut = new ProductSynchronizer(importer, validator, inventory);

        // Act
        sut.run();

        // Assert
        verify(inventory, never()).upsertProduct(product);
    }
}
