package BlackJack{

  class BlackJackGame(var player: Player, var dealer: Player)  {

    val deck = new Deck()
    var bet = 0

    def resetHands(): Unit = {
      player.hand = 0
      player.ace = 0
      dealer.hand = 0
      dealer.ace = 0

    }

    //would modiofy bet amounts here.
    //check that that the hand is more than 21
    //return a boolean here
    def evaluateHand(): Boolean = {
      if (player.hand > 21 && player.ace == 0) {
        player.score -= bet
        dealer.score += bet
        println("Player Bust.:" + player.hand + "\nPlayer score : " + player.score)
        true
      } else if (player.hand == 21){
        player.score += bet
        dealer.score -= bet
        false
      } else if (player.hand > 21) {
        player.ace -= 1
        player.hand -= 10
        evaluateHand()
      } else {
        false
      }
    }
    //evaluete and compare dealer hand to player
    //return true if dealer wins
    def compareHand(): Boolean = {
      if (dealer.hand > 21 || dealer.hand  < player.hand) {
        player.score += bet
        dealer.score -= bet
        println("Dealer Lost: " + dealer.hand + "\nScore: " + player.score)
        false
      } else if(dealer.hand == player.hand) {
        println("Push!" + "\nPlayer hand: " + player.hand + "\tDealer hand: " + dealer.hand + "\n")
        true
      } else {
        player.score -= bet
        dealer.score += bet
        println("Dealer won")
        true
      }
    }
  } 
}



