class Acronym {
    static String abbreviate(String phrase) {
        phrase.findAll(/(?<![\p{Alpha}'])\p{Alpha}/).join().toUpperCase()
    }
}
