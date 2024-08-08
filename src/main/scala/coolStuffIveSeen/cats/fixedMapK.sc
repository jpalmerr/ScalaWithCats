import cats.tagless._
import cats.~>
import cats.tagless.implicits._

trait Foo[F[_]]
object Foo {
  implicit val functorK: FunctorK[Foo] = Derive.functorK[Foo]
  def mapK[F1[_], F2[_]](foo: Foo[F1], fk: F1 ~> F2): Foo[F2] = foo.mapK(fk)
}

trait Bar[F[_], G[_]]
object Bar {
  implicit def functorK[F[_]]: FunctorK[Bar[F, *[_]]] = ???
  def mapK[F[_], G1[_], G2[_]](bar: Bar[F, G1], fk: G1 ~> G2): Bar[F, G2] = bar.mapK(fk)
}
