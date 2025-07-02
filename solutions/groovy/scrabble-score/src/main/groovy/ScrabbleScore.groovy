class ScrabbleScore {
    static Score = [AEIOULNRST: 1, DG: 2, BCMP: 3, FHVWY: 4, K: 5, JX: 8, QZ: 10]

    static scoreWord(String word) {
        Score.collect { (word.toUpperCase() =~ "[${it.key}]").size() * it.value }.sum()
    }
}
