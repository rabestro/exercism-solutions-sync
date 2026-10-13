class Bob {

    public String hey(String phrase) {
        if (phrase.isBlank()) {
            return "Fine. Be that way!";
        }
        var isQuestion = phrase.trim().endsWith("?");
        var isYelling = phrase.matches("[^a-z]*[A-Z][^a-z]*");
        if (isQuestion && isYelling) {
            return "Calm down, I know what I'm doing!";
        }
        if (isQuestion) {
            return "Sure.";
        }
        if (isYelling) {
            return "Whoa, chill out!";
        }
        return "Whatever.";
    }
}
