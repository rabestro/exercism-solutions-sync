{
    while (gsub(/\[\]|\(\)|\{\}|[[:space:]]/, ""));
    print !$0 ? "true" : "false"
}
