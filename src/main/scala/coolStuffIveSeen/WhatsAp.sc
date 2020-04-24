/*
http://justinhj.github.io/2020/04/04/whats-ap.html
 */

/*
Remember, the goal of functional programming is to do most of our work using pure functions.
We use structures like Functors and Monads to manage effects (things that are not pure);
letting us use pure functions in an effectful world.
 */

// Functor

/*
The Functor type class gives us the power to take a pure function
like a => a + 1 and apply it to the value inside an effect.
 */

List(1,2,3).map(a => a + 1)
// List[Int] = List(2, 3, 4)

Option(1).map(a => a + 1)
// Option[Int] = Some(2)

/*
Functor gives you the ability to reach inside an effect,
apply a pure function to the value inside there,
and wrap it up inside an effect of the same type.
 */

// Monad

import cats._
import cats.implicits._

import scala.concurrent.Future

1.pure[List]
// res: List[Int] = List(1)

1.pure[Option]
// res: Option[Int] = Some(1)

/*

 */

import scala.concurrent.ExecutionContext

implicit val ec = ExecutionContext.global

case class User(accountId: String)
case class AccountStatus(user: User)

val user = User("user")

def getUser(email: String): Future[User] = user.pure[Future]

def getAccountStatus(id: String): Future[AccountStatus] = AccountStatus(user).pure[Future]

val accountStatus: Future[Future[AccountStatus]] = getUser("bob@google.com")
  .map(user => getAccountStatus(user.accountId))

val accountStatus2: Future[AccountStatus] = getUser("bob@google.com")
  .flatMap(user => getAccountStatus(user.accountId))
