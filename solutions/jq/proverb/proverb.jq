.strings as $words
| ($words | length) as $size
| if $size == 0 then []
  else
      [range(1; $size)]
      | map("For want of a \($words[. - 1]) the \($words[.]) was lost.")
      | . + ["And all for the want of a \($words[0])."]
  end
