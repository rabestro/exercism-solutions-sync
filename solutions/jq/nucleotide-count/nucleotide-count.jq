reduce (.strand / "")[] as $item 
(
    {A:0,C:0,G:0,T:0}; 
    if has($item) 
    then 
        .[$item] += 1
    else 
        "Invalid nucleotide in strand" | 
        halt_error
    end
)
