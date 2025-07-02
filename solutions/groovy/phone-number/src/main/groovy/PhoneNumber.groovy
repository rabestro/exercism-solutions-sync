class PhoneNumber {
    static final PATTERN = ~/^\+?1|\D/

    static String clean(String input) {
        def number = PATTERN.matcher(input).replaceAll('')

        if (number.length() != 10 || number =~ /^[01]|^...[01]/) {
            throw new NumberFormatException()
        }
        number
    }
}
