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
        "backgroundImage" ->
          "url(https://ununsplash.imgix.net/photo-1434828927397-62ea053f7a35?dpr=2&fit=crop&fm=jpg&h=700&q=75&w=1050"
      )), containers = Seq(SlideContainer(elementType = "block", className = "loader up"),
        SlideContainer(elementType = "block", className = "loader down"))
      ),
      SlideProps(style = Some(js.Dictionary(
        "backgroundImage" ->
          "url(https://images.unsplash.com/photo-1434394673726-e8232a5903b4?q=80&fm=jpg&s=b154bdf22a4885c8e2dd1b845c5fe996)"
      )), containers = Seq(SlideContainer(elementType = "block", className = "top", classIn = "start", text = "True!"),
        SlideContainer(elementType = "block", className = "middle", classIn = "start"),
        SlideContainer(elementType = "linked-img", className = "img", classIn = "start",
          src ="http://exxo.ru/css/logo1.png", link="http://exxo.ru/"),
        SlideContainer(elementType = "block", className = "center",
          style = Some(js.Dictionary("left" -> "300px")),
          children = Seq(SlideChild(elementType = "block", text = "Ura!"))
        )
      )
      ),
      SlideProps(style = Some(js.Dictionary(
        "backgroundImage" ->
          "url(https://images.unsplash.com/photo-1432691301971-c8b920198bd7?q=80&fm=jpg&s=d6b5970179cd2bc77c3b56165da56f80)"
      )), text = "Shore",
        link = "https://unsplash.com/intrepid"
      )

    )
    ReactDOM.render(Slider(list = slides,
      generals = SliderGenerals(firstDelay = 1000, delay = 5000),
      preloads = Seq("https://ununsplash.imgix.net/photo-1434828927397-62ea053f7a35?dpr=2&fit=crop&fm=jpg&h=700&q=75&w=1050",
      "https://images.unsplash.com/photo-1434394673726-e8232a5903b4?q=80&fm=jpg&s=b154bdf22a4885c8e2dd1b845c5fe996",
      "https://images.unsplash.com/photo-1432691301971-c8b920198bd7?q=80&fm=jpg&s=d6b5970179cd2bc77c3b56165da56f80")), dom.document.getElementById("target"))
  }
}





