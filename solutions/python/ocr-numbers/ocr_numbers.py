from itertools import batched

DIGIT_WIDTH = 3
DIGIT_HEIGHT = 4

digits = [
    " _     _  _     _  _  _  _  _ ",
    "| |  | _| _||_||_ |_   ||_||_|",
    "|_|  ||_  _|  | _||_|  ||_| _|",
    "                              ",
]

def split_line(line):
    return zip(*(batched(x, DIGIT_WIDTH) for x in line))

digits_map = dict(zip(split_line(digits), map(str, range(10))))

def convert_line(line):
    return "".join(digits_map.get(x, '?') for x in split_line(line))

def convert(input_grid: list[str]):
    if len(input_grid) % DIGIT_HEIGHT != 0:
        raise ValueError("Number of input lines is not a multiple of four")

    if any(len(x) % DIGIT_WIDTH != 0 for x in input_grid):
        raise ValueError("Number of input columns is not a multiple of three")

    return ','.join(map(convert_line, batched(input_grid, DIGIT_HEIGHT)))


