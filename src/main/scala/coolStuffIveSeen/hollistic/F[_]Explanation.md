- `1`, `"a"`, `List(1,2,3)` are values
- `Int`, `String`, `List[Int]` are proper types
- `List[_]`, `Option[_]` are type constructors
    - takes a type and construct a new type
    - can be generalised with `F[_]`
`G[F[_]]` is a type constructor that takes another type constructor
    - `Functor[F[_]]`       
 
## Higher Kinded Types

A type with a type constructor (i.e. type with `[_]`)
is called a **higher kinded type**.

A type constructor is just a function that takes a type and returns a type.

 E.g. 
 
 - Type constructor `List[_]` is just a unction of type
    - `T => List[T]`
    
    e.g. - `String => List[String]`
    
We took a proper type and returned a proper type.

What if we returned another first order type?

`List[_] => WithMap[List[_]]`

or more generally

`F[_] => WithMap[F[_]]`

Higher order functions are functions that returns functions at the value level


---------------------

#### A nice explnation on why it is a good idea to use F[_] wrap on traits

https://gitter.im/typelevel/cats-effect?at=5dfa5831c62fdf33f713a072