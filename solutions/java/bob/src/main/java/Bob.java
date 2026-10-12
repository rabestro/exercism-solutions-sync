class Bob {

    public String hey(String phrase) {
        if (phrase.isBlank()) {
            return "Fine. Be that way!";
        }
        if (phrase.matches("[^a-z]*[A-Z][^a-z]*\\?")) {
            return "Calm down, I know what I'm doing!";
        }
        if (phrase.trim().endsWith("?")) {
            return "Sure.";
        }
        if (phrase.matches("[^a-z]*[A-Z][^a-z]*")) {
            return "Whoa, chill out!";
        }
        return "Whatever.";
    }
}
