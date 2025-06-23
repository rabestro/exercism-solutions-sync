module OcrNumbers

open System

let digit =
    function
    | " _ | ||_|   " -> "0"
    | "     |  |   " -> "1"
    | " _  _||_    " -> "2"
    | " _  _| _|   " -> "3"
    | "   |_|  |   " -> "4"
    | " _ |_  _|   " -> "5"
    | " _ |_ |_|   " -> "6"
    | " _   |  |   " -> "7"
    | " _ |_||_|   " -> "8"
    | " _ |_| _|   " -> "9"
    | _ -> "?"

let line (input: list<string>) =
    let box i =
        seq { 0 .. 3 }
        |> Seq.map (fun l -> input.[l].Substring(i, 3))
        |> String.Concat

    seq { 0 .. 3 .. input.[0].Length - 1 }
    |> Seq.map box
    |> Seq.map digit
    |> String.Concat

let page (input: list<string>) =
    List.chunkBySize 4 input
    |> List.map line
    |> String.concat ","

let convert (input: list<string>) =
    let isValidSize = input.Length % 4 = 0 && input |> Seq.forall (fun x -> x.Length % 3 = 0)
    if isValidSize then page input |> Some else None
