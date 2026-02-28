import java.util.List;

class BuildTree {

    TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) return null;

        int n = records.size();
        var orderedRecords = new Record[n];

        for (Record record : records) {
            int id = record.recordId();
            int parentId = record.parentId();

            if (id < 0 || id >= n || orderedRecords[id] != null) {
                throw new InvalidRecordsException("Invalid Records");
            }
            if (id == 0 ? parentId != 0 : parentId >= id) {
                throw new InvalidRecordsException("Invalid Records");
            }
            orderedRecords[id] = record;
        }
        var treeNodes = new TreeNode[n];
        treeNodes[0] = new TreeNode(0);

        for (int i = 1; i < n; i++) {
            var child = new TreeNode(i);
            treeNodes[i] = child;

            int parentId = orderedRecords[i].parentId();
            treeNodes[parentId].getChildren().add(child);
        }

        return treeNodes[0];
    }

}
