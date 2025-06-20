import java.util.List;
import java.util.regex.Pattern;

import static java.util.function.Predicate.not;

class ProteinTranslator {
    private static final Pattern SEQUENCE_SPLITTER = Pattern.compile("(?<=\\G.{3})");

    static String mapRnaCodonToProtein(String codon) {
        return switch (codon) {
            case "AUG" -> "Methionine";
            case "UUU", "UUC" -> "Phenylalanine";
            case "UUA", "UUG" -> "Leucine";
            case "UCU", "UCC", "UCA", "UCG" -> "Serine";
            case "UAU", "UAC" -> "Tyrosine";
            case "UGU", "UGC" -> "Cysteine";
            case "UGG" -> "Tryptophan";
            case "UAA", "UAG", "UGA" -> "STOP";
            default -> throw new IllegalArgumentException("Invalid codon");
        };
    }

    static List<String> translate(String rnaSequence) {
        return SEQUENCE_SPLITTER.splitAsStream(rnaSequence)
                .filter(not(String::isEmpty))
                .map(ProteinTranslator::mapRnaCodonToProtein)
                .takeWhile(not("STOP"::equals))
                .toList();
    }
}
