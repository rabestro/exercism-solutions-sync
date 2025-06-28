def find(search_list: list[int], value: int) -> int:
    """
    Performs a binary search on a sorted list to find the index of a specified value.

    :param search_list: A list of integers sorted in increasing order to be searched.
    :param value: The integer value to search for within the provided list.
    :return: The index of the value in the list if it exists.
    :raises ValueError: If the value is not found in the list.
    """
    low = 0
    high = len(search_list) - 1
    while low <= high:
        mid = (low + high) // 2
        guess = search_list[mid]
        if guess == value:
            return mid
        if guess > value:
            high = mid - 1
        else:
            low = mid + 1

    raise ValueError('value not in array')
