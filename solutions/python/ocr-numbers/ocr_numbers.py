import string
from collections.abc import Iterable, Iterator
from itertools import batched

type DigitPattern = tuple[tuple[str, ...], ...]

DIGIT_WIDTH = 3
DIGIT_HEIGHT = 4
OCR_TEMPLATE_STR = [
    " _     _  _     _  _  _  _  _ ",
    "| |  | _| _||_||_ |_   ||_||_|",
    "|_|  ||_  _|  | _||_|  ||_| _|",
    "                              ",
]


def extract_patterns(rows: Iterable[str]) -> Iterator[DigitPattern]:
    """
    Transposes row strings into vertical digit patterns.
    Returns an iterator of 3x4 character matrices.
    """
    return zip(*(batched(row, DIGIT_WIDTH) for row in rows), strict=True)


PATTERN_TO_DIGIT: dict[DigitPattern, str] = dict(
    zip(extract_patterns(OCR_TEMPLATE_STR), string.digits, strict=True)
)


def convert_ocr_rows(rows: Iterable[str]) -> str:
    """Converts a chunk of 4 rows into a string of digits."""
    return "".join(
        PATTERN_TO_DIGIT.get(pattern, "?")
        for pattern in extract_patterns(rows)
    )


def convert(input_grid: list[str]) -> str:
    """
    Converts a grid of strings representing OCR digits into a number string.
    Raises ValueError on malformed inputs.
    """
    if len(input_grid) % DIGIT_HEIGHT != 0:
        raise ValueError("Number of input lines is not a multiple of four")

    if any(len(row) % DIGIT_WIDTH != 0 for row in input_grid):
        raise ValueError("Number of input columns is not a multiple of three")

    return ",".join(map(convert_ocr_rows, batched(input_grid, DIGIT_HEIGHT)))
