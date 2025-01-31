package productimporter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.stream.Stream;

public class DomainArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<DomainArgumentsSource> {

    @Override
    public void accept(DomainArgumentsSource domainArgumentsSource) {
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        final var method = context.getRequiredTestMethod();
        final var parameterTypes = method.getParameterTypes();

        final var arguments = new Object[parameterTypes.length];
        final var argumentResolver = DomainArgumentResolver.instance;
        for (int i = 0; i < arguments.length; i++) {
            final var argument = argumentResolver.tryResolve(parameterTypes[i]);
            arguments[i] = argument.orElse(null);
        }

        return Arrays.stream(new Arguments[] {Arguments.of(arguments)});
    }
}
