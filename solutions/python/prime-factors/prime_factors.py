def factors(number: int) -> list[int]:
    prime_factors: list[int] = []
    prime = 2
    while prime <= number:
        if number % prime:
            prime += 1
        else:
            prime_factors.append(prime)
            number //= prime

    return prime_factors
