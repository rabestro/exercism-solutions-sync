from collections.abc import Iterable, Iterator
from itertools import batched

DIGIT_WIDTH = 3
DIGIT_HEIGHT = 4
type DigitPattern = tuple[str, ...]

OCR_TEMPLATE_STR = [
    " _     _  _     _  _  _  _  _ ",
    "| |  | _| _||_||_ |_   ||_||_|",
    "|_|  ||_  _|  | _||_|  ||_| _|",
    "                              ",
]


def extract_patterns(rows: Iterable[str]) -> Iterator[DigitPattern]:
    return zip(*(batched(row, DIGIT_WIDTH) for row in rows))


PATTERN_TO_DIGIT: dict[DigitPattern, str] = dict(
    zip(extract_patterns(OCR_TEMPLATE_STR), map(str, range(10)))
)


def convert_ocr_rows(rows: tuple[str, ...]) -> str:
    return ''.join(
        PATTERN_TO_DIGIT.get(pattern, '?')
        for pattern in extract_patterns(rows)
    )


def convert(input_grid: list[str]) -> str:
    if len(input_grid) % DIGIT_HEIGHT != 0:
        raise ValueError("Number of input lines is not a multiple of four")

    if any(len(x) % DIGIT_WIDTH != 0 for x in input_grid):
        raise ValueError("Number of input columns is not a multiple of three")

    return ','.join(map(convert_ocr_rows, batched(input_grid, DIGIT_HEIGHT)))
