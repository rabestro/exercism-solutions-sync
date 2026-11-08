import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

final class RelativeDistance {

    private final Map<String, Set<String>> relativesByPerson;

    RelativeDistance(Map<String, List<String>> familyTree) {
        record Edge(String from, String to) {
        }

        relativesByPerson = familyTree.entrySet().stream()
                .map(e -> Stream.concat(Stream.of(e.getKey()), e.getValue().stream()).toList())
                .flatMap(family -> family.stream().flatMap(from -> family.stream()
                        .filter(not(from::equals))
                        .map(to -> new Edge(from, to))))
                .collect(Collectors.groupingBy(
                        Edge::from,
                        Collectors.mapping(Edge::to, Collectors.toUnmodifiableSet())
                ));
    }

    int degreeOfSeparation(String personA, String personB) {
        if (!relativesByPerson.containsKey(personA) || !relativesByPerson.containsKey(personB)) {
            return -1;
        }

        var visited = new HashSet<>(Set.of(personA));

        var distance = (int) Stream
                .iterate(Set.of(personA), not(Set::isEmpty), level -> findNextLevel(level, visited))
                .takeWhile(level -> !level.contains(personB))
                .count();

        return visited.contains(personB) ? distance : -1;
    }

    private Set<String> findNextLevel(Set<String> currentLevel, Set<String> visited) {
        return currentLevel.stream()
                .map(relativesByPerson::get)
                .flatMap(Set::stream)
                .filter(visited::add)
                .collect(Collectors.toUnmodifiableSet());
    }
}
