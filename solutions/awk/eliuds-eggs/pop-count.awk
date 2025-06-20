{
    for (eggs = 0; $0; $0 = rshift($0, 1))
        eggs += $0 % 2
    print eggs
}
