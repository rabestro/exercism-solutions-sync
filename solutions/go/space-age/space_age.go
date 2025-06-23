package space

import "math"

type Planet string

const earthOrbitalPeriod = 31_557_600

var orbitalPeriods = map[Planet]float64{
	"Earth":   earthOrbitalPeriod,
	"Mercury": earthOrbitalPeriod * 0.2408467,
	"Venus":   earthOrbitalPeriod * 0.61519726,
	"Mars":    earthOrbitalPeriod * 1.8808158,
	"Jupiter": earthOrbitalPeriod * 11.862615,
	"Saturn":  earthOrbitalPeriod * 29.447498,
	"Uranus":  earthOrbitalPeriod * 84.016846,
	"Neptune": earthOrbitalPeriod * 164.79132,
}

func Age(seconds float64, planet Planet) float64 {
	if orbitalPeriods[planet] == 0 {
		return math.NaN()
	}
	return seconds / orbitalPeriods[planet]
}
