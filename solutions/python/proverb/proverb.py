import itertools


def proverb(*things: str, qualifier: str | None = None) -> list[str]:
    if not things:
        return []

    verses = [
        f"For want of a {want} the {lost} was lost."
        for want, lost in itertools.pairwise(things)
    ]
    qualified_noun = f"{qualifier} {things[0]}" if qualifier else things[0]
    verses.append(f"And all for the want of a {qualified_noun}.")
    return verses
