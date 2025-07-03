import functools
import threading


def requires_open_account(func):
    """Decorator that raises a ValueError if the account is not open."""

    @functools.wraps(func)
    def wrapper(self, *args, **kwargs):
        if not self._is_open:
            raise ValueError("account not open")
        return func(self, *args, **kwargs)

    return wrapper


def _validate_amount(amount) -> None:
    """Validate that the transaction amount is positive."""
    if amount <= 0:
        raise ValueError('amount must be greater than 0')


class BankAccount:
    def __init__(self):
        self._balance: int | None = None
        self._lock = threading.Lock()

    @property
    def _is_open(self) -> bool:
        """A private property to check the account state."""
        return self._balance is not None

    @requires_open_account
    def get_balance(self) -> int:
        """The current balance of the account."""
        return self._balance

    def open(self) -> None:
        """Opens the account with a balance of zero."""
        with self._lock:
            if self._is_open:
                raise ValueError("account already open")
            self._balance = 0

    @requires_open_account
    def deposit(self, amount: int) -> None:
        """Deposits a positive amount into the account."""
        _validate_amount(amount)
        with self._lock:
            self._balance += amount

    @requires_open_account
    def withdraw(self, amount: int) -> None:
        """Withdraws a positive amount from the account."""
        _validate_amount(amount)
        if amount > self._balance:
            raise ValueError("amount must be less than balance")
        with self._lock:
            self._balance -= amount

    @requires_open_account
    def close(self) -> None:
        """Closes the account."""
        with self._lock:
            self._balance = None
