# Error Handling

Either is typically used to implement fail-fast error handling.

When using Either for error handling, we need to determine what type we
want to use to represent errors. We could use Throwable for this.

`type Result[A] = Either[Throwable, A]`

This gives us similar semantics to `scala.util.Try`. The problem, however, is
that Throwable is an extremely broad type. We have (almost) no idea about
what type of error occurred.

We could define our own type...

```$xslt
sealed trait LoginError extends Product with Serializable

final case class UserNotFound(username: String)
extends LoginError

final case class PasswordIncorrect(username: String) extends LoginError

case object UnexpectedError extends LoginError

case class User(username: String, password: String)

type LoginResult = Either[LoginError, User]
```

It gives us a fixed set of expected error types and a catch-all 
for anything else that we didn’t expect. 

We also get the safety of exhaustively checking on any pattern matching
we do:

```$xslt
// Choose error-handling behaviour based on type:

def handleError(error: LoginError): Unit =
    error match {
        case UserNotFound(u) =>
            println(s"User not found: $u")
            
        case PasswordIncorrect(u) =>
            println(s"Password incorrect: $u")
            
        case UnexpectedError =>
            println(s"Unexpected error")
}

val result1: LoginResult = User("dave", "passw0rd").asRight
// result1: LoginResult = Right(User(dave,passw0rd))

val result2: LoginResult = UserNotFound("dave").asLeft
// result2: LoginResult = Left(UserNotFound(dave))
```

## What is best?

- Is the error handling strategy in the previous examples well suited for all purposes? 
- What other features might we want from error handling?

This is an open question.

- Error recovery is important when processing large jobs. We don’t want
  to run a job for a day and then find it failed on the last element.
  
- Error reporting is equally important. We need to know what went
  wrong, not just that something went wrong.
  
- In a number of cases, we want to collect all the errors, not just the first
  one we encountered. A typical example is validating a web form. It’s a
  far beer experience to report all errors to the user when they submit
  a form than to report them one at a time
  
--------------------------------

This is a particularly striking read after experience with ZIO