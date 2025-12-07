from itertools import batched, chain

STUDENTS = (
    "Alice", "Bob", "Charlie", "David", "Eve", "Fred",
    "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"
)

PLANT_CODES = {"C": "Clover", "G": "Grass", "R": "Radishes", "V": "Violets"}

class Garden:
    def __init__(self, diagram, students=STUDENTS):
        rows = (batched(row, 2) for row in diagram.splitlines())
        plants_by_student = tuple(map(tuple, map(chain.from_iterable, zip(*rows))))
        self.students = dict(zip(sorted(students), plants_by_student))

    def plants(self, student: str) -> list[str]:
        return list(map(PLANT_CODES.get, self.students[student]))
