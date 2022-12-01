package libs.Argv
import scala.collection.mutable.Map
var arg: Array[String]        = Array()
var args: Map[String, String] = Map()
def parse(arguments: Array[String]): Unit = {
  var isArgv: Boolean  = false
  var lastArgv: String = "";
  for (argument <- arguments) {
    if (argument.take(2).==("--")) {
      isArgv = true
      lastArgv = argument.drop(2)
    } else if (isArgv) {
      args.+=((lastArgv -> argument))
      isArgv = false
      lastArgv = ""
    } else {
      arg = arg.:+(argument)
    }
  }
}
def get(index: String): String = {
  var result: String = ""

  try {
    result = arg(index.toInt)
  } catch {
    case _ => {}
  }

  try {
    result = args(index)
  } catch {
    case _ => {}
  }

  return (result)
}
