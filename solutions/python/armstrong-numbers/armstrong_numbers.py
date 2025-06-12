def is_armstrong_number(number):
    s_number = str(number)
    power = len(str(s_number))
    return number == sum(int(digit) ** power for digit in s_number)

