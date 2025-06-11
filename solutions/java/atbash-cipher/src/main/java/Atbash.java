import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;

class Atbash {
    String encode(String plaintext) {
        return plaintext.chars()
                .filter(Character::isLetterOrDigit)
                .map(Atbash::cipher)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .replaceAll("(.{5})(?!$)", "$1 ");
    }

    String decode(String encodedText) {
        return encodedText.chars()
                .filter(Character::isLetterOrDigit)
                .map(Atbash::cipher)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static int cipher(int symbol) {
        return isLetter(symbol) ? 'a' + 'z' - toLowerCase(symbol) : symbol;
    }
}
