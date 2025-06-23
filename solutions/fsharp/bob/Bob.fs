module Bob

let response (input: string): string =
    let phrase = input.Trim()
    let question = phrase.EndsWith "?"
    let yelling = phrase.ToUpper() = phrase && phrase.ToLower() <> phrase
    
    match (phrase, question, yelling) with
    | _ when phrase = "" -> "Fine. Be that way!"
    | _, true, true -> "Calm down, I know what I'm doing!"
    | _, _, true -> "Whoa, chill out!"
    | _, true, _ -> "Sure."
    | _ -> "Whatever."
