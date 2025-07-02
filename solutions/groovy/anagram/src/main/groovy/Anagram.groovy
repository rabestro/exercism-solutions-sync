final class Anagram {
    final int[] sortedChars
    final sourceWord

    Anagram(String word) {
        sourceWord = word;
        sortedChars = toSortedChars(sourceWord);
    }

    def find(candidates) {
        candidates.findAll { isAnagram(it) }
    }

    private isAnagram(otherWord) {
        !sourceWord.equalsIgnoreCase(otherWord)
                && Arrays.equals(sortedChars, toSortedChars(otherWord));
    }

    private static toSortedChars(word) {
        word.chars().map(Character::toLowerCase).sorted().toArray()
    }
}