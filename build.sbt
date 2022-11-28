/*val scala3Version: String = "3.2.1"

lazy val root = {
  (project in file(".")).settings(
    name := "Mgm",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
}
*/
scalaVersion := "3.1.3"
enablePlugins(ScalaNativePlugin)
