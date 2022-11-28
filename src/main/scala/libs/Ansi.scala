package libs.Ansi
def code(text: String, name: Int): String = {
  s"\u001b[${name}m${text}\u001b[0m"
}

def color_to_code(col: String): Int = {
  col match {
    case "black" | "dark" | "d"                    => 0
    case "red" | "r"                               => 1
    case "green" | "lime" | "g" | "l"              => 2
    case "yellow" | "orange" | "y" | "o"           => 3
    case "blue" | "b"                              => 4
    case "magenta" | "purple" | "pink" | "p" | "m" => 5
    case "cyan" | "lightblue" | "c" | "lb"         => 6
    case "white" | "lightgray" | "w" | "lg"        => 7
    case _                                         => 0
  }
}

def style_to_code(style: String): Int = {
  return style match {
    case "reset" | "r" | "0"                    => 0
    case "bold" | "*"                           => 1
    case "dim" | "d" | "="                      => 2
    case "italic" | "cursive" | "i" | "c" | "/" => 3
    case "underline" | "u" | "_"                => 4
    case "invisible" | "n" | " "                => 8
    case "overline" | "o" | "-"                 => 9
    case _                                      => 0
  }
}

def style(text: String, style: String): String = {
  code(text, style_to_code(style))
}

def fg(text: String, color: String): String = {
  code(text, color_to_code(color) + 30)
}

def bg(text: String, color: String): String = {
  code(text, color_to_code(color) + 40)
}
