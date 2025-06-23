package clock

import "fmt"

// Clock type represents time in HH:MM format
type Clock struct {
	hour, minute int
}

// New is a constructor for Clock
func New(hour, minute int) Clock {
	hour += minute / 60
	minute %= 60
	if minute < 0 {
		hour--
		minute += 60
	}
	hour %= 24
	if hour < 0 {
		hour += 24
	}
	return Clock{hour: hour, minute: minute}
}

// String returns a string representation of Clock
func (tm Clock) String() string {
	return fmt.Sprintf("%02d:%02d", tm.hour, tm.minute)
}

// Add increases the Clock by given minutes
func (tm Clock) Add(minutes int) Clock {
	return New(tm.hour, tm.minute+minutes)
}

// Subtract decreases the Clock by given minutes
func (tm Clock) Subtract(minutes int) Clock {
	return New(tm.hour, tm.minute-minutes)
}
