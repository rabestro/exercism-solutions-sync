def transform(legacy_data: dict[int, list[str]]) -> dict[str, int]:
    return {
        letter.lower(): number
        for number, letters in legacy_data.items()
        for letter in letters
    }
