package pro.reiss

import com.raquo.laminar.api.L.*
import org.scalajs.dom

object Main:
  def main(args: Array[String]): Unit =
    render(
      dom.document.querySelector("#appContainer"),
      div(
        cls := "ui raised very padded container segment",
        h1(
          cls   := "ui header",
          i(cls   := "circular users icon"),
          div(cls := "content", "Users")
        ),
        div(cls := "ui divider")
      )
    )
