def is_armstrong_number(number):
    power = len(str(number))
    sum_of_digits = 0
    for digit in str(number):
        sum_of_digits += int(digit) ** power
    return sum_of_digits == number
