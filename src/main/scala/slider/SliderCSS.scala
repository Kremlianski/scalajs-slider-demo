package slider

import scalacss.Defaults._


object SliderCSS extends StyleSheet.Inline {
  import dsl._


  style(unsafeRoot("body")(
    paddingTop(50.px))
  )


}
