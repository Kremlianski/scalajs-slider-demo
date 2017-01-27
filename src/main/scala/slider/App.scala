package slider

import japgolly.scalajs.react._
import net.scalapro.SliderReact.Slider.SliderProps
import net.scalapro.SliderReact._
import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport


object App extends JSApp {
  implicit def string2Option(s: String) = Some(s)
  def main(): Unit ={

  }
  @JSExport
  def main1(json: js.Object | Boolean = false) {


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
          SlideContainer(elementType = "img", className = "car", classIn = "car-in", src = "img/car.png"),
          SlideContainer(elementType = "block",
            className = "block",
            children = Some(Seq(
              SlideChild(elementType = "block", classIn = "blah-in", className = "blah", text = "Blah"),
              SlideChild(elementType = "block", classIn = "blah-in", className = "blah blah1", text = "blah"),
              SlideChild(elementType = "block", classIn = "blah-in", className = "blah blah2", text = "blah")
            ))
          )
        )),
      SlideProps(
        style = Some(js.Dictionary(
          "backgroundImage" -> "url(img/step2.jpg)"
        )), containers = Seq(
          SlideContainer(elementType = "img", className = "car1", classIn = "car1-in", src = "img/car1.png"),
          SlideContainer(elementType = "block",
            className = "block1",
            classIn = "block1-in",
            children = Some(Seq(
              SlideChild(elementType = "block", classIn = "blah-1-in", className = "blah-1", text = "Blah"),
              SlideChild(elementType = "block", classIn = "blah-1-in", className = "blah-1 blah-1-1", text = "blah"),
              SlideChild(elementType = "block", classIn = "blah-1-in", className = "blah-1 blah-1-2", text = "blah")
            ))
          )
        ))

    )


    val sliderProps: SliderProps = json match {
      case x:js.Object => x.asInstanceOf[SliderPropsJS].toSliderProps
      case x if x == false => SliderProps(list = slides,
        generals = SliderGenerals(firstDelay = 1000, delay = 5000),
        preloads = Seq("img/step1.jpg", "img/car.png", "img/step2.jpg", "img/car1.png"))
    }

    val slider = Slider(sliderProps.list, sliderProps.generals, sliderProps.preloads)


    ReactDOM.render( slider, dom.document.getElementById("target"))
  }
}





