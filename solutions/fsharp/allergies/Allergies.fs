module Allergies

open System

type Allergen =
    | Eggs
    | Peanuts
    | Shellfish
    | Strawberries
    | Tomatoes
    | Chocolate
    | Pollen
    | Cats

let allergenCodes =
    seq {
        (Eggs, 1)
        (Peanuts, 2)
        (Shellfish, 4)
        (Strawberries, 8)
        (Tomatoes, 16)
        (Chocolate, 32)
        (Pollen, 64)
        (Cats, 128)
    }
    |> Map.ofSeq

let allergicTo codedAllergies allergen = codedAllergies &&& allergenCodes.Item allergen > 0

let list codedAllergies =
    allergenCodes
    |> Map.filter (fun allergen _ -> allergicTo codedAllergies allergen)
    |> Map.keys
    |> Seq.toList
