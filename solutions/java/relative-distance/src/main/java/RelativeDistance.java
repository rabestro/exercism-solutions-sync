import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        if (!relativesByPerson.containsKey(personA) || !relativesByPerson.containsKey(personB)) {
            return -1;
        }

        var currentLevel = Set.of(personA);
        var visited = new HashSet<String>();

        for (var distance = 0; !currentLevel.isEmpty(); distance++) {
            if (currentLevel.contains(personB)) {
                return distance;
            }
            visited.addAll(currentLevel);
            currentLevel = findNextLevel(currentLevel, visited);
        }
        return -1;
    }

    private Set<String> findNextLevel(Set<String> currentLevel, Set<String> visited) {
        return currentLevel.stream()
                .map(relativesByPerson::get)
                .flatMap(Set::stream)
                .filter(Predicate.not(visited::contains))
                .collect(Collectors.toSet());
    }
}
