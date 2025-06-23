module CollatzConjecture

let rec steps (number: int): int option =
    let rec countStepsFor number : int =
        if number < 2 then 0
        elif number % 2 = 0 then 1 + countStepsFor (number / 2)
        else 1 + countStepsFor (3 * number + 1)
    
    if number < 1 then None
    else Some <| countStepsFor number
    

    
    
     