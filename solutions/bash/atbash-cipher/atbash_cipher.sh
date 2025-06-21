#!/usr/bin/env bash

declare -A code_table=(
  [a]=z [b]=y [c]=x [d]=w [e]=v [f]=u [g]=t [h]=s [i]=r [j]=q [k]=p [l]=o [m]=n
  [n]=m [o]=l [p]=k [q]=j [r]=i [s]=h [t]=g [u]=f [v]=e [w]=d [x]=c [y]=b [z]=a)

declare -rl phrase=${2//[^[:alnum:]]/}
declare -i len=${#phrase}

for ((i = 0; i < len; i++)); do
  symbol=${phrase:i:1}
  if [[ $symbol =~ [[:alpha:]] ]]; then
    symbol=${code_table[$symbol]}
  fi
  result+=$symbol
done

if [[ $1 == encode ]]; then
  echo "$result" | sed -e 's/.\{5\}/& /g' -e 's/ *$//'
else
  echo "$result"
fi
