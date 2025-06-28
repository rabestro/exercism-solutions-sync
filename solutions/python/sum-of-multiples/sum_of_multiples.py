def sum_of_multiples(limit: int, multiples: list[int]) -> int:
    """Calculate the sum of all unique multiples of given factors below a limit.

    Args:
        limit: Integer representing exclusive upper bound for calculating multiples.
        multiples: List of integers representing factors whose multiples will be summed.
            Factors of zero are ignored.

    Returns:
        Sum of all unique multiples of the given factors below the specified limit.
    """
    return sum({
        number
        for factor in multiples if factor > 0
        for number in range(factor, limit, factor)
    })
