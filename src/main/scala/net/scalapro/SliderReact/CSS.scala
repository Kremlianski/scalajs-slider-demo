package net.scalapro.SliderReact


import scalacss.Defaults._


object CSS extends StyleSheet.Inline {
  import dsl._

  style(unsafeRoot("body")(
    margin.`0`,
    padding.`0`
  )
  )

  val slider = style(
    font := "sans-serif",
    width(100.%%),
    height(100.vh),
    position.relative,
    overflow.hidden
  )


}

