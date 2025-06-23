// Given a moment, determine the moment that would be after a gigasecond has passed.
//A gigasecond is 10^9 (1,000,000,000) seconds.
package gigasecond

// import path for the time package from the standard library
import "time"

// AddGigasecond adds a gigasecond to the given time
func AddGigasecond(t time.Time) time.Time {
	return t.Add(1_000_000_000 * time.Second)
}
