from itertools import zip_longest

def transpose(text: str) -> str:
    return '\n'.join(
        ''.join(c)
        for c in zip_longest(*text.splitlines(), fillvalue='')
    )
