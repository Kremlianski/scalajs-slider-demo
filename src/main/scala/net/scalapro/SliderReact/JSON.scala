package net.scalapro.SliderReact

import net.scalapro.SliderReact.Slider.SliderProps
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


@ScalaJSDefined
trait SliderPropsJS extends js.Object {
  val list: js.Array[SlidePropsJS]
  val generals: SliderGeneralsJS
  val preloads: js.Array[String]
}

object SliderPropsJS {
  implicit class SliderPropsOps(val self: SliderPropsJS) extends AnyVal {
    def toSliderProps: SliderProps = {
      SliderProps(
        self.list.map(_.toSlideProps).toList,
        self.generals.toSliderGenerals,
        self.preloads.toSeq
      )
    }
  }
}


@ScalaJSDefined
trait SlidePropsJS extends js.Object {
  val style: js.UndefOr[js.Dictionary[Any]]
  val text: js.UndefOr[String]
  val className: js.UndefOr[String]
  val link: js.UndefOr[String]
  val containers: js.Array[SlideContainerJS]
  val active: js.UndefOr[Boolean]
}

object SlidePropsJS {
  implicit class SlidePropsOps(val self: SlidePropsJS) extends AnyVal {
    def toSlideProps: SlideProps = {
      SlideProps(
        self.style.toOption,
        self.text.toOption,
        self.className.toOption,
        self.link.toOption,
        self.containers.map(_.toSlideContainer).toSeq,
        self.active.toOption.getOrElse(false)
      )
    }
  }
}





@ScalaJSDefined
trait SliderGeneralsJS extends js.Object {
  val controlsType: js.UndefOr[String]
  val delay: Int
  val firstDelay: Int
}

object SliderGeneralsJS {
  implicit class SliderGeneralsOps(val self: SliderGeneralsJS) extends AnyVal {
    def toSliderGenerals: SliderGenerals = {
      SliderGenerals(
        self.controlsType.toOption,
        self.delay,
        self.firstDelay
      )
    }
  }
}



@ScalaJSDefined
trait SlideChildJS extends js.Object {
  val elementType: String
  val style: js.UndefOr[js.Dictionary[Any]]
  val text: js.UndefOr[String]
  val src: js.UndefOr[String]
  val link: js.UndefOr[String]
  val className: js.UndefOr[String]
  val classIn: js.UndefOr[String]
}

object SlideChildJS {
  implicit class SlideChildOps(val self: SlideChildJS) extends AnyVal {
    def toSlideChild: SlideChild = {
      SlideChild(
        self.elementType,
        self.style.toOption,
        self.text.toOption,
        self.src.toOption,
        self.link.toOption,
        self.className.toOption,
        self.classIn.toOption
      )
    }
  }
}

@ScalaJSDefined
trait SlideContainerJS extends js.Object {
  val elementType: String
  val className: js.UndefOr[String]
  val style: js.UndefOr[js.Dictionary[Any]]
  val children: js.UndefOr[js.Array[SlideChildJS]]
  val classIn: js.UndefOr[String]
  val src: js.UndefOr[String]
  val link: js.UndefOr[String]
  val text: js.UndefOr[String]
}

object SlideContainerJS {
  implicit class SlideContainerOps(val self: SlideContainerJS) extends AnyVal {
    def toSlideContainer: SlideContainer = {
      SlideContainer(
        self.elementType,
        self.className.toOption,
        self.style.toOption,
        self.children.toOption.map(_.map(_.toSlideChild)),
        self.classIn.toOption,
        self.src.toOption,
        self.link.toOption,
        self.text.toOption
      )
    }
  }
}