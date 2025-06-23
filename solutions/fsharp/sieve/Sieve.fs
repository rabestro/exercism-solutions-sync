module Sieve

let primes limit =
    let isPrime n =
        if n < 2 then false
        else 
            let root = int (sqrt (float n))
            seq {2..root} |> Seq.exists (fun x -> n % x = 0) |> not
    seq {2..limit} |> Seq.filter isPrime |> Seq.toList