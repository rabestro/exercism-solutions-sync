package forth;

import forth.word.BuiltIns;
import forth.word.ForthWord;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public final class ForthEngine {
    private static final Predicate<String> IS_NUMBER = Pattern.compile("-?\\d+").asMatchPredicate();
    private static final String DEFINITION_START = ":";
    private static final String DEFINITION_END = ";";

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Map<String, ForthWord> words = new HashMap<>(BuiltIns.words());

    private static List<String> tokenize(String command) {
        return Arrays.stream(command.toLowerCase(Locale.ENGLISH).split("\\s+"))
                .filter(Predicate.not(String::isEmpty))
                .toList();
    }

    private static String validatedName(String name) {
        if (IS_NUMBER.test(name)) {
            throw new IllegalArgumentException("Cannot redefine numbers");
        }
        return name;
    }

    public void evaluate(String command) {
        var tokens = tokenize(command);
        while (!tokens.isEmpty()) {
            tokens = DEFINITION_START.equals(tokens.getFirst())
                    ? defineWord(tokens)
                    : execute(tokens);
        }
    }

    public List<Integer> stack() {
        var list = new ArrayList<Integer>();
        stack.descendingIterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Defines the word at the start of the tokens and returns the tokens that follow the definition.
     */
    private List<String> defineWord(List<String> tokens) {
        int end = tokens.indexOf(DEFINITION_END);
        if (end < 0) {
            throw new IllegalArgumentException("Word definition must end with \";\"");
        }
        if (end < 2) {
            throw new IllegalArgumentException("Word definition requires a name");
        }
        var name = validatedName(tokens.get(1));
        words.put(name, compile(tokens.subList(2, end)));
        return tokens.subList(end + 1, tokens.size());
    }

    /**
     * Runs the tokens up to the next definition and returns the tokens from that definition on.
     */
    private List<String> execute(List<String> tokens) {
        int nextDefinition = tokens.indexOf(DEFINITION_START);
        int end = nextDefinition < 0 ? tokens.size() : nextDefinition;
        compile(tokens.subList(0, end)).accept(stack);
        return tokens.subList(end, tokens.size());
    }

    private ForthWord compile(List<String> tokens) {
        return tokens.stream()
                .map(this::parseToken)
                .reduce(ForthWord::then)
                .orElse(ForthWord.NO_OP);
    }

    private ForthWord parseToken(String token) {
        if (IS_NUMBER.test(token)) {
            return ForthWord.number(Integer.parseInt(token));
        }
        var word = words.get(token);
        if (word == null) {
            throw new IllegalArgumentException("No definition available for operator \"" + token + "\"");
        }
        return word;
    }
}
