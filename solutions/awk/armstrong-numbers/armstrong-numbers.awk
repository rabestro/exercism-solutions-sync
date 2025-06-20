# These variables are initialized on the command line (using '-v'):
# - num

BEGIN {
    print num == 0 || isAmstrongNumber(num) ? "true" : "false"
}

function isAmstrongNumber(number,   digits,power,sum,i) {
    split(number, digits, "")
    power = length(number)
    for (i in digits) sum += digits[i] ^ power
    return number == sum
}
