module Raindrops

let convert (number: int): string =
    let sound divider word =
        if number % divider = 0 then word else ""
    [ sound 3 "Pling"
      sound 5 "Plang"
      sound 7 "Plong" ]
    |> String.concat ""
    |> function
    | "" -> string number
    | sounds -> sounds
