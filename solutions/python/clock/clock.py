from __future__ import annotations

MINUTES_IN_HOUR = 60
HOURS_IN_DAY = 24
MINUTES_IN_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR


def _normalize_minutes(minutes: int) -> int:
    return (minutes % MINUTES_IN_DAY + MINUTES_IN_DAY) % MINUTES_IN_DAY


class Clock:
    def __init__(self, hour: int, minute: int):
        """Initialize the clock with given hours and minutes."""
        self.minutes = _normalize_minutes(hour * MINUTES_IN_HOUR + minute)

    def _get_hour_and_minute(self) -> tuple[int, int]:
        """Extract normalized hour and minute from total minutes."""
        hours = self.minutes // MINUTES_IN_HOUR
        minutes = self.minutes % MINUTES_IN_HOUR
        return hours, minutes

    def __repr__(self) -> str:
        """Return formal string representation of the clock."""
        hour, minute = self._get_hour_and_minute()
        return f"Clock({hour}, {minute})"

    def __str__(self) -> str:
        """Return a formatted time string in HH:MM format."""
        hour, minute = self._get_hour_and_minute()
        return f"{hour:02d}:{minute:02d}"

    def __eq__(self, other: Clock) -> bool:
        """Compare two clocks for equality."""
        return self.minutes == other.minutes

    def __add__(self, minutes: int) -> Clock:
        """Add minutes to the clock time."""
        return Clock(0, self.minutes + minutes)

    def __sub__(self, minutes: int) -> Clock:
        """Subtract minutes from the clock time."""
        return Clock(0, self.minutes - minutes)
