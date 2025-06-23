module Clock

type Clock =
    { Hours: int
      Minutes: int }

let create hours minutes =
    let mutable h = hours + minutes / 60
    let mutable m = minutes % 60
    if m < 0 then
        h <- h - 1
        m <- m + 60
    h <- h % 24
    if h < 0 then h <- h + 24

    { Hours = h
      Minutes = m }

let add minutes clock = create clock.Hours (clock.Minutes + minutes)

let subtract minutes clock = create clock.Hours (clock.Minutes - minutes)

let display clock = $"%02d{clock.Hours}:%02d{clock.Minutes}"
