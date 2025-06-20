from math import sqrt


def score(x, y):
    distance = sqrt(x ** 2 + y ** 2)
    return 10 if distance <= 1 else 5 if distance <= 5 else 1 if distance <= 10 else 0
