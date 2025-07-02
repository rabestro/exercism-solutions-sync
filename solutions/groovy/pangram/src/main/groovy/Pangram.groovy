class Pangram {

    static boolean isPangram(String sentence) {
        sentence.toLowerCase().toSet().containsAll 'a'..'z'
    }
}
