# input: a floating point number
# output: the number rounded to two decimal places
def two_decimal: ((. * 100) | round) / 100;

31557600 as $earth_orbital_period |
{
    Mercury: ($earth_orbital_period * 0.2408467),
    Venus: ($earth_orbital_period * 0.61519726),
    Earth: ($earth_orbital_period),
    Mars: ($earth_orbital_period * 1.8808158),
    Jupiter: ($earth_orbital_period * 11.862615),
    Saturn: ($earth_orbital_period * 29.447498),
    Uranus: ($earth_orbital_period * 84.016846),
    Neptune: ($earth_orbital_period * 164.79132)
} as $orbital_period |

if (.planet | in($orbital_period))
then
    .seconds / $orbital_period[.planet] | 
    two_decimal
else
    "not a planet" |
    halt_error
end 
