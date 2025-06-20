# These variables are initialized on the command line (using '-v'):
# - num

BEGIN {
    if (num % 3 == 0) {
        Result = "Pling"
    }
    if (num % 5 == 0) {
        Result = Result "Plang"
    }
    if (num % 7 == 0) {
        Result = Result "Plong"
    }
    if (isEmpty(Result)) {
        Result = num
    }

    print Result
}

function isEmpty(result) {
    return !result
}
