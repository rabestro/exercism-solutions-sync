"""Functions to manage a users shopping cart items."""
from typing import Iterable


def add_item(current_cart: dict[str, int], items_to_add) -> dict[str, int]:
    """Add items to a shopping cart.

    :param current_cart: Dict - the current shopping cart.
    :param items_to_add: iterable - items to add to the cart.
    :return: dict - the updated user cart dictionary.
    """

    for item in items_to_add:
        current_cart[item] = current_cart.get(item, 0) + 1
    return current_cart


def read_notes(notes: Iterable) -> dict[str, int]:
    """Create a user cart from an iterable notes entry.

    :param notes: Iterable of items to add to the cart.
    :return: dict - a user shopping cart dictionary.
    """

    return dict.fromkeys(notes, 1)


def update_recipes(ideas: dict, recipe_updates: dict) -> dict[str, int]:
    """Update the recipe ideas dictionary.

    :param ideas: dict - The "recipe ideas" dict.
    :param recipe_updates: dict - dictionary with updates for the ideas section.
    :return: dict - updated "recipe ideas" dict.
    """

    return ideas | dict(recipe_updates)


def sort_entries(cart: dict) -> dict[str, int]:
    """Sort a users shopping cart in alphabetically order.

    :param cart: dict - a users shopping cart dictionary.
    :return: dict - users shopping cart sorted in alphabetical order.
    """

    return dict(sorted(cart.items()))


def send_to_store(cart: dict, aisle_mapping: dict) -> dict[str, list]:
    """Combine users order to aisle and refrigeration information.

    :param cart: dict - users shopping cart dictionary.
    :param aisle_mapping: dict - aisle and refrigeration information dictionary.
    :return: dict - fulfillment dictionary ready to send to store.
    """
    return {
        item: [quantity, *aisle_mapping[item]]
        for item, quantity in sorted(cart.items(), reverse=True)
    }


def update_store_inventory(fulfillment_cart: dict, store_inventory: dict) -> dict[str, list]:
    """Update store inventory levels with user order.

    :param fulfillment_cart: dict - fulfillment cart to send to store.
    :param store_inventory: dict - store available inventory
    :return: dict - store_inventory updated.
    """
    return {
        item: [
            quantity - fulfillment_cart.get(item, [0])[0] or 'Out of Stock',
            aisle,
            refrigeration
        ]
        for item, (quantity, aisle, refrigeration) in store_inventory.items()
    }
