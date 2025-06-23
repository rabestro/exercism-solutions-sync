module ValentinesDay

type Approval =
    | Yes
    | No 
    | Maybe

type Cuisine =
    | Korean
    | Turkish

type Genre =
    | Crime
    | Horror
    | Romance
    | Thriller

type Activity = 
    | BoardGame
    | Chill
    | Movie of Genre
    | Restaurant of Cuisine
    | Walk of int

let rateMovie (genre: Genre): Approval =
    match genre with
    | Romance -> Yes
    | _ -> No

let rateActivity (activity: Activity): Approval = 
    match activity with 
    | BoardGame -> No
    | Chill -> No
    | Movie genre -> rateMovie genre
    | _ -> Maybe
