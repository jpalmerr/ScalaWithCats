val result = for {
  a <- Some("Hello")
  _ = println("step 1")
  c <- None
  _ = println("step 2")
  b <- Some("World")
  _ = println("step 3")
} yield ()