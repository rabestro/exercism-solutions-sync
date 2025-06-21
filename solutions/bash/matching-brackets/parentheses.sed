:combine
s/\(\)//g
s/\{\}//g
s/\[\]//g
s/[^][(){}]//g
t combine

s/^$/true/p
t
s/.*/false/p
