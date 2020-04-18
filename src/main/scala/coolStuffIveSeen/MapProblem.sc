 val optHeaders: Option[Map[String, Seq[String]]] = Some(Map(
    "key1" -> Seq("returnme1", "value 2"),
    "key2" -> Seq("returnme2", "value 4"),
    "key3" -> Seq()
  )
  )
// val optHeaders: Option[Map[ String, Seq[String]]] = None

  def extract(key: String): Option[String] = {
    for {
      headers <- optHeaders
      keyExist = headers.contains(key)
      values = if (keyExist) headers(key) else Seq.empty // TODO: improve me
      targetEl <- values.headOption
    } yield targetEl
  }

 extract("key1")
 println("----------")
 extract("key3")
 println("-----------")
 extract("key4")


 /*
 fwiw to get into this problem is likely from bad design
 - however created an interesting problem
  */