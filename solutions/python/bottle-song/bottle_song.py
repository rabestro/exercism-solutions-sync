NUMBERS = ("no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")


def hanging(bottles):
    suffix = '' if bottles == 1 else 's'
    return f"{NUMBERS[bottles]} green bottle{suffix} hanging on the wall"


def verse(bottles):
    current_hanging = hanging(bottles)
    next_hanging = hanging(bottles - 1).lower()
    return (
        f"{current_hanging},",
        f"{current_hanging},",
        f"And if one green bottle should accidentally fall,",
        f"There'll be {next_hanging}.",
        ""
    )


def recite(start: int, take=1) -> list[str]:
    return [
               line
               for lines in map(verse, range(start, start - take, -1))
               for line in lines
           ][:-1]
