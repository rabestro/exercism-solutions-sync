{
    command = "rev <<< \""$0"\"" | getline
    print
    close("rev")
}
