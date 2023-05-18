import scala.swing._
package BlackJack {

  object Game{
    def main(args: Array[String]): Unit = {
      println("Hello, world!")
      var player = new Player(false)
      var dealer = new Player(true)
      val game = new GameBoard(new BlackJackGame(player,dealer), new Player(dealer = true))
      

    }
  }

}

