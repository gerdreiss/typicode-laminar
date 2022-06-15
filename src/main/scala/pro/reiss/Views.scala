package pro.reiss

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.*

object Views:
  val $userStream = EventStream.fromFuture(TypicodeClient.getUsers)

  def renderApp: ReactiveHtmlElement[HTMLElement] =
    div(
      cls := "ui raised very padded container segment",
      h1(cls  := "ui header", i(cls := "circular users icon"), div(cls := "content", "Users")),
      div(cls := "ui divider"),
      children <-- $userStream.map {
        case Left(error)  => renderError(error)
        case Right(users) => renderUserList(users)
      }
    )

  def renderError(error: String): List[ReactiveHtmlElement[HTMLElement]] =
    div(cls := "content", p(s"Loading users failed: $error"), color := "red") :: Nil

  def renderUserList(users: List[Domain.User]): List[ReactiveHtmlElement[HTMLElement]] =
    users.map { user =>
      div(
        cls := "ui grid",
        div(cls := "four wide column", renderUser(user)),
        div(cls := "three wide column", renderAddress(user.address)),
        div(cls := "three wide column", renderGeo(user.address.geo)),
        div(cls := "six wide column", renderCompany(user.company))
      )
    }

  def renderUser(user: Domain.User): ReactiveHtmlElement[HTMLElement] =
    div(
      cls := "ui card",
      div(
        cls := "content",
        div(cls := "header", a(user.name)),
        div(cls := "description", i(cls := "envelope icon"), user.email),
        div(cls := "description", i(cls := "phone icon"), user.phone),
        div(cls := "description", i(cls := "globe icon"), user.website),
        br()
      )
    )

  def renderAddress(address: Domain.Address): ReactiveHtmlElement[HTMLElement] =
    div(
      cls := "ui card",
      div(
        cls := "content",
        div(cls := "header", i(cls := "address book icon"), "Address"),
        div(cls := "description", address.street),
        div(cls := "description", address.suite),
        div(cls := "description", address.city),
        div(cls := "description", address.zipcode)
      )
    )

  def renderGeo(geo: Domain.Geo): ReactiveHtmlElement[HTMLElement] =
    div(
      cls := "ui card",
      div(
        cls := "content",
        div(cls := "header", i(cls := "location arrow icon"), "Position"),
        div(cls := "description", geo.lat),
        div(cls := "description", geo.lng),
        br(),
        br()
      )
    )

  def renderCompany(company: Domain.Company): ReactiveHtmlElement[HTMLElement] =
    div(
      cls := "ui card",
      div(
        cls := "content",
        div(cls := "header", i(cls := "building icon"), "Company"),
        div(cls := "description", company.name),
        div(cls := "description", company.catchPhrase),
        div(cls := "description", company.bs),
        br()
      )
    )
