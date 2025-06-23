module ArmstrongNumbers

let isArmstrongNumber (number: int): bool =
    let digits = number.ToString().ToCharArray() |> Array.map (fun c -> int (string c))
    let length = digits.Length
    let armstrongSum = digits |> Array.sumBy (fun d -> float d ** length)
    armstrongSum = number