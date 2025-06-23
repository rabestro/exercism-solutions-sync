module KindergartenGarden

type Plant =
    | Grass
    | Clover
    | Radishes
    | Violets

let toPlant symbol =
    function
    | 'G' -> Grass
    | 'C' -> Clover
    | 'R' -> Radishes
    | 'V' -> Violets
    | _ -> failwith $"Unknown symbol '{symbol}'"
    
let plants (diagram: string) (student:string) =
    let startIndex = 2 * int (student[0] - 'A')
    
    diagram.Split('\n')
    |> Seq.collect _.Substring(startIndex, 2)
    |> Seq.map toPlant
    |> Seq.toList

