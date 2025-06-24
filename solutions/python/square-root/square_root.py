def square_root(number: int) -> int:
    left = 0
    right = number + 1
    while left < right:
        mid = (left + right) // 2
        if mid * mid <= number:
            left = mid + 1
        else:
            right = mid
    return left - 1
