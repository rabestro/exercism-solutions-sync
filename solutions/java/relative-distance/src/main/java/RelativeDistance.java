import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class RelativeDistance {

    private final Map<String, Set<String>> familyTree;

    RelativeDistance(Map<String, List<String>> familyTree) {
        this.familyTree = new HashMap<>(familyTree.size());
        familyTree.forEach((key, list) -> this.familyTree.put(key, Set.copyOf(list)));
    }

    int degreeOfSeparation(String personA, String personB) {
        var family = new HashSet<>(Set.of(personA));
        for (var distance = 1;;distance++) {
            var relatives = relatives(family);
            if (relatives.isEmpty()) return -1;
            if (relatives.contains(personB)) return distance;
            family.addAll(relatives);
        }
    }

    Set<String> relatives(Set<String> family) {
        return family.stream()
                .flatMap(this::closeFamily)
                .filter(Predicate.not(family::contains))
                .collect(Collectors.toSet());
    }

    Stream<String> children(String parent) {
        return this.familyTree.getOrDefault(parent, Set.of()).stream();
    }

    Stream<String> siblings(String person) {
        return this.familyTree.values().stream()
                .filter(s -> s.contains(person))
                .flatMap(Collection::stream)
                .filter(Predicate.not(person::equals));
    }

    Stream<String> partners(String person) {
        var children = children(person).collect(Collectors.toSet());
        return this.familyTree.entrySet().stream()
                .filter(e -> !e.getKey().equals(person))
                .filter(e -> !Collections.disjoint(e.getValue(), children))
                .map(Map.Entry::getKey);
    }

    Stream<String> parents(String person) {
        return this.familyTree.entrySet().stream()
                .filter(e -> e.getValue().contains(person))
                .map(Map.Entry::getKey);
    }

    Stream<String> closeFamily(String person) {
        return Stream.of(children(person), siblings(person), parents(person), partners(person))
                .flatMap(Function.identity());
    }
}
