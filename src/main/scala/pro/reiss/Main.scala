package pro.reiss

import com.raquo.laminar.api.L.*

object Main:
  val $userStream = EventStream.fromFuture(TypicodeClient.getUsers)

  def renderUser(user: Domain.User) =
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

  def renderAddress(address: Domain.Address) =
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

  def renderGeo(geo: Domain.Geo) =
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

  def renderCompany(company: Domain.Company) =
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

  def renderUserList(users: List[Domain.User]) =
    users.map { user =>
      div(
        cls := "ui grid",
        div(cls := "four wide column", renderUser(user)),
        div(cls := "three wide column", renderAddress(user.address)),
        div(cls := "three wide column", renderGeo(user.address.geo)),
        div(cls := "six wide column", renderCompany(user.company))
      )
    }

  def main(args: Array[String]): Unit =
    render(
      org.scalajs.dom.document.querySelector("#appContainer"),
      div(
        cls := "ui raised very padded container segment",
        h1(cls  := "ui header", i(cls := "circular users icon"), div(cls := "content", "Users")),
        div(cls := "ui divider"),
        children <-- $userStream.map {
          case Right(users) => renderUserList(users)
          case Left(error)  => div(cls := "content", p("Loading users failed")) :: Nil
        }
      )
    )
