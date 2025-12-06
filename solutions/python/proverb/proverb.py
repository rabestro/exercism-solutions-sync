def proverb(*things: str, qualifier: str | None) -> list[str]:
    last_thing = len(things) - 1
    return [
        f"For want of a {things[i]} the {things[i + 1]} was lost." if i < last_thing else
        f"And all for the want of a {qualifier + ' ' if qualifier else ''}{things[0]}."
        for i in range(len(things))
    ]
