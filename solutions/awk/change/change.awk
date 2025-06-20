NR == 1 {
    Dmax = split($0, Denominations)
}
$0 < 0 {
    print "target can't be negative"
    exit 1
}
NR == 2 {
    print getChange($0)
}

function getChange(amount,    i,j,size,key,value,changes,amounts,total,coin) {
    size = 1
    amounts[size] = 0
    changes[0] = ""

    for (i = 1; i <= size; ++i) {
        key = amounts[i]
        value = changes[key]
        if (key == amount) return substr(value, 2);

        for (j = 1; j <= Dmax; ++j) {
            coin = Denominations[j]
            total = key + Denominations[j]
            if (total <= amount && !(total in changes)) {
                ++size
                amounts[size] = total
                changes[total] = value" "coin
            }
        }
    }
    print "can't make target with given coins"
    exit 1
}
