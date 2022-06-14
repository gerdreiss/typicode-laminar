package pro.reiss

import com.raquo.laminar.api.L.*
import org.scalajs.dom

object Main:
  def main(args: Array[String]): Unit =
    render(dom.document.querySelector("#appContainer"), div("hello world"))
