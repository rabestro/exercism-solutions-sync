days = (
    ("first", "a Partridge in a Pear Tree."),
    ("second", "two Turtle Doves, and"),
    ("third", "three French Hens,"),
    ("fourth", "four Calling Birds,"),
    ("fifth", "five Gold Rings,"),
    ("sixth", "six Geese-a-Laying,"),
    ("seventh", "seven Swans-a-Swimming,"),
    ("eighth", "eight Maids-a-Milking,"),
    ("ninth", "nine Ladies Dancing,"),
    ("tenth", "ten Lords-a-Leaping,"),
    ("eleventh", "eleven Pipers Piping,"),
    ("twelfth", "twelve Drummers Drumming,"),
)


def recite_single_verse(verse_number: int) -> str:
    day = days[verse_number - 1][0]
    gifts = " ".join(days[i][1] for i in reversed(range(verse_number)))
    return f"On the {day} day of Christmas my true love gave to me: {gifts}"


def recite(start_verse, end_verse):
    verses = range(start_verse, end_verse + 1)
    return list(map(recite_single_verse, verses))
