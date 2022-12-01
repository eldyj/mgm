package Mgm
import scala.collection.mutable.ArrayBuffer
import libs.*
import game.Board.*
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

  def main(arguments: Array[String]): Unit = {
    Argv.parse(arguments)
    var finalsize: Int = 5
    if (Argv.arg.contains("-help") || Argv.arg.contains("-h")) {
      println(Ansi.fg("usage","green")+":")
      println(Ansi.fg("  mgm <flags> <miniflags>"))
      println(Ansi.fg("flags","green")+":")
      var flags = List(
        "size"/*,
        "player1-color",
        "player2-color",
        "player1-figure",
        "player2-figure"*/
      )
      var flags_description = List(
        "set board size"/*,
        "set first player color",
        "set second player color",
        "set first player figure",
        "set second player figure"*/
      )
      for (i <- (0 to (flags.length - 1))) {
        println("  --"+Ansi.fg(flags(i),"green")+": " + Ansi.fg(flags_description(i),"cyan"))
      }
      println(Ansi.fg("miniflags:","green"))
      println(Ansi.fg("  -h | -help","green")+": " + Ansi.fg("","cyan"))
      System.exit(0)
    }

    if (Argv.get("size") != "") {
      try {
        finalsize = Argv.get("size").toInt
      } catch {
        case _ => {
          println
            (
              Ansi.fg
                (
                  "Unexpected board size " + Argv.get
                    ("size") + ", using " + finalsize,
                  "yellow"
                )
            )
        }
      }
    }

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
