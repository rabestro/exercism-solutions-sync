module RnaTranscription

let dnaToRna =
    seq {
        ('G', 'C')
        ('C', 'G')
        ('T', 'A')
        ('A', 'U')
    }
    |> Map.ofSeq

let toRna (dna: string): string =
    dna
    |> Seq.map (fun x -> dnaToRna.Item x)
    |> System.String.Concat
