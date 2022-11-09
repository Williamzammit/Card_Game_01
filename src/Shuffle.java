public class Shuffle {
    public int[] ShuffleCards(int[] cards){

        for(int i = 0; i < 52; i++){
        int x = (int)(Math.random()*52); // 37
        int x2 = cards[x]; // 36
        int y = (int)(Math.random()*52); // 46
        int y2 = cards[y]; // 47

            cards[x] = y2;
            cards[y] = x2;
        }

        return cards;
    }
    
}   