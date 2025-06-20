BEGIN {
    FS = ","
    Number["ones"] = 1
    Number["twos"] = 2
    Number["threes"] = 3
    Number["fours"] = 4
    Number["fives"] = 5
    Number["sixes"] = 6
}
{
    Score = 0
    Category = $NF
    delete Dice
    while (--NF) Dice[$NF]++
}
Category ~ "ones|twos|threes|fours|fives|sixes" {
    side = Number[Category]
    Score = Dice[side] ? side * Dice[side] : 0
}
Category == "full house" && length(Dice) == 2 {
    Score = full_house()
}
Category == "yacht" {
    Score = length(Dice) == 1 ? 50 : 0
}
Category == "four of a kind" {
    Score = four_of_a_kind()
}
Category == "little straight" && length(Dice) == 5 && !Dice[6] {
    Score = 30
}
Category == "big straight"  && length(Dice) == 5 && !Dice[1] {
    Score = 30
}
Category == "choice" {
    for (key in Dice)
        Score += key * Dice[key]
}
{
    print Score
}
function full_house(   score,key) {
    if (length(Dice) != 2) return 0
    for (key in Dice) {
        if (Dice[key] < 2 || Dice[key] > 3) return 0
        score += key * Dice[key]
    }
    return score
}
function four_of_a_kind(   key) {
    for (key in Dice)
        if (Dice[key] > 3) return key * 4
    return 0
}