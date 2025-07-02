final class Anagram {
    final sourceWord

    Anagram(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    def find(candidates) {
        candidates.findAll { isAnagram(it) }
    }

    private isAnagram(otherWord) {
        !sourceWord.equalsIgnoreCase(otherWord)
                && toSortedChars(sourceWord) == toSortedChars(otherWord)
    }

    private static toSortedChars(word) {
        word.collect({ it.toLowerCase() }).toSorted()
    }
}