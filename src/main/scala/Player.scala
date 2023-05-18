package BlackJack {
  class Player(var dealer: Boolean)  {
    var score = 100
    var hand = 0
    var ace = 0

    //for ace we only have to look at situations where the user is over 21 since we will eval them as 11
    //if score is greater than 21 and they have an ace  minus 10
    //if socre is less than
    //hadn is 10 + ace == 21 (win)
    //hand is 8 + ace + 8  = 27  - 10  17
    //hand is ace ace 8 = 30 - 10 = 20  + 10 = 3-  10 20
    //if (ace > 0 && hand > 21 ) sub from hand and sub from aces.


    // bet system last tbh becuase that has to deal with the UI and user input.


    //returns the new value after hit
    def hit(card: Card): Card = {
      if(card.value == 1) {
        ace += 1
        hand += 11
      } else {
        hand += card.value
      }
      if(ace > 0 && hand > 21) {
        //println("Entity was hit: " + (hand - 10))
      } else {
        //println("Entity was hit: " + hand)
      }
      card
    }

    //if hand less than or = to 16 hit
    def dealerLogic(): Boolean = {
      hand <= 16
    }

  }
}


