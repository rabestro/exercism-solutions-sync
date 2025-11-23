def proverb(words):
    [range(0; (words | length) - 1)]
    | map("For want of a \(words[.]) the \(words[. + 1]) was lost.")
    | . + ["And all for the want of a \(words[0])."]
;

if (.strings | length) == 0
then []
else proverb(.["strings"])
end
