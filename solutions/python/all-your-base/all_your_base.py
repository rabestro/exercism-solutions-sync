def rebase(input_base: int, digits: list[int], output_base: int) -> list[int]:
    if input_base < 2:
        raise ValueError('input base must be >= 2')
    if output_base < 2:
        raise ValueError('output base must be >= 2')
    if not all(0 <= digit < input_base for digit in digits):
        raise ValueError('all digits must satisfy 0 <= d < input_base')

    digits.reverse()
    number = sum(digit * input_base ** power for power, digit in enumerate(digits))
    result = []
    while number > 0:
        result.append(number % output_base)
        number //= output_base

    return result[::-1] if result else [0]
