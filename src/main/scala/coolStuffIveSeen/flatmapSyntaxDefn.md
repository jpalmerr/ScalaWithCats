> `>>` is a Cats operator to sequence two operations where the output of the first is not needed by the second
(i.e. it is equivalent to `first.flatMap(_ => second))`.
> 