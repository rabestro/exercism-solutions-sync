def add_value:
    {black: 0, brown: 1, red: 2, orange: 3, yellow: 4, 
    green: 5, blue: 6, violet: 7, grey: 8, white: 9} as $colors |
    .[0:3] |
    (10 * $colors[first] + $colors[.[1]]) * pow(10; $colors[last]) |
    {value: .}
;

def add_units:
    (.value | tostring) as $resistance |
    if $resistance | test("000000000")
        then .value /= 1e9 | .unit = "giga"
    elif $resistance | test("000000")
        then .value /= 1e6 | .unit = "mega"
    elif $resistance | test("000")
        then .value /= 1e3 | .unit = "kilo"
        else .unit = ""
    end |
    .unit += "ohms"
;

.colors | add_value | add_units 
