def yell: test("[[:upper:]]") and (test("[[:lower:]]") | not);
def question: test("[?][[:space:]]*$");
def silence: test("^[[:space:]]*$");
def forceful_question: yell and question;

.heyBob |

if forceful_question 
    then "Calm down, I know what I'm doing!"
elif yell 
    then "Whoa, chill out!"
elif question 
    then "Sure."
elif silence
    then "Fine. Be that way!"
else "Whatever."
end 
