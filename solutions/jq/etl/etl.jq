.legacy |
to_entries |
.[] |
(.key | tonumber) as $score |

reduce (.value[] | ascii_downcase) as $letter 
(
    {}; .[$letter] = $score
) |
reduce . as $i ({}; . += $i)
