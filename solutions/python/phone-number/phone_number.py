class PhoneNumber:
    def __init__(self, number: str):
        if any(c.isalpha() for c in number):
            raise ValueError("letters not permitted")
        if any(c in "@:!" for c in number):
            raise ValueError("punctuations not permitted")
        digits = "".join(filter(str.isdigit, number))
        if len(digits) > 11:
            raise ValueError("must not be greater than 11 digits")
        if len(digits) < 10:
            raise ValueError("must not be fewer than 10 digits")
        if len (digits) == 11 and not digits.startswith("1"):
            raise ValueError("11 digits must start with 1")
        self.number = digits[-10:]
        if self.number.startswith("0"):
            raise ValueError("area code cannot start with zero")
        if self.number.startswith("1"):
            raise ValueError("area code cannot start with one")
        exchange_code = self.number[3:6]
        if exchange_code.startswith("0"):
            raise ValueError("exchange code cannot start with zero")
        if exchange_code.startswith("1"):
            raise ValueError("exchange code cannot start with one")

    def pretty(self) -> str:
        return f"({self.area_code})-{self.number[3:6]}-{self.number[6:]}"

    @property
    def area_code(self) -> str:
        return self.number[:3]
