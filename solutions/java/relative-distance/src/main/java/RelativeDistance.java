import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

final class RelativeDistance {

    private final Map<String, Set<String>> relativesByPerson;

    RelativeDistance(Map<String, List<String>> familyTree) {
        relativesByPerson = familyTree.entrySet().stream()
                .map(entry -> Stream.concat(
                        Stream.of(entry.getKey()),
                        entry.getValue().stream()
                ).toList())
                .flatMap(family -> family.stream()
                        .map(person -> Map.entry(person, family)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.flatMapping(
                                entry -> entry.getValue().stream()
                                        .filter(not(entry.getKey()::equals)),
                                Collectors.toUnmodifiableSet()
                        )
                ));
    }

    int degreeOfSeparation(String personA, String personB) {
        if (!relativesByPerson.containsKey(personA) || !relativesByPerson.containsKey(personB)) {
            return -1;
        }

        record State(Set<String> level, Set<String> visited, int distance) {
            State next(Map<String, Set<String>> graph) {
                var nextVisited = Stream.concat(visited.stream(), level.stream())
                        .collect(Collectors.toUnmodifiableSet());
                var nextLevel = level.stream()
                        .map(graph::get)
                        .flatMap(Set::stream)
                        .filter(not(nextVisited::contains))
                        .collect(Collectors.toUnmodifiableSet());
                return new State(nextLevel, nextVisited, distance + 1);
            }
        }

        return Stream.iterate(
                        new State(Set.of(personA), Set.of(), 0),
                        state -> !state.level().isEmpty(),
                        state -> state.next(relativesByPerson)
                )
                .filter(state -> state.level().contains(personB))
                .mapToInt(State::distance)
                .findFirst()
                .orElse(-1);
    }
}
