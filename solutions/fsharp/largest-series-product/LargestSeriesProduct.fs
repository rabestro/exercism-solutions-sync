module LargestSeriesProduct

open System

let largestProduct (input: string) seriesLength: int option =
    let series start = input |> Seq.skip start |> Seq.take seriesLength
    let product digits = Seq.map (fun x -> x - '0') digits |> Seq.map int |> Seq.reduce (*)
    let isValidInput = Seq.forall Char.IsDigit input && input.Length > 0
    let isValidSpan = seriesLength > 0 && seriesLength <= input.Length

    if isValidInput && isValidSpan then
        seq { 0 .. input.Length - seriesLength }
        |> Seq.maxBy (series >> product)
        |> Some
    else
        None
