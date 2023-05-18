package BlackJack {
  class Card(var value: Int, var suit: String) {

    var imagePath = "src/main/scala/"+ value + suit + ".png"

    if (value > 10) {
      value = 10
    }
    
     

    override def toString: String = {
      this.value +  "of" + this.suit
    }
  }


}


