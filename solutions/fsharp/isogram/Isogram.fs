module Isogram

open System.Text.RegularExpressions

let repeatingLetter = Regex("([a-z]).*?\1", RegexOptions.IgnoreCase)
let isIsogram (phrase: string): bool = not <| repeatingLetter.IsMatch phrase
