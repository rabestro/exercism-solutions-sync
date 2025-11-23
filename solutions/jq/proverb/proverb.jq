.strings as $words | $words |
[
    range(length - 1) |
    "For want of a \($words[.]) the \($words[. + 1]) was lost."
]
+
[
    first | values |
    "And all for the want of a \(.)." 
]
