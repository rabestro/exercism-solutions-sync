package booking

import "time"

// Schedule returns a time.Time from a string containing a date.
func Schedule(date string) time.Time {
	appointment, _ := time.Parse("1/2/2006 15:04:05", date)
	return appointment
}

// HasPassed returns whether a date has passed.
func HasPassed(date string) bool {
	appointment, _ := time.Parse("January 2, 2006 15:04:05", date)
	return time.Now().After(appointment)
}

// IsAfternoonAppointment returns whether a time is in the afternoon.
func IsAfternoonAppointment(date string) bool {
	appointment, _ := time.Parse("Monday, January 2, 2006 15:04:05", date)
	hour, _, _ := appointment.Clock()
	return hour >= 12 && hour < 18
}

// Description returns a formatted string of the appointment time.
func Description(date string) string {
	const layout = "You have an appointment on Monday, January 2, 2006, at 15:04."
	return Schedule(date).Format(layout)
}

// AnniversaryDate returns a Time with this year's anniversary.
func AnniversaryDate() time.Time {
	return time.Date(time.Now().Year(), time.September, 15, 0, 0, 0, 0, time.UTC)
}
