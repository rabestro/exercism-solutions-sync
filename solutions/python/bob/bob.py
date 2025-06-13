def response(hey_bob):
    hey_bob = hey_bob.strip()
    is_question = hey_bob.endswith('?')
    is_shouting = hey_bob.isupper()

    return "Calm down, I know what I'm doing!" if is_shouting and is_question \
        else "Whoa, chill out!" if is_shouting \
        else "Sure." if is_question \
        else "Fine. Be that way!" if hey_bob == "" \
        else "Whatever."
