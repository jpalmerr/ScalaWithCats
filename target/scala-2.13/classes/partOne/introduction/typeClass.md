# Type Classes

A *type class* is an interface or API that represents some functionality we want
to implement.

 In Cats a type class is represented by a **trait** with at least one
type parameter

## Implicits


When you create a type class instance constructor using an implicit
def, be sure to mark the parameters to the method as `implicit` parameters