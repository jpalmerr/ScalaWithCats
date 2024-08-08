```
object Decoders:

def enhancedInstance[A](f: HCursor => Decoder.Result[A]): Decoder[A] =  
Decoder.instance[A] { cur =>  
f(cur).leftMap(df => df.withMessage(s"${df.message} in json: ${cur.asString}"))  
}
```


Turn `Decoder.instance { cur => ??? }` into `Decoders.enhancedInstance { cur => ??? }`


And in return it will turn this `DecodingFailure at .title: Missing required field`

into

```
DecodingFailure at .title: Missing required field in json: {
"sys" : {
"id" : "7irM6EjKIilqdHwil6e6SM"
},
"slug" : "us-election-2024",
"label" : "US Election 2024"
}
```