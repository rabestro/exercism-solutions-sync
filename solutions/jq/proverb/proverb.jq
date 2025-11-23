.strings as $words |
[
    range(1; $words | length) |
    "For want of a \($words[. - 1]) the \($words[.]) was lost."
]
+
[
    if length > 0
    then "And all for the want of a \(.)." 
    else empty end
]
