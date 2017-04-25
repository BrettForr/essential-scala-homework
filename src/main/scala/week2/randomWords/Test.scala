package week2.randomWords



/**
  * Created by am_dev on 4/11/17.
  */
object SimpleTest extends App {
  val subjects = List("Noel", "The cat", "The dog")
  val verbs = List("wrote", "chased", "slept on")
  val objects = List("the book", "the ball", "the bed")

  val allCombso = for {
    sub <- subjects
    verb <- verbs
    obj <- objects
  } yield s"$sub $verb $obj"

 // allCombso.foreach(println)

  val subjectMap = Map(
    "Noel" -> List("wrote", "chased", "slept on"),
    "The cat" -> List("meowed at", "chased", "slept on"),
    "The dog" -> List("barked at", "chased", "slept on")
  )
  val objectMap = Map(
    "wrote" -> List("the book", "the letter", "the code"),
    "chased" -> List("the ball", "the dog", "the cat"),
    "slept on" -> List("the bed"),
    "meowed at" -> List("Noel"),
    "barked at" -> List("the postman")
  )

  val betterCombos = for {
    subject <- subjects
    verb <- subjectMap(subject)
    obj <- objectMap(verb)
  } yield s"$subject $verb $obj"

  betterCombos.foreach(println)
}

class Distribution[A](private val probabilities: List[(A, Double)]) {
  implicit def toDistribution[T](l: List[(T, Double)]): Distribution[T] = new Distribution(l)

  def combine(prob: Double): Distribution[A] = probabilities.map{case (elem, p) => elem -> p*prob}
  def map[B](f: A => B): Distribution[B] = probabilities.map{case (elem, prob) => f(elem) -> prob}
  def flatMap[B](f: A => Distribution[B]): Distribution[B] = probabilities
      .flatMap{case (elem, prob) => f(elem).combine(prob).probabilities}
      .groupBy{case (elem, prob) => elem}
      .mapValues(_.map(_._2).sum)
      .toList
  def getProbabilities = probabilities
}
object Distribution {
  def uniform[A](elements: List[A]) = {
    val uniformProb = 1.0 / elements.size
    new Distribution(elements.map(e => (e, uniformProb)))
  }
}

sealed trait Coin
final case object Heads extends Coin
final case object Tails extends Coin

object CoinTest extends App {
  val fairCoin: Distribution[Coin] = Distribution.uniform(List(Heads, Tails))
  val threeFlips = for {
    c1 <- fairCoin
    c2 <- fairCoin
    c3 <- fairCoin
  } yield (c1, c2, c3)
  //threeFlips.getProbabilities.foreach(println)




}
