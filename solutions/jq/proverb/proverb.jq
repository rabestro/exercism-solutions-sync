.strings as $words
| ($words | length) as $size
| if $size == 0 then []
  else
      [range(0; $size - 1)]
      | map("For want of a \($words[.]) the \($words[. + 1]) was lost.")
      | . + ["And all for the want of a \($words[0])."]
  end
