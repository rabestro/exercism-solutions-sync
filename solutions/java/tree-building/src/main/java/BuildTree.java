import java.util.Comparator;
import java.util.List;

class BuildTree {

    private static boolean isValid(List<Record> records) {
        if (records.isEmpty()) return true;
        return records.getLast().recordId() == records.size() - 1
                && records.getFirst().recordId() == 0
                && records.getFirst().parentId() == 0
                && records.stream().skip(1).allMatch(r -> r.parentId() < r.recordId());
    }

    TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) return null;
        var sortedRecords = records.stream()
                .sorted(Comparator.comparing(Record::recordId)).toList();

        if (!isValid(sortedRecords)) {
            throw new InvalidRecordsException("Invalid Records");
        }
            
        var treeNodes = sortedRecords.stream()
                .map(Record::recordId)
                .map(TreeNode::new)
                .toArray(TreeNode[]::new);

        sortedRecords.stream().skip(1L).forEach(record -> {
            var parent = treeNodes[record.parentId()];
            var child = treeNodes[record.recordId()];
            parent.getChildren().add(child);
        });

        return treeNodes[0];
    }

}
