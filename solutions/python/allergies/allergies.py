ALLERGIES = {
    "eggs": 1,
    "peanuts": 2,
    "shellfish": 4,
    "strawberries": 8,
    "tomatoes": 16,
    "chocolate": 32,
    "pollen": 64,
    "cats": 128
}


class Allergies:

    def __init__(self, score):
        self._score = score

    def allergic_to(self, item: str) -> bool:
        return (self._score & ALLERGIES[item]) != 0

    @property
    def lst(self):
        return [a for a in ALLERGIES.keys() if self.allergic_to(a)]
