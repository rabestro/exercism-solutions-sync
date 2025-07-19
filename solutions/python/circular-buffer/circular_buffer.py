import collections


class BufferFullException(BufferError):
    """Exception raised when CircularBuffer is full."""


class BufferEmptyException(BufferError):
    """Exception raised when CircularBuffer is empty."""


class CircularBuffer[T]:
    """A circular buffer implementation using collections.deque."""

    def __init__(self, capacity: int):
        """Initialize the CircularBuffer with a fixed capacity."""
        self._capacity = capacity
        self._data: collections.deque[T] = collections.deque(maxlen=capacity)

    @property
    def is_empty(self) -> bool:
        """Return True if the buffer is empty, False otherwise."""
        return not self._data

    @property
    def is_full(self) -> bool:
        """Return True if the buffer is full, False otherwise."""
        return len(self._data) == self._capacity

    def read(self) -> T:
        """Remove and return the oldest item from the buffer."""
        if self.is_empty:
            raise BufferEmptyException("Circular buffer is empty")
        return self._data.popleft()

    def write(self, data: T) -> None:
        """Add an item to the buffer if it's not full."""
        if self.is_full:
            raise BufferFullException("Circular buffer is full")
        self._data.append(data)

    def overwrite(self, data: T) -> None:
        """Add an item, overwriting the oldest item if the buffer is full."""
        self._data.append(data)

    def clear(self) -> None:
        """Remove all items from the buffer."""
        self._data.clear()
