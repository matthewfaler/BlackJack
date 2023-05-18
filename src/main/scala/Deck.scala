import java.util.ArrayList
package BlackJack {

  import java.util.Collections

  class Deck {
    private val NUM_CARDS = 52                               // number of cards in deck
     val deck = ArrayList[Card]

    val suit = Array ("Spade", "Heart", "Club", "Diamond")
    for(  x <-0 to NUM_CARDS - 1 ){
      var card = new Card(x % 13 + 1, suit (x / 13))
      deck.add(card)
    }
    Collections.shuffle(deck)
    println(deck)

    def draw(): Card = {
      deck.remove(0)
    }
  }
}