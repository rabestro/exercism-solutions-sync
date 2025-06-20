BEGIN {
    FS = "[- _]+"
}
{
    acronym = ""
    for (i = 1; i <= NF; ++i) {
        acronym = acronym substr($(i), 1, 1)
    }
    print toupper(acronym)
}
