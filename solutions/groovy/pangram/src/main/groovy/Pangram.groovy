class Pangram {

    static boolean isPangram(String sentence) {
        !'abcdefghijklmnopqrstuvwxyz'.replaceAll("(?i)[$sentence ]", '')
    }
}
