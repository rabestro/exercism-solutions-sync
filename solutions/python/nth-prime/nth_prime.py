_primes = [2, 3, 5, 7, 11, 13]

def prime(number):
    if number == 0:
        raise ValueError("there is no zeroth prime")
    if number < 0:
        raise ValueError("negative numbers are not supported")

    if number <= len(_primes):
        return _primes[number - 1]

    candidate = _primes[-1]
    while len(_primes) < number:
        candidate += 2
        if all(candidate % prime for prime in _primes):
            _primes.append(candidate)

    return _primes[-1]