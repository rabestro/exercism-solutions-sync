module SqueakyClean

open System

let transform (c: char) : string =
    match c with
    | '-' -> "_"
    | ' ' -> ""
    | _ when System.Char.IsUpper c -> $"-{System.Char.ToLower c}"
    | _ when System.Char.IsDigit c -> ""
    | _ when System.Char.IsLower c && not (System.Char.IsAsciiLetter c) -> "?"
    | _ -> $"{c}"

let clean (identifier: string): string =
    String.collect transform identifier
