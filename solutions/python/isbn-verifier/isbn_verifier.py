def is_valid(isbn: str) -> bool:
    cleaned_isbn = isbn.replace('-', '')

    if len(cleaned_isbn) != 10 or not cleaned_isbn[:-1].isdigit() or not cleaned_isbn[-1] in '0123456789X':
        return False

    digits = cleaned_isbn[:-1]
    check_char = cleaned_isbn[-1]
    check_digit = 10 if check_char == 'X' else int(check_char)

    check_sum = sum(
        int(digit) * weight
        for digit, weight in zip(digits, range(10, 1, -1))
    )
    return (check_sum + check_digit) % 11 == 0
