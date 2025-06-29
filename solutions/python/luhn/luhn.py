class Luhn:
    def __init__(self, card_num):
        self.card_num = card_num.replace(' ', '')

    def valid(self):
        return self.card_num.isdigit() and len(self.card_num) > 1 and sum(
            doubled_digit - 9 if doubled_digit > 9 else doubled_digit
            for doubled_digit in (
                digit * (1 + index % 2)
                for index, digit in enumerate(map(int, reversed(self.card_num)))
            )
        ) % 10 == 0
