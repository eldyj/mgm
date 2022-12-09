import libs.Ansi
package game.Figure {
  class Figure(val name: Char, val color: String) {
    def in_color: String = {
      return Ansi.fg(s"$name", color)
    }

    def display(pre: String): Unit = {
      print(pre + "name: " + name)
      println(name)
      print(pre + "color: " + color)
    }

  }
  val figure0: Figure = new Figure(' ', "none")
  val figure1: Figure = new Figure('#', "blue")
  val figure2: Figure = new Figure('%', "red")
}
