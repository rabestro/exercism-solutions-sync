module Darts

let score (x: double) (y: double): int =
    let distance = x * x + y * y |> sqrt
    match distance with
    | _ when distance <= 1 -> 10
    | _ when distance <= 5 -> 5
    | _ when distance <= 10 -> 1
    | _ -> 0
