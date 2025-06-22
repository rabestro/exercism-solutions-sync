def say(number: int) -> str:
    if 0 <= number <= 999_999_999_999:
        return say_in_english(number)
    else:
        raise ValueError("input out of range")

def say_in_english(number: int) -> str:
    units = {
        0: 'zero', 1: 'one', 2: 'two', 3: 'three', 4: 'four', 5: 'five',
        6: 'six', 7: 'seven', 8: 'eight', 9: 'nine', 10: 'ten',
        11: 'eleven', 12: 'twelve', 13: 'thirteen', 14: 'fourteen', 15: 'fifteen',
        16: 'sixteen', 17: 'seventeen', 18: 'eighteen', 19: 'nineteen',
        20: 'twenty', 30: 'thirty', 40: 'forty', 50: 'fifty',
        60: 'sixty', 70: 'seventy', 80: 'eighty', 90: 'ninety'
    }
    if number in units:
        return units[number]

    if number < 100:
        return say_in_english(number // 10 * 10) + "-" + say_in_english(number % 10)

    def say_magnitude(divisor, magnitude):
        quotient_part = f'{say_in_english(number // divisor)} {magnitude}'
        remainder = number % divisor
        return f'{quotient_part} {say_in_english(remainder)}' if remainder else quotient_part

    if number < 1000:
        return say_magnitude(100, 'hundred')
    if number < 1_000_000:
        return say_magnitude(1_000, 'thousand')
    if number < 1_000_000_000:
        return say_magnitude(1_000_000, 'million')
    return say_magnitude(1_000_000_000, 'billion')
