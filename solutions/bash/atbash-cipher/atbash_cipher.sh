#!/usr/bin/env bash

declare -A code_table=(
  [a]=z [b]=y [c]=x [d]=w [e]=v [f]=u [g]=t [h]=s [i]=r [j]=q [k]=p [l]=o [m]=n
  [n]=m [o]=l [p]=k [q]=j [r]=i [s]=h [t]=g [u]=f [v]=e [w]=d [x]=c [y]=b [z]=a)

declare -r input=${2//[[:blank:][:punct:]]/}
declare -r phrase=${input,,}
declare -i len=${#phrase}

for ((i = 0; i < len; i++)); do
  symbol=${phrase:i:1}
  if [[ $symbol =~ [[:alpha:]] ]]; then
    symbol=${code_table[$symbol]}
  fi
  result=$result$symbol
done

if [[ $1 == encode ]]; then
  echo "$result" | sed 's/.\{5\}/& /g' | sed 's/ *$//g'
else
  echo "$result"
fi
