package elon

import "fmt"

// Drive reduces the current battery and increase the traveled distance.
func (car *Car) Drive() {
	if car.battery < car.batteryDrain {
		return
	}
	car.battery -= car.batteryDrain
	car.distance += car.speed
}

// DisplayDistance returns the total distance traveled by the car.
func (car *Car) DisplayDistance() string {
	return fmt.Sprintf("Driven %d meters", car.distance)
}

// DisplayBattery returns the current battery level in percentage.
func (car *Car) DisplayBattery() string {
	return fmt.Sprintf("Battery at %d%%", car.battery)
}

// CanFinish checks if the car can finish the given track distance.
func (car *Car) CanFinish(trackDistance int) bool {
	return car.battery/car.batteryDrain*car.speed >= trackDistance
}
