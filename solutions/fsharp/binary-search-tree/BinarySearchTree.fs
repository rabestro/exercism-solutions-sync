module BinarySearchTree

type Node =
    { data: int
      left: Node option
      right: Node option }

let left node = node.left

let right node = node.right

let data node = node.data

let rec create items =
    match items with
    | head :: tail ->
        let numbers op =
            let branch = List.filter (op head) tail
            if branch = [] then None else Some <| create branch
        { data = head
          left = numbers (>=)
          right = numbers (<) }
    | [] -> failwith "Empty list"

let rec sortedData node: int list =
    match node.data, node.left, node.right with
    | x, None, None -> [ x ]
    | x, Some left, None ->
        List.concat
            [ sortedData left
              [ x ] ]
    | x, None, Some right ->
        List.concat
            [ [ x ]
              sortedData right ]

    | x, Some left, Some right ->
        List.concat
        [ sortedData left; [ x ]; sortedData right ]
