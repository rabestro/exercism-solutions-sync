def triplets_with_sum(number: int) -> list[list[int]]:
    return [
        [a, b, number - a - b]
        for a in range(3, number // 3 + 1)
        if (numerator := number * number - 2 * number * a) % (denominator := 2 * (number - a)) == 0
        if a < (b := numerator // denominator)
    ]
