import java.util.Date

import cats.data.EitherT
import cats.effect.IO
import cats.implicits._ // for recoverWith

/*
The user must have a valid subscription (this probably requires to call some billing service)
The user must not be banned from using the service (probably enforced by calling some moderation component)
 */

case class User(name: String, userName: String)

case object BadUserName extends RuntimeException("No user with that name")
case object BadPassword extends RuntimeException("Wrong password")
case class BadSubscription(expirationDate: Date)
  extends RuntimeException("Expired subscription")
case object BadUser extends RuntimeException("User is banned")

def badFindUserByUsername(username: String): IO[User] = ???
def badCheckPassword(user: User, password: String): IO[Unit] = ???
def badCheckSubscription(user: User): IO[Unit] = ???
def badCheckUserStatus(user: User): IO[Unit] = ???

def badAuthenticateUser(userName: String, password: String): IO[User] =
  for {
    user <- badFindUserByUsername(userName)
    _ <- badCheckPassword(user, password)
    _ <- badCheckSubscription(user)
    _ <- badCheckUserStatus(user)
  } yield user

/*
I can call my authenticate method and choose what to do with my user.
Eventually, I will also have to recover my various errors.
*/

badAuthenticateUser("john.doe", "foo.bar")
  .flatMap(user => IO {
    println(s"Success! $user") })
  .recoverWith({
    case BadUserName => IO { /* Do stuff ... */ }
    case BadPassword => IO { /* Do stuff ... */ }
    case BadSubscription(date) => IO { /* Do stuff ... */ }
    case BadUser => IO { /* Do stuff ... */ }
    case _ => IO {
      println("Another exception was caught !") }
  })

/*
However notice case _ : therefore we don't capture all our business errors
 */

sealed trait AuthenticationError
case object WrongUserName extends AuthenticationError
case object WrongPassword extends AuthenticationError
final case class ExpiredSubscription(expirationDate: Date) extends AuthenticationError
case object BannedUser extends AuthenticationError

def findUserByName(username: String): IO[Either[AuthenticationError, User]] = ???
def checkPassword(user: User, password: String): IO[Either[AuthenticationError, Unit]] = ???
def checkSubscription(user: User): IO[Either[AuthenticationError, Unit]] = ???
def checkUserStatus(user: User): IO[Either[AuthenticationError, Unit]] = ???

def authenticate(userName: String, password: String): EitherT[IO, AuthenticationError, User] =
  for {
    user <- EitherT(findUserByName(userName))
    _ <- EitherT(checkPassword(user, password))
    _ <- EitherT(checkSubscription(user))
    _ <- EitherT(checkUserStatus(user))
  } yield user

authenticate("", "").value.flatMap({
  case Right(user) => IO(println(user))
  case Left(BannedUser) => IO(println(s"Error! The user is banned"))
  case Left(WrongPassword) => IO(println(s"Error! Wrong password"))
  case Left(ExpiredSubscription(date)) => IO(println(s"Error! Subscription expired on $date"))
})