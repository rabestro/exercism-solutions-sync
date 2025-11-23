def normalise_date:
    if length == 10 then . + "T00:00:00" else . end + "Z"
;
def add_gigasecond:
    fromdate | . + 1e9 | todate | .[:-1]
;

.moment | normalise_date | add_gigasecond
