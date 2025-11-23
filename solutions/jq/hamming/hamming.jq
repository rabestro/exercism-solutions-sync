if (.strand1 | length) != (.strand2 | length)
then
  "strands must be of equal length" | halt_error
else
  [.[]]
end
| map(explode)
| transpose
| map(select(.[0] != .[1]))
| length
