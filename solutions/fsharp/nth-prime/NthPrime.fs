module NthPrime

let calculatePrime nth =
    let primes = ResizeArray<int>([ 2 ])
    let isPrime number = primes |> Seq.forall (fun p -> number % p <> 0)

    seq { 3 .. 2 .. 9999999 }
    |> Seq.takeWhile (fun _ -> primes.Count < nth)
    |> Seq.filter isPrime
    |> Seq.iter primes.Add

    primes.Item(nth - 1)

let prime nth: int option =
    if nth < 1 then None else Some <| calculatePrime nth
