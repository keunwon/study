package numberguessing;

import java.util.Random;

public class RandomGenerator implements PositiveIntegerGenerator {
    private static final Random random = new Random();

    @Override
    public int generateLessThanOrEqualToHundred() {
        return random.nextInt(100) + 1;
    }
}
