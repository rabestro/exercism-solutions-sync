def equilateral(sides):
    return is_valid_triangle(sides) and sides[0] == sides[1] == sides[2]


def isosceles(sides):
    return is_valid_triangle(sides) and (
            sides[0] == sides[1] or sides[0] == sides[2] or sides[1] == sides[2])


def scalene(sides):
    return (is_valid_triangle(sides)
            and sides[0] != sides[1]
            and sides[0] != sides[2]
            and sides[1] != sides[2])

def is_valid_triangle(sides):
    return (len(sides) == 3
            and all(x > 0 for x in sides)
            and sides[0] + sides[1] > sides[2]
            and sides[2] + sides[1] > sides[0]
            and sides[2] + sides[0] > sides[1])
