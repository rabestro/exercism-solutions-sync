module SpaceAge

type Planet = | Earth | Mercury | Venus | Mars | Jupiter | Saturn | Uranus | Neptune

let rec age (planet: Planet) (seconds: int64): float =
    let earthAge = float seconds / 31557600.0
    match planet with
    | Earth -> earthAge
    | Mercury -> earthAge / 0.2408467
    | Venus -> earthAge / 0.61519726
    | Mars ->  earthAge / 1.8808158
    | Jupiter ->  earthAge / 11.862615
    | Saturn ->  earthAge / 29.447498
    | Uranus ->  earthAge / 84.016846
    | Neptune ->  earthAge / 164.79132
