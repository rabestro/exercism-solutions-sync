import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Sieve {
    private final List<Integer> primes;
    private final int maxPrime;

    Sieve(int maxPrime) {
        this.maxPrime = maxPrime;
        primes = new ArrayList<>();
    }

    List<Integer> getPrimes() {
        IntPredicate isPrime = number -> primes.stream().allMatch(prime -> number % prime > 0);
        IntStream.rangeClosed(2, maxPrime).filter(isPrime).forEach(primes::add);
        return primes;
    }
}
