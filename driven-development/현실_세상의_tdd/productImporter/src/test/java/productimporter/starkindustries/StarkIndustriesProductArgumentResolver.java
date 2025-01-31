package productimporter.starkindustries;

import productimporter.DomainArgumentResolver;
import productimporter.suppliers.starkindustries.StarkIndustriesProduct;

import java.util.Optional;
import java.util.UUID;

public class StarkIndustriesProductArgumentResolver implements DomainArgumentResolver {

    @Override
    public Optional<Object> tryResolve(Class<?> parameterType) {
        if (parameterType.equals(StarkIndustriesProduct.class)) {
            return Optional.of(generate());
        } else if (parameterType.equals(StarkIndustriesProduct[].class)) {
            return Optional.of(new StarkIndustriesProduct[] {generate(), generate(), generate()});
        }

        return Optional.empty();
    }

    private static StarkIndustriesProduct generate() {
        String id = "id" + UUID.randomUUID().toString();
        String title = "title" + UUID.randomUUID().toString();
        int listPrice = random.nextInt(100000) + 100000;
        int sellingPrice = listPrice - random.nextInt(10000) + 10000;
        return new StarkIndustriesProduct(id, title, listPrice, sellingPrice);
    }
}
