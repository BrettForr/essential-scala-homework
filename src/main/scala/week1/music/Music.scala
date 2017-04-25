package week1.music

/**
  * Created by brett on 4/2/2017.
  */
sealed trait Music

final case class Song(parts: Seq[Music]) extends Music

