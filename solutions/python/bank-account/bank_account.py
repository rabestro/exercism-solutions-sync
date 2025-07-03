class BankAccount:
    def __init__(self):
        self._balance: int | None = None

    def _validate_open(self) -> None:
        if self._balance is None:
            raise ValueError('account not open')

    def _validate_closed(self) -> None:
        if self._balance is not None:
            raise ValueError('account already open')

    def _validate_amount(self, amount) -> None:
        if amount <= 0:
            raise ValueError('amount must be greater than 0')

    def get_balance(self) -> int:
        self._validate_open()
        return self._balance

    def open(self) -> None:
        self._validate_closed()
        self._balance = 0

    def deposit(self, amount) -> None:
        self._validate_open()
        self._validate_amount(amount)
        self._balance += amount

    def withdraw(self, amount) -> None:
        self._validate_open()
        self._validate_amount(amount)
        if amount > self._balance:
            raise ValueError('amount must be less than balance')
        self._balance -= amount

    def close(self) -> None:
        self._validate_open()
        self._balance = None
