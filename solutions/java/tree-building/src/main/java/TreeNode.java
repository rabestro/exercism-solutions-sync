import java.util.ArrayList;
import java.util.List;

record TreeNode(int nodeId, List<TreeNode> children) {

    TreeNode(int nodeId) {
        this(nodeId, new ArrayList<>());
    }

    int getNodeId() {
        return nodeId;
    }

    List<TreeNode> getChildren() {
        return children;
    }
}
