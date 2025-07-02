class Acronym {

    static String abbreviate(String phrase) {
        phrase.replaceAll(/\b_?(\w)[^-\s]*[- ]*/, '$1').toUpperCase()
    }
}
