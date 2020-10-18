Blocker is just a data type that wraps ExecutionContext similarly as ContextShift.


Its main purpose is to wrap a separate thread pool for running just blocking operations.

Blocker has method `blockOn`, similar to `evalOn` from ContextShift.
`blockOn`

- executes effect on a blocking thread pool, that is wrapped by Blocker
- shifts backs to thread pool of implicit ContextShift being in scope.

As another option, you can use `delay`, which doesn’t take an effect, but a block of code producing a value.
It works similarly to `IO.delay` but executes effect on blocking context.

```
def checkIfExists(path: Path): IO[Boolean] = IO(Files.exists(path))

def safeCreate(path: Path)(blocker: Blocker): IO[Unit] = for {
  //we can block on io
  alreadyExists <- blocker.blockOn(checkIfExists(path))
  //or create effect running on blocking EC
  _ <- blocker.delay[IO, Unit](Files.createFile(path)).unlessA(alreadyExists)
} yield ()
```

Unlike ContextShift there’s no default Blocker provided by IOApp,
so we will always have to create instances of Blocker by hand.

It is also advised to pass these explicitly.

```
`blockOn` - block on IO
`delay` - create effect running on blocking EC

`Blocker.apply` - returns resource which will automatically close underlying EC
```