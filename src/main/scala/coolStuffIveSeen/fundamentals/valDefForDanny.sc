object Test7 {
  val simpleField = {
    println("Evaluating a simple field")
    println("Im in a val you wont see me")
    42
  }

  def noParamMethod = {
    println("evaluating noParamMethod")
    println("Im in a def you will see me")
    42
  }
}

Test7 // evaluate me just to get it out way
// remember an object only gets evaluated once

Test7 // hence it doesn't happen this time

// IGNORE ABOVE ME



// im a val so i only return my final value
Test7.simpleField
// res3: Int = 42

// im a method so i do everything that happens inside me
Test7.noParamMethod
// evaluating noParamMethod
//Im in a def you will see me
//res4: Int = 42
