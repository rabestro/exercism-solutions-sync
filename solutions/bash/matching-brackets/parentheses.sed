:while
s/\(\)|\{\}|\[\]|[^][(){}]//g
t while

s/^$/true/p
t
s/.*/false/p
