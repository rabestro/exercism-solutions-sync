module Transpose

let transpose input =
    input |> List.map Seq.toList |> List.transpose |> List.map (String.concat "") 
    
    