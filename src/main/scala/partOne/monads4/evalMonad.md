# The Eval Monad

- `cats.Eval` is a monad that allows us to abstract over different models of evaluation. 
- We typically hear of two such models: 
  - **eager** and **lazy **
- Eval throws in a further distinction of whether or not a result is **memoized**



- eager
  - eager computations happen immediately
- lazy
  - lazy computations happen on access
- memoized
  - run once on first access
  - after which the results are cached

## Eval's Models of Evaluation

`Eval` has three subtypes

- `Now`
- `Later`
- `Always`

(see evalMonad.sc)

|  Scala   |  Cats  |     Properties     |
| :------: | :----: | :----------------: |
|   val    |  Now   |  eager, memoized   |
| lazy val | Later  |   lazy, memoized   |
|   def    | Always | lazy, not memoized |

