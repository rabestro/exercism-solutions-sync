module CircularBuffer

type CircularBuffer<'a> =
    { items: 'a list
      size: int }

type CircularBuffer<'a> with
    member this.isFull() = this.items.Length = this.size

let mkCircularBuffer size =
    { items = []
      size = size }

let clear buffer = { buffer with items = [] }

let write value (buffer: CircularBuffer<'a>) =
    if buffer.isFull()
    then failwith "Cannot write to full buffer"
    else { buffer with items = buffer.items @ [ value ] }

let forceWrite value (buffer: CircularBuffer<'a>) =
    if buffer.isFull()
    then { buffer with items = buffer.items.Tail @ [ value ] }
    else { buffer with items = buffer.items @ [ value ] }

let read buffer =
    match buffer.items with
    | [] -> failwith "Cannot read from empty buffer"
    | x :: xs -> x, { buffer with items = xs }
