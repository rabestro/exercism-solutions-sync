(.strand1 | explode) as $a
| (.strand2 | explode) as $b
| ($a | length) as $size
| if ($b | length) != $size
  then
    "strands must be of equal length" | halt_error
  else
    [range(0; $size) | select($a[.] != $b[.])] | length
  end
