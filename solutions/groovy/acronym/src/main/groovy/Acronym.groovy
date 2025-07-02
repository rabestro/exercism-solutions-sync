class Acronym {

    static String abbreviate(String phrase) {

        phrase.findAll(/[\p{Alpha}']+/)
                .collect {it[0]}
                .join()
                .toUpperCase()
    }
}
