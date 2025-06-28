def sum_of_multiples(limit: int, multiples: list[int]) -> int:
    return sum({
        number
        for factor in multiples if factor > 0
        for number in range(factor, limit, factor)
    })
