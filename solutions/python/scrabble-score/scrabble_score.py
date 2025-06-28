SCRABBLE_SCORE = (
    (1, "AEIOULNRST"),
    (2, "DG"),
    (3, "BCMP"),
    (4, "FHVWY"),
    (5, "K"),
    (8, "JX"),
    (10, "QZ")
)


def score(word: str) -> int:
    word = word.upper()
    return sum(
        score * word.count(letter)
        for score, letters in SCRABBLE_SCORE
        for letter in letters
    )
