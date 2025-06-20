def is_valid(isbn: str) -> bool:
    digits = list(isbn.replace('-', ''))

    if len(digits) != 10:
        return False

    if digits[-1] == 'X':
        digits[-1] = '10'

    if any(not symbol.isdigit() for symbol in digits):
        return False

    check_sum = sum(
        int(digit) * weight
        for digit, weight in zip(digits, range(10, 0, -1))
    )

    return check_sum % 11 == 0
