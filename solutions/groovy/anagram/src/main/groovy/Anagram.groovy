final class Anagram {
    final sourceWord

    Anagram(String sourceWord) {
        this.sourceWord = sourceWord.toLowerCase()
    }

    def find(candidates) {
        candidates.findAll { isAnagram(it) }
    }

    private isAnagram(word) {
        def otherWord = word.toLowerCase()
        sourceWord != otherWord &&
                sourceWord.collect().toSorted() == otherWord.collect().toSorted()
    }

}