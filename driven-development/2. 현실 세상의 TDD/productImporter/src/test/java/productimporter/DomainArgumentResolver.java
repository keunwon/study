package productimporter;

import productimporter.suppliers.wayneeenterprises.WayneEnterprisesProductArgumentResolver;

import java.util.Optional;
import java.util.Random;

public interface DomainArgumentResolver {
    Optional<Object> tryResolve(Class<?> parameterType);

    static Random random =  new Random();

    static CompositeArgumentResolver instance = new CompositeArgumentResolver(
            new WayneEnterprisesProductArgumentResolver());
}
