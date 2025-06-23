module RobotSimulator

type Direction =
    | North
    | East
    | South
    | West

type Position = int * int

type Robot =
    { direction: Direction
      position: Position }

let turnRight robot =
    match robot.direction with
    | North -> { robot with direction = East }
    | East -> { robot with direction = South }
    | South -> { robot with direction = West }
    | West -> { robot with direction = North }

let turnLeft robot =
    match robot.direction with
    | North -> { robot with direction = West }
    | East -> { robot with direction = North }
    | South -> { robot with direction = East }
    | West -> { robot with direction = South }

let advance robot =
    match robot.direction with
    | North -> { robot with position = (fst robot.position, snd robot.position + 1) }
    | East -> { robot with position = (fst robot.position + 1, snd robot.position) }
    | South -> { robot with position = (fst robot.position, snd robot.position - 1) }
    | West -> { robot with position = (fst robot.position - 1, snd robot.position) }

let act robot instruction =
    match instruction with
    | 'R' -> turnRight robot
    | 'L' -> turnLeft robot
    | _ -> advance robot

let create direction position =
    { direction = direction
      position = position }

let move (instructions: string) robot = Seq.fold act robot instructions
