import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class RelativeDistance {

    private final Map<String, Set<String>> relativesByPerson;

    RelativeDistance(Map<String, List<String>> familyTree) {
        relativesByPerson = Stream
                .concat(
                        familyTree.keySet().stream(),
                        familyTree.values().stream().flatMap(List::stream)
                )
                .distinct()
                .collect(Collectors.toMap(
                        person -> person,
                        person -> new HashSet<>()
                ));

        for (var entry : familyTree.entrySet()) {
            var parent = entry.getKey();
            var children = entry.getValue();
            var relatives = relativesByPerson.get(parent);
            relatives.addAll(children);
            familyTree.entrySet().stream()
                    .filter(e -> !e.getKey().equals(parent))
                    .filter(e -> !Collections.disjoint(e.getValue(), children))
                    .map(Map.Entry::getKey)
                    .forEach(relatives::add);
            familyTree.entrySet().stream()
                    .filter(e -> e.getValue().contains(parent))
                    .map(Map.Entry::getKey)
                    .forEach(relatives::add);

            for (var child : children) {
                var siblings = new HashSet<>(children);
                siblings.remove(child);
                relativesByPerson.get(child).add(parent);
                relativesByPerson.get(child).addAll(siblings);
            }
        }
    }

    int degreeOfSeparation(String personA, String personB) {
        var nextLevel = Set.of(personA);
        var visited = new HashSet<String>();

        for (var distance = 0; !nextLevel.isEmpty(); distance++) {
            if (nextLevel.contains(personB)) return distance;
            visited.addAll(nextLevel);
            nextLevel = findNextLevel(nextLevel, visited);
        }
        return -1;
    }

    private Set<String> findNextLevel(Set<String> currentLevel, Set<String> visited) {
        return currentLevel.stream()
                .map(relativesByPerson::get)
                .filter(Objects::nonNull)
                .flatMap(Set::stream)
                .filter(Predicate.not(visited::contains))
                .collect(Collectors.toSet());
    }
}
