package variance;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println(switch (args.length) {
                    case 0 -> "입력된 데이터가 없습니다.";
                    case 1 -> "데이터가 부족해 분산을 계산할 수 없습니다. 2개 이상의 데이터를 입력해 주세요.";
                    default -> getVarianceOutput(args);
        });
    }

    private static String getVarianceOutput(String[] args) {
        final var sources = parseArguments(args);
        final var variance = CalculateVariance(sources);
        return "분산: " + variance;
    }

    private static double CalculateVariance(double[] source) {
        final var sumOfSquares = CalculateSumOfSquares(source);
        final var degreesOfFreedom = source.length - 1;
        return sumOfSquares / degreesOfFreedom;
    }

    private static double CalculateSumOfSquares(double[] arr) {
        var mean = Arrays.stream(arr).average().orElse(Double.NaN);
        return Arrays.stream(arr)
                .map(x -> Math.pow(mean - x, 2))
                .sum();
    }

    private static double[] parseArguments(String[] args) {
        return Stream.of(args)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }
}
