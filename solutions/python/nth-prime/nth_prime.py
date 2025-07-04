_primes = [2, 3, 5, 7, 11, 13]

def prime(number):
    if number == 0:
        raise ValueError("there is no zeroth prime")
    if number < 0:
        raise ValueError("negative numbers are not supported")

    if number <= len(_primes):
        return _primes[number - 1]

    max_prime = _primes[-1]
    while len(_primes) < number:
        max_prime += 2
        if all(max_prime % prime for prime in _primes):
            _primes.append(max_prime)

    return _primes[-1]