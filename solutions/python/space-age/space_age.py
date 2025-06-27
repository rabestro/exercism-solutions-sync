"""Compute years on various planets."""

EARTH_YEAR_IN_SECONDS = 31_557_600


class SpaceAge:
    """Compute years on various planets."""

    def __init__(self, seconds):
        self.seconds = seconds

    def on_planet(self, orbital_period):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / orbital_period, 2)

    def on_earth(self) -> float:
        return self.on_planet(1)

    def on_mercury(self) -> float:
        return self.on_planet(0.2408467)

    def on_jupiter(self) -> float:
        return self.on_planet(11.862615)

    def on_venus(self) -> float:
        return self.on_planet(0.61519726)

    def on_saturn(self) -> float:
        return self.on_planet(29.447498)

    def on_uranus(self) -> float:
        return self.on_planet(84.016846)

    def on_neptune(self) -> float:
        return self.on_planet(164.79132)

    def on_mars(self) -> float:
        return self.on_planet(1.8808158)
