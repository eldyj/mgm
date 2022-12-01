package game.Board
import libs.Ansi
import libs.Elib
import game.Figure
import game.Player
import scala.collection.mutable.ArrayBuffer

class BoardItem
  (
    var figure: Figure.Figure,
    var ability: ArrayBuffer[String],
    var empty: Boolean
  ) {
  def permit(fig: Figure.Figure): Unit = {
    if (!(ability.contains(fig.color))) {
      ability += fig.color
    }
  }

  def have_ability(fig: Figure.Figure): Boolean = {
    ability.contains(fig.color)
  }

  def display(pre: String): Unit = {
    println("figure: ")
    figure.display(pre)
    println("ability: ")

    for (ab <- ability) {
      println(pre + ab + ",")
    }

    println("empty: " + empty)
  }

  def set_figure(newfig: Figure.Figure): Boolean = {
    if (empty && (ability.contains(newfig.color))) {
      ability.clear()
      empty = false
      figure = newfig
      return true
    }

    return false
  }
}

class Board
  (
    var playground: ArrayBuffer[ArrayBuffer[BoardItem]],
    val size: Int
  ) {
  def init: Unit = {
    for (x <- (1 to size)) {
      var row: ArrayBuffer[BoardItem] = ArrayBuffer[BoardItem]()

      for (y <- (1 to size)) {
        var item: BoardItem =
          new BoardItem(Figure.figure0, ArrayBuffer[String](), true)

        if (List(1, size).contains(y)) {
          if (x == 1) {
            item.permit(Figure.figure1)
          } else if (x == size) {
            item.permit(Figure.figure2)
          }
        }

        row += item
      }

      playground += row
    }
  }
  def display: Unit = {
    print("")
    val sep: String = Ansi.style
      (
        Ansi.style
          (
            Ansi.fg("\n" + ("----" * (size + 1)) + "-\n", "black"),
            "bold"
          ),
        "overline"
      )
    val sep2: String = Ansi.style(Ansi.fg("|", "black"), "bold")
    print(sep)
    print(sep2 + " " + Ansi.fg("+", "yellow") + " " + sep2)

    for (i <- (0 to size - 1)) {
      print(Ansi.fg(s" ${i + 1} ", Player.active.color) + sep2)
    }

    for (y <- (0 to (playground.length - 1))) {
      print(sep)
      print
        (
          sep2 + Ansi.fg(s" ${Elib.abc.charAt(y)} ", Player.active.color) + sep2
        )

      for (x <- playground(y)) {
        var to_print: String = x.figure.in_color

        if (x.figure.name == ' ') {
          if (x.have_ability(Player.active)) {
            to_print = Ansi.fg("+", Player.active.color)
          }
        }

        print(s" ${to_print} ${sep2}")
      }
    }
    print(sep)
  }

  def have_ability(y: Int, x: Int, fig: Figure.Figure): Boolean = {
    playground(y)(x).have_ability(fig)
  }

  def is_empty(y: Int, x: Int): Boolean = {
    playground(y)(x).empty
  }

  def permit(y: Int, x: Int, fig: Figure.Figure): Boolean = {
    var result: Boolean = true

    if ((y >= 0 && y < size) && (x >= 0 && x < size)) {
      if (is_empty(y, x)) {
        playground(y)(x).permit(fig)
      } else {
        result = false
      }
    }

    result
  }
  def canput(fig: Figure.Figure): Boolean = {
    var result: Boolean = false

    for (y <- (0 to (size - 1))) {
      for (x <- (0 to (size - 1))) {
        if (have_ability(y, x, fig)) {
          result = true
        }
      }
    }

    result
  }

  def win: Unit = {
    if (canput(Figure.figure1) && !canput(Figure.figure2)) {
      println
        (Ansi.fg(Figure.figure1.color + " player won", Figure.figure1.color))
      System.exit(0)
    } else if (!canput(Figure.figure1) && canput(Figure.figure2)) {
      println
        (Ansi.fg(Figure.figure2.color + " player won", Figure.figure2.color))
      System.exit(0)
    } else if (!canput(Figure.figure1) && !canput(Figure.figure2)) {
      println(Ansi.fg("draw", "yellow"))
      System.exit(0)
    }
  }

  def put(y: Int, x: Int, fig: Figure.Figure): Boolean = {
    var result: Boolean = false

    if (is_empty(y, x) && have_ability(y, x, fig)) {
      playground(y)(x).set_figure(fig)
      permit(y + 1, x, fig)
      permit(y, x + 1, fig)
      permit(y - 1, x, fig)
      permit(y, x - 1, fig)
      result = true
    }

    result
  }
}
