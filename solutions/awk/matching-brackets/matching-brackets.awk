{
    while (gsub(/\[\]|\(\)|\{\}|[^][(){}]+/, ""));
    print !$0 ? "true" : "false"
}
