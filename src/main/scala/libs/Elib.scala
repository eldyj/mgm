package libs.Elib
val abclow: String = "abcdefghilklmnopqrstuvwxyz"
val abc: String    = abclow + abclow.toUpperCase()

def letter_to_int(ch: Char): Int = {
  abc.indexOf(ch)
}
