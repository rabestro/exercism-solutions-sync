from collections.abc import Sequence
import functools

type Sides = Sequence[float | int]


def is_valid_triangle(sides: Sides) -> bool:
    """Validates if the given sides can form a triangle."""
    try:
        s1, s2, s3 = sorted(sides)
    except ValueError:
        return False

    return s1 > 0 and s1 + s2 > s3


def validate_triangle(func):
    """Decorator to ensure sides form a valid triangle before proceeding."""

    @functools.wraps(func)
    def wrapper(sides: Sides) -> bool:
        # We can perform a simplified check here because `is_valid_triangle`
        # will do the full, robust check.
        if not is_valid_triangle(sides):
            return False
        return func(sides)

    return wrapper


@validate_triangle
def equilateral(sides: Sides) -> bool:
    """Determines if a triangle is equilateral."""
    return len(set(sides)) == 1


@validate_triangle
def isosceles(sides: Sides) -> bool:
    """Determines if a triangle is isosceles."""
    return len(set(sides)) <= 2


@validate_triangle
def scalene(sides: Sides) -> bool:
    """Determines if a triangle is scalene."""
    return len(set(sides)) == 3
