module DifferenceOfSquares

let squareOfSum (number: int): int =
    let sum = number * (number + 1) / 2
    sum * sum

let sumOfSquares (number: int): int = number * (number + 1) * (2 * number + 1) / 6

let differenceOfSquares (number: int): int = squareOfSum number - sumOfSquares number