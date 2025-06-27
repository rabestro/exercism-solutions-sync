import datetime

GIGASECOND = datetime.timedelta(seconds=1e9)


def add(moment: datetime.datetime):
    return moment + GIGASECOND
