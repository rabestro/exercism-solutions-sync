import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class RelativeDistance {

    private final Map<String, Set<String>> familyTree;
    private Set<String> visited;

    RelativeDistance(Map<String, List<String>> familyTree) {
        this.familyTree = new HashMap<>(familyTree.size());
        familyTree.forEach((key, list) -> this.familyTree.put(key, Set.copyOf(list)));
    }

    int degreeOfSeparation(String personA, String personB) {
        var nextLevel = Set.of(personA);
        visited = new HashSet<>();

        for (var distance = 0; !nextLevel.isEmpty(); distance++) {
            if (nextLevel.contains(personB)) return distance;
            visited.addAll(nextLevel);
            nextLevel = findNextLevel(visited);
        }
        return -1;
    }

    Set<String> findNextLevel(Set<String> family) {
        return family.stream()
                .flatMap(this::neighbors)
                .filter(Predicate.not(visited::contains))
                .collect(Collectors.toSet());
    }

    Stream<String> childrenOf(String parent) {
        return this.familyTree.getOrDefault(parent, Set.of()).stream();
    }

    Stream<String> siblingsOf(String person) {
        return this.familyTree.values().stream()
                .filter(s -> s.contains(person))
                .flatMap(Collection::stream)
                .filter(Predicate.not(person::equals));
    }

    Stream<String> coParentsOf(String person) {
        var children = childrenOf(person).collect(Collectors.toSet());
        return this.familyTree.entrySet().stream()
                .filter(e -> !e.getKey().equals(person))
                .filter(e -> !Collections.disjoint(e.getValue(), children))
                .map(Map.Entry::getKey);
    }

    Stream<String> parentsOf(String person) {
        return this.familyTree.entrySet().stream()
                .filter(e -> e.getValue().contains(person))
                .map(Map.Entry::getKey);
    }

    Stream<String> neighbors(String person) {
        return Stream.of(childrenOf(person), siblingsOf(person), parentsOf(person), coParentsOf(person))
                .flatMap(Function.identity());
    }
}
