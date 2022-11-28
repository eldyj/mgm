package Mgm
import scala.collection.mutable.ArrayBuffer
import libs.Ansi
import libs.Elib
import game.Board.Board
import game.Board.BoardItem
import game.Player

object Mgm {

  def inp(board: Board): Board = {
    var brd: Board = board
    brd.win
    val src: String =
      scala.io.StdIn.readLine(Ansi.fg(": ", Player.active.color))

    if (List("exit", "quit", "done", "q") contains src) {
      println(Ansi.fg("draw", "yellow"))
      System.exit(0)
    }

    if (src.length > 1) {
      var y: Int = Elib.letter_to_int(src.charAt(0))
      var x: Int = (src.charAt(1).asDigit - 1)

      if (!((y >= 0) && (y < brd.size) && (x >= 0) && (x < brd.size))) {
        y = Elib.letter_to_int(src.charAt(1))
        x = (src.charAt(0).asDigit - 1)
      }

      if ((y >= 0) && (y < brd.size) && (x >= 0) && (x < brd.size)) {
        if (brd.put(y, x, Player.active)) {
          Player.change()
          brd.display
        } else {
          println
            (
              Ansi.fg("you haven't permissions or cell not empty", "yellow")
            )
        }
      } else {
        println(Ansi.fg("wrong position", "purple"))
      }
    } else {
      println(Ansi.fg("wrong position", "purple"))
    }

    brd
  }

  @main def main(len: Int): Unit = {
    var finalsize: Int = len

    if (finalsize > 9) {
      finalsize = 9
    } else if (finalsize < 3) {
      finalsize = 3
    }

    var board: Board =
      new Board(ArrayBuffer[ArrayBuffer[BoardItem]](), finalsize)
    board.init
    board.display

    while (true) {
      board = inp(board)
    }

  }
}
