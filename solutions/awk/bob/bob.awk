BEGIN {RS = ""}

yell() && question() {
    say("Calm down, I know what I'm doing!")
}
yell() {
    say("Whoa, chill out!")
}
question() {
    say("Sure.")
}
silence() {
    say("Fine. Be that way!")
}
{
    say("Whatever.")
}

END {
    if (!NR) say("Fine. Be that way!")
}

function say(message) {print message; exit }
function yell() {return !/[[:lower:]]/ && /[[:upper:]]/}
function question() {return /\?[[:space:]]*$/}
function silence() {return /^[[:space:]]*$/}
