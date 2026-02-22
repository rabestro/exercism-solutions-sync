import java.util.ArrayList;
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
        records.sort(Comparator.comparing(Record::recordId));

        if (!isValid(records)) {
            throw new InvalidRecordsException("Invalid Records");
        }

        var treeNodes = records.stream()
                .map(Record::recordId)
                .map(TreeNode::new)
                .toList();

        var orderedRecordIds = records.stream().map(Record::recordId).sorted().toList();

        for (int i = 0; i < orderedRecordIds.size(); i++) {
            TreeNode parent;
            for (TreeNode n : treeNodes) {
                if (i == n.getNodeId()) {
                    parent = n;
                    for (Record record : records) {
                        if (record.parentId() == i) {
                            for (TreeNode node : treeNodes) {
                                if (node.getNodeId() == 0) {
                                    continue;
                                }
                                if (record.recordId() == node.getNodeId()) {
                                    parent.getChildren().add(node);
                                }
                            }
                        }
                    }
                    break;
                }
            }

        }

        if (!treeNodes.isEmpty()) {
            return treeNodes.getFirst();
        }

        return null;
    }

}
