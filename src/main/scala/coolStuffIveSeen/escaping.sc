val x = s"""{\"query\": \"query{entries(filter: {ccid: {eq: \"y8kwsrx\"}}){historicalId, ccid}}\"}""".stripMargin
x


"""{"query": "query{entries(filter: {ccid: {eq:""" ++ """ \""" ++ "\"y8kwsrx" ++ """\""" +"\"" ++
  """}}){historicalId, ccid}}"""" ++"}"  .stripMargin


