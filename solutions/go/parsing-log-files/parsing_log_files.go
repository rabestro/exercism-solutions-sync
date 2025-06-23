package parsinglogfiles

import "regexp"

var (
	validLogLine     = regexp.MustCompile(`^\[(TRC|DBG|INF|WRN|ERR|FTL)\]`)
	fieldDelimiter   = regexp.MustCompile(`<[-*~=]*>`)
	containsPassword = regexp.MustCompile(`(?i)".*password.*"`)
	endOfLine        = regexp.MustCompile(`end-of-line\d+`)
	userPattern      = regexp.MustCompile(`.*User +([a-zA-Z\d]+).*`)
)

func IsValidLine(text string) bool {
	return validLogLine.MatchString(text)
}

func SplitLogLine(text string) []string {
	return fieldDelimiter.Split(text, -1)
}

func CountQuotedPasswords(lines []string) int {
	count := 0
	for _, line := range lines {
		if containsPassword.MatchString(line) {
			count++
		}
	}
	return count
}

func RemoveEndOfLineText(text string) string {
	return endOfLine.ReplaceAllString(text, "")
}

func TagWithUserName(lines []string) []string {
	for i, line := range lines {
		lines[i] = userPattern.ReplaceAllString(line, "[USR] $1 $0")
	}
	return lines
}
