from collections.abc import Iterable
from typing import Any


def deep_flatten(nested: Iterable):
    """Flattens an arbitrarily deep and irregular list."""
    for item in nested:
        if isinstance(item, Iterable):
            yield from deep_flatten(item)
        else:
            yield item


def flatten(iterable: Iterable[Any]) -> list[int]:
    """Flattens a nested iterable into a single-level list, excluding None values.

    Takes an arbitrarily nested iterable and returns a flattened list containing
    all non-None values from the input, preserving the order of elements.

    Args:
        iterable: An iterable that may contain nested iterables and None values.

    Returns:
        list[int]: A flattened list containing all non-None values from the input.

    Example:
        >>> flatten([1, [2, None, [3, None]], 4])
        [1, 2, 3, 4]
    """
    return [x for x in deep_flatten(iterable) if x is not None]
