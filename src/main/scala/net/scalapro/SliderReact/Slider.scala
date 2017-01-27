package net.scalapro.SliderReact

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react._
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLImageElement
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.concurrent.ExecutionContext.Implicits.global


case class SlideContainer(elementType: String, className: Option[String] = None,
                          style: Option[js.Dictionary[Any]] = None, children: Option[Seq[SlideChild]] = Some(Seq.empty),
                          classIn: Option[String] = None, src: Option[String] = None,
                          link: Option[String] = None, text: Option[String] = None)

case class SlideChild(elementType: String, style: Option[js.Dictionary[Any]] = None, text: Option[String] = None,
                      src: Option[String] = None, link: Option[String] = None, className: Option[String] = None,
                      classIn: Option[String] = None)

case class SliderGenerals(controlsType: Option[String] = None, delay: Int = 4000, firstDelay: Int = 500)

case class SlideProps(
                       style: Option[js.Dictionary[Any]] = None,
                       text: Option[String] = None,
                       className: Option[String] = None,
                       link: Option[String] = None,
                       containers: Seq[SlideContainer] = Seq.empty,
                       active: Boolean = false
                     )


object Slider {


  case class State(active: Int)

  val activeAtr = "data-active".reactAttr

  val Slide = ReactComponentB[SlideProps]("Slide")
    .render_P(p => {

      def renderElement(elementType: String, className: Option[String],
                        style: Option[js.Dictionary[Any]],
                        classIn: Option[String], src: Option[String],
                        link: Option[String], text: Option[String],
                        children: Seq[SlideChild] = Seq.empty): ReactElement = {
        elementType match {
          case t if t == "block" => {
            <.div(^.classSet1(className.getOrElse(""),
              classIn.getOrElse("") -> p.active),
              p.active ?= (^.style := style),
              text,
              children.map(y => {
                renderElement(y.elementType, y.className,
                  y.style, y.classIn, y.src, y.link, y.text)
              }
              )

            )
          }
          case img if img == "img" => {
            <.img(^.src := src,
              ^.classSet1(className.getOrElse(""),
                classIn.getOrElse("") -> p.active),
              p.active ?= (^.style := style))
          }
          case img if img == "linked-img" => {
            <.a(^.href := link,
              <.img(^.src := src,
                ^.classSet1(className.getOrElse(""),
                  classIn.getOrElse("") -> p.active),
                p.active ?= (^.style := style)))
          }
          case _ => <.div
        }
      }

      <.div(^.classSet1(
        "slider__slide",
        p.className.getOrElse("") -> true
        ),
        activeAtr := p.active,
        ^.style := p.style,
        <.div(^.className := "slider__slide__text",
          p.text.map(t => <.a(^.href := p.link, t))
        ),
        p.containers.map(x => {
          renderElement(x.elementType, x.className,
            x.style, x.classIn, x.src, x.link, x.text, x.children.getOrElse(Seq.empty))
        }
        )
      )
    }
    )
    .build

  class Backend($: BackendScope[SliderProps, State]) {

    var timer: Option[Int] = None

    def nextSlide = Callback {
      val active = ($.state.runNow().active + 1) match {
        case x if x < $.props.runNow().list.length => x
        case 0 => 1
        case _ => 1
      }
      $.setState(State(active)).runNow()
    }

    def previousSlide = Callback {
      val active = ($.state.runNow().active - 1) match {
        case x if x <= 0 => $.props.runNow().list.length - 1
        case x => x
      }
      $.setState(State(active)).runNow()
    }

    def render = {
      val props: SliderProps = $.props.runNow()
      val slides: List[SlideProps] = props.list
      <.div(^.className := "slider",
        slides.zipWithIndex.map(x => {
          val slide = x._1
          val index = x._2
          Slide(SlideProps(style = slide.style,
            className = slide.className,
            text = slide.text,
            active = index == $.state.runNow().active,
            link = slide.link,
            containers = slide.containers
          ))
        }), props.generals.controlsType match {
          case Some(_) => <.div(
            <.div(^.className := "slider__next",
              ^.onClick --> nextSlide,
              <.i(^.className := "fa fa-4x fa-arrow-circle-right")
            ),
            <.div(^.className := "slider__previous",
              ^.onClick --> previousSlide,
              <.i(^.className := "fa fa-4x fa-arrow-circle-left")
            )
          )
          case None => <.div()

        }

      )

    }
  }

  case class SliderProps(
                          list: List[SlideProps],
                          generals: SliderGenerals,
                          preloads: Seq[String] = Seq.empty
                        )

  private val Slider = ReactComponentB[SliderProps]("Slider")
    .initialState(State(0))
    .renderBackend[Backend]
    .componentDidUpdate(i => {
      i.$.backend.timer.map(i => window.clearTimeout(i))
      Callback(i.$.backend.timer = Option(window.setTimeout(() => i.$.backend.nextSlide.runNow(), i.$.props.generals.delay)))
    })
    .componentDidMount(i => {
      i.backend.timer.map(c => window.clearTimeout(c))

      def onLoadFuture(img: HTMLImageElement) = {
        if (img.complete) {
          Future.successful(img.src)
        } else {
          val p = Promise[String]()
          img.onload = { (e: Event) => {

            p.success(img.src)
          }
          }
          p.future
        }
      }

      def getInitialInterval(t: Int) = {
        val p = Promise[String]()
        window.setTimeout(() => p.success("!"), t)
        p.future
      }


      val futures = i.props.preloads.map(s => {
        val img = document.createElement("img").asInstanceOf[HTMLImageElement]
        img.src = s
        onLoadFuture(img)
      }) :+ getInitialInterval(i.props.generals.firstDelay)

      Callback(Future.sequence(futures).onComplete(_ => {
        i.backend.nextSlide.runNow()
      }))
    })
    .componentWillUnmount(i => Callback(i.backend.timer.map(c => window.clearTimeout(c))))
    .build

  def apply(list: List[SlideProps],
            generals: SliderGenerals = SliderGenerals(),
            preloads: Seq[String] = Seq.empty) = Slider(SliderProps(list, generals, preloads))

}
