import cats.effect.IO
import cats.implicits._
import org.http4s.Uri

case class DbResponse(fieldOne: String, fieldTwo: String)
case class TypeOne(mutualId: String, sharedField: String, storeParam: Int, url: String)
case class TypeTwo(mutualId: String, sharedField: String, dbResponse: DbResponse, uriFromUrl: Uri)

def dbCall(mutualId: String): IO[DbResponse] = IO(DbResponse("dbres1", "dbres2"))
def toUri(url: String): IO[Uri]

def convert(typeOnes: List[TypeOne]): IO[List[TypeTwo]] = {
  typeOnes.traverse { typeOne =>
    toUri(typeOne.url).flatMap { uri =>
      dbCall(typeOne.mutualId).map { dbRes =>
        TypeTwo(typeOne.mutualId, typeOne.sharedField, dbRes, uri)
      }
    }

  }
}