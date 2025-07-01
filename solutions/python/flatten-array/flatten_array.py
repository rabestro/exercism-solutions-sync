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
    return [x for x in deep_flatten(iterable) if x is not None]
