PLANT_CODES = {
    "C": "Clover",
    "G": "Grass",
    "R": "Radishes",
    "V": "Violets"
}

DEFAULT_STUDENTS = (
    "Alice", "Bob", "Charlie", "David", "Eve", "Fred",
    "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"
)


class Garden:
    def __init__(self, diagram: str, students: tuple[str, ...] = DEFAULT_STUDENTS):
        self.rows = diagram.splitlines()
        self.students = sorted(students)

    def plants(self, student: str) -> list[str]:
        index = self.students.index(student)
        start = index * 2
        end = start + 2
        row1_segment = self.rows[0][start:end]
        row2_segment = self.rows[1][start:end]
        return [PLANT_CODES[code] for code in row1_segment + row2_segment]
