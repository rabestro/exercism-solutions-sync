module Acronym

open System

let abbreviate (phrase: string) =
    phrase.Split [| ' '; '-'; '_' |]
    |> Array.filter (fun x -> x <> "")
    |> Array.map (fun x -> x.[0])
    |> Array.map System.Char.ToUpper
    |> String
