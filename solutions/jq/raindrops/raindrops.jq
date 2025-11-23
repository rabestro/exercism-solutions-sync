.number as $i
| if $i % 3 == 0 then "Pling" else empty end
| if $i % 5 == 0 then . + "Plang" else . end
| if $i % 7 == 0 then . + "Plong" else . end
| . // $i
