module LuciansLusciousLasagna

let expectedMinutesInOven = 40

let remainingMinutesInOven minutes = expectedMinutesInOven - minutes

let preparationTimeInMinutes layers = 2 * layers

let elapsedTimeInMinutes layers minutes = preparationTimeInMinutes layers + minutes
