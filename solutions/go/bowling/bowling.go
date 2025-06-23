package bowling

import "errors"

type Game struct {
	frame      int
	frames     [10]int
	pinsLog    []int
	secondRoll bool
}

func NewGame() *Game {
	return &Game{}
}

func (g *Game) previousPins() int {
	return g.pinsLog[len(g.pinsLog)-1]
}

func (g *Game) first(frame int) int {
	return g.pinsLog[g.frames[frame]]
}

func (g *Game) second(frame int) int {
	return g.pinsLog[g.frames[frame]+1]
}

func (g *Game) third(frame int) int {
	return g.pinsLog[g.frames[frame]+2]
}

func (g *Game) isSpare(frame int) bool {
	return g.first(frame) < 10 && g.first(frame)+g.second(frame) == 10
}

func (g *Game) isStrike(frame int) bool {
	return g.first(frame) == 10
}

func (g *Game) score(frame int) int {
	if g.isStrike(frame) || g.isSpare(frame) {
		return g.first(frame) + g.second(frame) + g.third(frame)
	}
	return g.first(frame) + g.second(frame)
}

func (g *Game) isGameOver() bool {
	if g.frame < 9 {
		return false
	}
	if len(g.pinsLog)-g.frames[g.frame] < 2 {
		return false
	}
	if len(g.pinsLog)-g.frames[g.frame] > 2 {
		return true
	}
	return !(g.isSpare(9) || g.isStrike(9))
}

func (g *Game) Roll(pins int) error {
	if g.isGameOver() {
		return errors.New("cannot roll after game is over")
	}
	if pins < 0 {
		return errors.New("negative roll is invalid")
	}
	if pins > 10 || g.secondRoll && g.previousPins()+pins > 10 {
		return errors.New("pin count exceeds pins on the lane")
	}

	g.pinsLog = append(g.pinsLog, pins)
	g.secondRoll = !(g.secondRoll || pins == 10)

	if !g.secondRoll && g.frame < 9 {
		g.frame++
		g.frames[g.frame] = len(g.pinsLog)
	}
	return nil
}

func (g *Game) Score() (int, error) {
	if !g.isGameOver() {
		return 0, errors.New("score cannot be taken until the end of the game")
	}
	sum := 0
	for frame := 0; frame < 10; frame++ {
		sum += g.score(frame)
	}
	return sum, nil
}
