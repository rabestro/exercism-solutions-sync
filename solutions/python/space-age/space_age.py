"""Compute years on various planets."""

EARTH_YEAR_IN_SECONDS = 31_557_600

ORBIT_RATIOS = (
    ('earth', 1.0),
    ('mercury', 0.2408467),
    ('venus', 0.61519726),
    ('mars', 1.8808158),
    ('jupiter', 11.862615),
    ('saturn', 29.447498),
    ('uranus', 84.016846),
    ('neptune', 164.79132)
)


class SpaceAge:
    """Compute years on various planets."""

    def __init__(self, seconds):
        for planet, ratio in ORBIT_RATIOS:
            age_in_years = round(seconds / EARTH_YEAR_IN_SECONDS / ratio, 2)
            setattr(self, 'on_' + planet,lambda x=age_in_years: x)
