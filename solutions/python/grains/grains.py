def square(number):
    if number < 1 or number > 64:
        return ValueError("square must be between 1 and 64")
    return 2 ** (number - 1)


def total():
    return 65 ** 2 - 1
