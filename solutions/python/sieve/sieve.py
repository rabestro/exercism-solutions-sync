def primes(limit: int) -> list[int]:
    prime_numbers = []
    def is_prime(n: int) -> bool:
        return all(n % prime for prime in prime_numbers)
    for number in range(2, limit + 1):
        if is_prime(number):
            prime_numbers.append(number)
    return prime_numbers
