def is_valid_triangle(sides):
    return (len(sides) == 3
            and all(x > 0 for x in sides)
            and max(sides) < sum(sides) / 2)


def equilateral(sides):
    return is_valid_triangle(sides) and len(set(sides)) == 1


def isosceles(sides):
    return is_valid_triangle(sides) and len(set(sides)) <= 2


def scalene(sides):
    return is_valid_triangle(sides) and len(set(sides)) == 3
