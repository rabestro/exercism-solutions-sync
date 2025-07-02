class Isogram {
    static boolean isIsogram(String phrase) {
        !phrase.matches(/(?i).*(\p{Alpha}).*\1.*/)
    }
}