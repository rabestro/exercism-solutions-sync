class Bob {
    static response(phrase) {

        switch (phrase) {
            case ~/^\s*$/:
                return "Fine. Be that way!"
            case ~/[^a-z]*[A-Z][^a-z]*\?/:
                return "Calm down, I know what I'm doing!"
            case ~/.*\?\s*/:
                return "Sure."
            case ~/[^a-z]*[A-Z][^a-z]*/:
                return "Whoa, chill out!"
            default:
                return "Whatever."
        }

    }
}