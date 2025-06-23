import Cipher.cipherChar

import scala.util.Random

class Cipher(val key: String):
  val keyStream: Seq[Char] = LazyList.continually(key).flatten

  def encode(text: String): String = cipherText(_ + _)(text)

  def decode(text: String): String = cipherText(_ - _)(text)

  private def cipherText(algorithm: (Int, Int) => Int)(text: String): String =
    text.to(LazyList).zip(keyStream).map(cipherChar(algorithm)).mkString

object Cipher:
  private val MinKeyLength = 100
  private val MaxKeyLength = 1000000
  private val lettersInAlphabet = 'z' - 'a' + 1

  def apply(cipherKey: Option[String]): Cipher =
    require(cipherKey.forall(_.forall(_.isLower)))
    require(cipherKey.forall(_.nonEmpty))
    new Cipher(cipherKey getOrElse randomKey)

  def randomKey: String =
    val keyLength = Random.between(MinKeyLength, MaxKeyLength)
    Random.alphanumeric.filter(_.isLower).take(keyLength).mkString

  def cipherChar(algorithm: (Int, Int) => Int)(symbol: Char, key: Char): Char =
    val keyIndex = key - 'a'
    val symbolIndex = symbol - 'a'
    val encodedSymbol = algorithm(symbolIndex, keyIndex) % lettersInAlphabet + 'a'
    encodedSymbol.toChar
