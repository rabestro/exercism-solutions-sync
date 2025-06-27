trait BankAccount:
  def closeAccount(): Unit
  def getBalance: Option[Int]
  def incrementBalance(increment: Int): Option[Int]

class BankAccountImpl extends BankAccount:
  private var balance: Option[Int] = Some(0)

  def closeAccount(): Unit = balance = None

  def getBalance: Option[Int] = balance

  def incrementBalance(increment: Int): Option[Int] =
    synchronized:
      balance = balance.map(_ + increment)
      balance

object Bank:
  def openAccount(): BankAccount = BankAccountImpl()
