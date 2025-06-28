ACTIONS = ('wink', 'double blink', 'close your eyes', 'jump')


def commands(binary_str: str) -> list[str]:
    reverse_bit, *action_bits = binary_str
    handshake = [action for action, bit in zip(ACTIONS, reversed(action_bits)) if bit == '1']
    return handshake if reverse_bit == '0' else handshake[::-1]
