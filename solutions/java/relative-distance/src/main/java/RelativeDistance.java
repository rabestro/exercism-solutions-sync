import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

final class RelativeDistance {

    private final Map<String, Set<String>> relativesByPerson;

    RelativeDistance(Map<String, List<String>> familyTree) {
        relativesByPerson = new HashMap<>();

        familyTree.forEach((parent, children) -> {
            relativesByPerson.computeIfAbsent(parent, _ -> new HashSet<>()).addAll(children);

            for (var child : children) {
                var childRelatives = relativesByPerson.computeIfAbsent(child, _ -> new HashSet<>());
                childRelatives.add(parent);
                childRelatives.addAll(children);
                childRelatives.remove(child);
            }
        });
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
