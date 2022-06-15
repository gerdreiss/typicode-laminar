package pro.reiss

import io.circe.generic.auto.*
import sttp.client3.*
import sttp.tapir.*
import sttp.tapir.client.sttp.SttpClientInterpreter
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

import concurrent.ExecutionContext.Implicits.global
import concurrent.Future
import pro.reiss.Domain.User

object TypicodeClient:
  private val typicodeUrl = uri"https://jsonplaceholder.typicode.com"

  private val users: PublicEndpoint[Unit, String, List[User], Any] =
    endpoint
      .in("users")
      .get
      .out(jsonBody[List[Domain.User]])
      .errorOut(jsonBody[String])

  private val backend = FetchBackend()

  def getUsers: Future[Either[String, List[Domain.User]]] =
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(users, Some(typicodeUrl))
      .apply(())
      .send(backend)
      .map(_.body)
