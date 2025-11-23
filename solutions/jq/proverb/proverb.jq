.strings as $words |
[
    range(1; $words | length) |
    "For want of a \($words[. - 1]) the \($words[.]) was lost."
]
+
[
    $words | first | values |
    "And all for the want of a \(.)." 
]
