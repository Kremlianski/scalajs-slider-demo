lazy val root = project
  .enablePlugins(ScalaJSPlugin)

enablePlugins(WorkbenchPlugin)


name := "Slider React"

version := "0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
  "com.github.japgolly.scalajs-react" %%% "extra" % "0.11.3"
)
libraryDependencies += "com.github.japgolly.scalacss" %%% "core" % "0.5.1"

jsDependencies ++= Seq(

  "org.webjars.bower" % "react" % "15.4.1"
    /        "react-with-addons.js"
    minified "react-with-addons.min.js"
    commonJSName "React",

  "org.webjars.bower" % "react" % "15.4.1"
    /         "react-dom.js"
    minified  "react-dom.min.js"
    dependsOn "react-with-addons.js"
    commonJSName "ReactDOM"
)


