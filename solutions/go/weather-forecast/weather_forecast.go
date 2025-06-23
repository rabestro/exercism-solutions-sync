// Package weather forecast the current weather condition of various cities in Goblinocus.
package weather

// CurrentCondition represents the current weather condition.
var CurrentCondition string

// CurrentLocation represents the current city location.
var CurrentLocation string

// Forecast forecasts the current weather condition of the city of Goblinocus.
func Forecast(city, condition string) string {
	CurrentLocation, CurrentCondition = city, condition
	return CurrentLocation + " - current weather condition: " + CurrentCondition
}
