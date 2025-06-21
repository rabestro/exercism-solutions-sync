:do
s/\(\)|\{\}|\[\]|[^][(){}]//g
t do

s/^$/true/p
t
s/.*/false/p
