BEGIN {
    Score["[AEIOULNRST]"] = 1
    Score["[DG]"] = 2
    Score["[BCMP]"] = 3
    Score["[FHVWY]"] = 4
    Score["[K]"] = 5
    Score["[JX]"] = 8
    Score["[QZ]"] = 10
}
{
    print score(toupper($0))
}

function value(letter,   range) {
    for (range in Score) if (letter ~ range) return Score[range]
}

function score(word,   wordScore,i) {
    wordScore = 0
    for (i = length(word); i > 0; --i)
        wordScore += value(substr(word, i, 1))
    return word","wordScore
}
