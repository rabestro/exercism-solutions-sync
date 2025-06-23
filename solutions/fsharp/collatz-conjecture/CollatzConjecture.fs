module CollatzConjecture

let rec count number : int =
    if number < 1 then 1
    elif number % 2 = 0 then 1 + count number / 2
    else 1 + count 3 * number + 1
let rec steps (number: int): int option =
    match number with
    | _ when number < 0 -> None
    | 1 -> Some 1
    | _ -> Some <| count number
    
    
     