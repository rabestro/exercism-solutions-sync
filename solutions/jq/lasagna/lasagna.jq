# The input will be null or an object that _may_ contain keys
#   actual_minutes_in_oven,
#   number_of_layers
#
# If the needed key is missing, use a default value:
#   zero minutes in oven,
#   one layer.
#
# Task: output a JSON object with keys:

.actual_minutes_in_oven //= 0
| .number_of_layers //= 1
| {
  "expected_minutes_in_oven": 40,
  "remaining_minutes_in_oven": (40 - .actual_minutes_in_oven),
  "preparation_time": (.number_of_layers * 2),
  "total_time": (.actual_minutes_in_oven + .number_of_layers * 2)
}
