package game.Player
import game.Figure
var active: Figure.Figure = Figure.figure1

def change(): Unit = {
  if (active == Figure.figure1) {
    active = Figure.figure2
  } else {
    active = Figure.figure1
  }
}
