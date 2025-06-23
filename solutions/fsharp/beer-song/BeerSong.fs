module BeerSong

let recite (startBottles: int) (takeDown: int) =
    let takeIt number =
        match number with
        | 0 -> "Take it down and pass it around, no more bottles of beer on the wall."
        | 1 -> "Take one down and pass it around, 1 bottle of beer on the wall."
        | _ when number > 1 -> $"Take one down and pass it around, {number} bottles of beer on the wall."
        | _ -> "Go to the store and buy some more, 99 bottles of beer on the wall."
    
    let onTheWall number =
        match number with
        | 0 -> "No more bottles of beer on the wall, no more bottles of beer."
        | 1 -> "1 bottle of beer on the wall, 1 bottle of beer."
        | _ -> $"{number} bottles of beer on the wall, {number} bottles of beer."
   
    let verse number = (onTheWall number, takeIt number)
    
    seq { startBottles .. -1 .. startBottles - takeDown }
    |> Seq.map verse
    |> Seq.toList
        