class School:
    def __init__(self):
        self.db = {}
        self.status = []

    def add_student(self, name: str, grade: int):
        not_exist = name not in self.db
        self.status.append(not_exist)
        if not_exist:
            self.db[name] = grade

    def roster(self) -> list[str]:
        return [
            name for name, grade in sorted(
                self.db.items(),
                key=lambda item: (item[1], item[0])
            )
        ]

    def grade(self, grade_number: int) -> list[str]:
        return sorted([
            name for name, grade in self.db.items()
            if grade == grade_number
        ])

    def added(self) -> list[bool]:
        return self.status
