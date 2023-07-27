val lambdaSyntaxExample = List(1,2,3).map(x => x * 2)

// a method that takes an Int returns a String
def normalMethod(input: Int): String = input.toString

// a "curried" method that takes an Int
// and it takes a function, that takes an Int and returns a String
def passLambdaInMethod(number: Int)(aMethod: Int => String): String = {
  // passes the number into the method
  aMethod(number)
}

// takes an int, and a method Int => String, like our method above
passLambdaInMethod(10)(normalMethod)