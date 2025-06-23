module ProteinTranslation

let toPolypeptide (codons: char []) =
    match System.String(codons) with
    | "AUG" -> "Methionine"
    | "UUC"
    | "UUU" -> "Phenylalanine"
    | "UUA"
    | "UUG" -> "Leucine"
    | "UCU"
    | "UCC"
    | "UCA"
    | "UCG" -> "Serine"
    | "UAU"
    | "UAC" -> "Tyrosine"
    | "UGC"
    | "UGU" -> "Cysteine"
    | "UGG" -> "Tryptophan"
    | _ -> "STOP"

let proteins (rna: string) =
    rna
    |> Seq.chunkBySize 3
    |> Seq.map toPolypeptide
    |> Seq.takeWhile (fun x -> x <> "STOP")
    |> Seq.toList
