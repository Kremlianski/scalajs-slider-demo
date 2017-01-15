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

  val slide = style(
    width(100.%%),
    height(100.vh),
    transition := "all 1s ease-in-out",
    transform := "scale(1.1)",
    position.absolute,
    top.`0`,
    left.`0`,
    minHeight(200.px),
    backgroundSize := "cover",
    backgroundPosition := "center",
    zIndex(1),
    opacity(0),
    &.attr("data-active", "true") (
      transform := "scale(1)",
      zIndex(100),
      opacity(1)
    )
  )


  val text_a = mixin(
    color.white,
    transition := "all 1s ease-in-out",
    textDecorationLine.none,
    textTransform.uppercase,
    fontSize(32.px),
    padding(20.px),
    display.block,
    cursor.pointer,
    &.hover(
      backgroundColor(c"rgba(20,20,20,0.4)"),
      color(c"#c8c8c8")
    )
  )



  val text = style(
    position.absolute,
    top(20.px),
    left(20.px),
    backgroundColor(c"rgba(0,0,0,0.6)"),

    unsafeChild("a")(
      text_a
    ),

    unsafeChild("a:link")(
      text_a
    )
  )

  val button = mixin(
    transition := "all 0.3s ease-in-out",
    color(c"rgba(255, 255, 255, 0.6)"),
    zIndex(200),
    position.absolute,
    cursor.pointer,
    bottom(20.px),
    right(20.px),
    width(20.px),
    height(20.px),
    &.hover(
      color(c"rgba(255, 255, 255, 0.6)"),
      transform := "scale(1.1)"
    )
  )

  val next = style(
    button,
    right(20.px)
  )

  val prev = style(
    button,
    left(20.px)
  )

  val rotate0 = keyframe(
    transform := "rotate(0deg)"
  )
  val rotate180 = keyframe(
    transform := "rotate(180deg)"
  )
  val rotate360 = keyframe(
    transform := "rotate(360deg)"
  )

  val rotate = keyframes(
    (0%%) -> rotate0,
    (50%%) -> rotate180,
    (100%%) -> rotate360
  )

  val loader = mixin(
    position.absolute,
    borderRadius(200.px),
    border := "6px solid #fff",
    borderTopColor(c"#fff"),
    borderLeftColor(c"#555"),
    borderRightColor(c"#555"),
    borderBottomColor(c"#fff"),
    top(50 %%),
    left(50 %%),
    animationIterationCount.infinite,
    animationName(rotate),
    animationTimingFunction.linear
  )

  val up = style(
    loader,
    height(200.px),
    width(200.px),
    animationDuration := "3s",
    marginTop(-100.px),
    marginLeft(-100.px)
  )

  val down = style(
    loader,
    height(150.px),
    width(150.px),
    animationDuration := "1s",
    marginTop(-75.px),
    marginLeft(-75.px)
  )

}




