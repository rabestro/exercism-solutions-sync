package bob

import (
	"strings"
	"unicode"
)

type Remark string

func newRemark(remark string) Remark {
	return Remark(strings.TrimSpace(remark))
}

func (remark Remark) isSilence() bool {
	return remark == ""
}

func (remark Remark) isShouting() bool {
	hasLetters := strings.IndexFunc(string(remark), unicode.IsLetter) >= 0
	isUpcased := strings.ToUpper(string(remark)) == string(remark)
	return hasLetters && isUpcased
}

func (remark Remark) isQuestion() bool {
	return strings.HasSuffix(string(remark), "?")
}

func (remark Remark) isExasperated() bool {
	return remark.isShouting() && remark.isQuestion()
}
