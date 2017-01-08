package slider

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import net.scalapro.SliderReact._
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.{Event, EventTarget, MouseEvent}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.{Any, Dynamic, JSApp, |}


object App extends JSApp {
  implicit def string2Option(s: String) = Some(s)

  def main() {

    val slides = List(
      SlideProps(style = Some(js.Dictionary(
        "backgroundColor" -> "gray"
      )), containers = Seq(
        SlideContainer(elementType = "block", className = "loader up"),
        SlideContainer(elementType = "block", className = "loader down"))
      ),
      SlideProps(
        style = Some(js.Dictionary(
          "backgroundImage" -> "url(img/step1.jpg)"
        )), containers = Seq(
          SlideContainer(elementType = "img", className = "car", classIn = "car-in", src = "img/car.png")
        )),
      SlideProps(
        style = Some(js.Dictionary(
          "backgroundImage" -> "url(img/step2.jpg)"
        )), containers = Seq(
          SlideContainer(elementType = "img", className = "car1", classIn = "car1-in", src = "img/car1.png")
        ))

    )
    ReactDOM.render(Slider(list = slides,
      generals = SliderGenerals(firstDelay = 1000, delay = 5000),
      preloads = Seq("img/step1.jpg", "img/car.png", "img/step2.jpg", "img/car1.png")),
      dom.document.getElementById("target"))
  }
}





