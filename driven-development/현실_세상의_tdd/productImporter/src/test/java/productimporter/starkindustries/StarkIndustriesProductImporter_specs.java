package productimporter.starkindustries;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;
import productimporter.suppliers.starkindustries.StarkIndustriesProduct;
import productimporter.suppliers.starkindustries.StarkIndustriesProductImporter;
import productimporter.suppliers.starkindustries.StarkIndustriesProductSource;
import productimporter.suppliers.starkindustries.StarkIndustriesProductTranslator;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StarkIndustriesProductImporter_specs {

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_projects_all_products(StarkIndustriesProduct[] sourceProducts) {
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class);

        var sut = new StarkIndustriesProductImporter(productSource, translator);

        Iterable<Product> actual = sut.fetchProducts();

        assertThat(actual).hasSize(sourceProducts.length);
    }

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_translates_source_products(StarkIndustriesProduct[] sourceProducts, Product[] products) {
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class);

        var tuples = IntStream.range(0, Math.min(sourceProducts.length, products.length))
                .mapToObj(index -> Tuple.tuple(sourceProducts[index], products[index]))
                .toList();

        for (Tuple tuple : tuples) {
            var values = tuple.toArray();
            when(translator.translate((StarkIndustriesProduct) values[0])).thenReturn((Product) values[1]);
        }

        var sut = new StarkIndustriesProductImporter(productSource, translator);
        var actual = sut.fetchProducts();

        assertThat(actual).containsExactly(products);
    }
}
