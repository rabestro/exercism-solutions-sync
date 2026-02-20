import java.util.ArrayList;
import java.util.Comparator;

class BuildTree {
    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        records.sort(Comparator.comparing(Record::recordId));
        var orderedRecordIds = records.stream().map(Record::recordId).sorted().toList();

        if (!records.isEmpty()) {
            if (orderedRecordIds.getLast() != orderedRecordIds.size() - 1) {
                throw new InvalidRecordsException("Invalid Records");
            }
            if (orderedRecordIds.getFirst() != 0) {
                throw new InvalidRecordsException("Invalid Records");
            }
        }

        ArrayList<TreeNode> treeNodes = new ArrayList<>();

        for (Integer orderedRecordId : orderedRecordIds) {
            for (Record record : records) {
                if (orderedRecordId == record.recordId()) {
                    if (record.recordId() == 0 && record.parentId() != 0) {
                        throw new InvalidRecordsException("Invalid Records");
                    }
                    if (record.recordId() < record.parentId()) {
                        throw new InvalidRecordsException("Invalid Records");
                    }
                    if (record.recordId() == record.parentId() && record.recordId() != 0) {
                        throw new InvalidRecordsException("Invalid Records");
                    }
                    treeNodes.add(new TreeNode(record.recordId()));
                }
            }
        }

        for (int i = 0; i < orderedRecordIds.size(); i++) {
            TreeNode parent;
            for (TreeNode n: treeNodes) {
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
