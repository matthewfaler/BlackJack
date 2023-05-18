import scala.swing._
import javax.swing.ImageIcon
import java.awt.Color
import javax.swing.JButton

package BlackJack {

  import java.{awt, util}


  class GameBoard(var game: BlackJackGame, var player: Player)  {
    val dialog = new Label("Game Dialog")
    val playerScore = new Label("Player Score: " + game.player.score)
    val dealerScore = new Label("Dealer Score: " + game.dealer.score)
    private var playerBox = new BoxPanel(Orientation.Vertical) {
      contents += new Label("Player Box")

    }

    var la = new Label {
      icon = new ImageIcon(new Card(0, "FaceDown").imagePath)
    }


    private var dealerBox = new BoxPanel(Orientation.Vertical)
    var frame = new Frame {


      preferredSize = new Dimension(700, 650)
      title = "Hello world"
      contents = new FlowPanel {


        contents += new BoxPanel(Orientation.Vertical) {
          contents +=  Button("Hit") {
            if (game.bet == 0) {
              println("No bet entered. Player loses!")
              updateDialog("No bet entered. Player loses! New round started.")
              startRound()
            } else {
            println("Hit")
            updateDialog("Player drew a card!")

            drawPlayerCard(game.player.hit(game.deck.draw()))
            if (game.evaluateHand()) {


              println("Player Bust")
              game.resetHands()
              updateDialog("The player bust! Start a new round to continue!")
            } else if (game.player.hand == 21) {
              //blackjack logic
              println("BLACKJACK")
              updateDialog("BLACKJACK the player wins.")
              game.resetHands()
            }
          }
          }

          contents += Button("Stay") {
            dealerBox.contents -= la
            println("UPDATED")
            Thread.sleep(500)
            drawDealerCard(game.dealer.hit(game.deck.draw()))

            while (game.dealer.dealerLogic()) {
              updateDialog("Player stayed! Dealer conduting turn.")
              drawDealerCard(game.dealer.hit(game.deck.draw()))
            }
            updateDialog("The Dealer decided to stay!")
            Thread.sleep(1000)

            if (game.compareHand() ) {
              updateDialog("The Dealer Won!")
            } else  {
              updateDialog("The Player Won!")
            }

            //startRound()
            //getState()
          }
          contents += Button("Start New Round") {
            updateDialog("Starting new round")
            startRound()

          }
          contents += dialog
          contents += playerScore
          contents += dealerScore
        }
        contents += playerBox
        contents += dealerBox

        contents += new GridPanel(2,2) {
          contents += Button("$10 Bet") {
            if(game.player.score >= 10) { //to make sure that the user has sufficient funds (not working)
              game.bet = 10
              println("$10 was bet.")
              updateDialog("A bet of " + game.bet + " was made!")
            } else {
              updateDialog("Insufficient funds")
              println("Insufficient funds")
            }
          }

          contents += Button("$25 Bet") {
            if(game.player.score >= 25) { //to make sure that the user has sufficient funds (not working)
              game.bet = 25
              println("$25 was bet.")
              updateDialog("A bet of " + game.bet + " was made!")
            } else {
              updateDialog("Insufficient funds")
              println("Insufficient funds")
            }
          }


          contents += Button("$50 Bet") {
            if(game.player.score >= 50) { //to make sure that the user has sufficient funds (not working)
              game.bet = 50
              println("$50 was bet.")
              updateDialog("A bet of " + game.bet + " was made!")
            } else {
              updateDialog("Insufficient funds")
              println("Insufficient funds")
            }
          }

          contents += Button("$100 Bet") {
            if(game.player.score >= 100) { //to make sure that the user has sufficient funds (not working)
              game.bet = 100
              println("$100 was bet.")
              updateDialog("A bet of " + game.bet + " was made!")
            } else {
              updateDialog("Insufficient funds")
              println("Insufficient funds")
            }
          }



        }
      }


      pack()
      centerOnScreen()
      open()

    }

    startRound()

    def clearCard(): Unit = {


      while (playerBox.contents.size  > 0) {
        playerBox.contents.remove(0)
      }

      while (dealerBox.contents.size > 0) {
        dealerBox.contents.remove(0)
      }
    }

    def drawPlayerCard(card: Card): Unit = {
      var lab = new Label {
        icon = new ImageIcon(card.imagePath)
      }
      println("darwing " + card.imagePath)

      playerBox.contents += lab
      resetScreen()
      Thread.sleep(1000)
    }

    def drawDealerCard(card: Card): Unit = {
      var lab = new Label {
        icon = new ImageIcon(card.imagePath)
      }

      println("darwing " + card.imagePath)

      dealerBox.contents += lab
      resetScreen()
      Thread.sleep(1000)
    }

    def startRound(): Unit = {
      updateScore()
      clearCard()
      game.resetHands()

      var l = new Label("Dealer Panel")
      dealerBox.contents += l

      var p = new Label("Player Panel")
      playerBox.contents += p

      drawPlayerCard(game.player.hit(game.deck.draw()))

      drawPlayerCard(game.player.hit(game.deck.draw()))

      drawDealerCard(game.dealer.hit(game.deck.draw()))

      dealerBox.contents += la

      resetScreen()

    }

    def resetScreen(): Unit = {
      frame.validate()
      frame.repaint()
      frame.pack()
      frame.centerOnScreen()
    }


    def getState(): Unit = {
      println("Player score: " + game.player.score)
      println("Dealer score: " + game.dealer.score)
      println("Player ending Hand: " + game.player.hand)
      println("Dealer ending Hand: " + game.dealer.hand)
      println("Deck size: " + game.deck.deck.size())
    }


    def updateDialog(string: String): Unit ={
      dialog.text = (string)
    }

    def updateScore(): Unit ={
      playerScore.text = ("Player Score: " + game.player.score)
      dealerScore.text = ("Dealer Score: " + game.dealer.score)
    }


  }
}