import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Sample {
	public static boolean isPrime(int number) {
		return number > 1 && 
				IntStream.range(2, number) // exclusivo -> exclui o number
				.noneMatch(i -> number % i == 0);
	}

	public static double computeSumOfSqrtOfPrimes(int start, int count) {
		// Interface de Função Identidade: <T> UnaryOperator<T> identity();
		final UnaryOperator<Integer> incrStart = i -> Integer.valueOf(i + 1);

		return Stream.iterate(start, incrStart) // Infinite Series
				.filter(Sample::isPrime)
				.mapToDouble(Math::sqrt)
				.limit(count) 
				.sum(); // Lazy Evaluation
	}

	public static void main(String[] args) {
		System.out.println(computeSumOfSqrtOfPrimes(101, 51));
	}
}