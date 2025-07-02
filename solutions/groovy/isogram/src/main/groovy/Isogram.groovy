class Isogram {
    static boolean isIsogram(String phrase) {
        !(phrase =~ /(?i)(\p{Alpha}).*\1/)
    }
}