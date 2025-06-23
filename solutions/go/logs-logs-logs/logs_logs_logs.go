package logs

import (
	"regexp"
	"strings"
	"unicode/utf8"
)

var icons = regexp.MustCompile("[❗🔍☀]")

var applications = map[string]string{
	"❗": "recommendation",
	"🔍": "search",
	"☀": "weather",
	"":  "default",
}

// Application identifies the application emitting the given log.
func Application(log string) string {
	return applications[icons.FindString(log)]
}

// Replace replaces all occurrences of old with new, returning the modified log
// to the caller.
func Replace(log string, oldRune, newRune rune) string {
	return strings.ReplaceAll(log, string(oldRune), string(newRune))
}

// WithinLimit determines whether or not the number of characters in log is
// within the limit.
func WithinLimit(log string, limit int) bool {
	return utf8.RuneCountInString(log) <= limit
}
