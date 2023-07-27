import cats.effect.IO
import cats.implicits._

sealed trait ApiResponse[A] {
  def toOption: Option[A] = this match {
    case ApiSuccess(a) => Some(a)
    case _             => None
  }

  override def toString: String = this match {
    case ApiSuccess(a: A)       => s"ApiSuccess(${a.toString})"
    case ApiNotFound(s: String) => s"ApiNotFound($s)"
    case ApiErr(s: String)      => s"ApiErr($s)"
  }
}

final case class ApiSuccess[A](a: A)                    extends ApiResponse[A]
final case class ApiNotFound[A](additionalInfo: String) extends Throwable with ApiResponse[A] {
  override def getMessage: String = additionalInfo
}
final case class ApiErr[A](additionalInfo: String)      extends Throwable with ApiResponse[A] {
  override def getMessage: String = additionalInfo
}

final case class Genre(key: String, description: String)
final case class SeriesParent(ccid: String, numberInGroup: Int, children: List[String])

final case class EpisodeMetaResponse(
                                      displayName: Option[String],
                                      numberInGroup: Int,
                                      genre: Genre,
                                      subGenres: List[Genre],
                                      parent: SeriesParent,
                                    )

trait GraphQLCatalogueClient {
  def fetchEpisodeAndSeriesMeta(ccid: String): IO[ApiResponse[EpisodeMetaResponse]]
}

def myThing(client: GraphQLCatalogueClient, ccid: String): IO[Option[String]] = {
  def handle(ccid: String): IO[Option[String]] = {
    client.fetchEpisodeAndSeriesMeta(ccid).map { apiResponse =>
      apiResponse.toOption.flatMap { meta =>
        if (meta.genre.key == "SPORT" && meta.subGenres.map(_.key).contains("EVENT")) {
          meta.displayName
        } else {
          if (meta.parent.children.length <= 60) {
            s"S${meta.parent.numberInGroup}, E${meta.numberInGroup} ${meta.displayName.getOrElse("")}".some
          } else {
            none
          }
        }
      }
    }
  }

  handle(ccid)
}
