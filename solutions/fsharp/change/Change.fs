module Change

open System.Collections.Generic

let change coins target =
    let changes = Dictionary<int, list<int>>()
    let amounts = Queue<int>()
    amounts.Enqueue 0
    changes[0] <- []
    
    while amounts.Count > 0 do
        let amount = amounts.Dequeue 
        let change = changes.Item amount
        .
       

let findFewestCoins coins target =
    if target < 0 then None else change coins target
