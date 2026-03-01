{
    if (/1[123]$|[^123]$/) suffix = "th"
    else if (/1$/) suffix = "st"
    else if (/2$/) suffix = "nd"
    else suffix = "rd"

    print $1 ", you are the " $2 suffix " customer we serve today. Thank you!"
}

