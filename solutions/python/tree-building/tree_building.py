from dataclasses import dataclass


@dataclass
class Record:
    record_id: int
    parent_id: int


class Node:
    def __init__(self, node_id):
        self.node_id = node_id
        self.children = []


def BuildTree(records: list[Record]) -> Node | None:
    if not records:
        return None

    n = len(records)
    ordered_records: list[Record | None] = [None] * n

    for record in records:
        record_id = record.record_id
        parent_id = record.parent_id

        if record_id < 0 or record_id >= n or ordered_records[record_id] is not None:
            raise ValueError('Record id is invalid or out of order.')
        if 0 < record_id == parent_id:
            raise ValueError('Only root should have equal record and parent id.')
        if record_id < parent_id:
            raise ValueError('Node parent_id should be smaller than its record_id.')
        ordered_records[record_id] = record

    tree_nodes: list[Node | None] = [None] * n
    tree_nodes[0] = Node(0)

    for i in range(1, n):
        child = Node(i)
        tree_nodes[i] = child
        tree_nodes[ordered_records[i].parent_id].children.append(child)

    return tree_nodes[0]
