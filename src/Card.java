class Card{
    enum Suit{CLUBS, DIAMONDS, HEARTS, SPADES}
    enum Face{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
    private int cardNumber;
    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }
    public String Suit(){
        Suit s = Suit.values()[this.cardNumber/13];
        
        return s.name();
    }
    public String Face(){
        Face f = Face.values()[this.cardNumber%13];
        return f.name();
    }
}