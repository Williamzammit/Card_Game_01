import java.util.Scanner;
class DrawCard{
    Card[] hand;

    public DrawCard(){
  

        
    }
    public String DrawAmount(int amount){
        Scanner input = new Scanner(System.in);
        String output = "";
        //Hand handCards = new Hand(); -- not using this file till I figure out why its being a bitch
        String yn;
        hand = new Card[amount];

        for(int i = 0; i < amount; i++){
            hand[i] = new Card((int)(Math.random()*52));
        }
        System.out.println("Do you want to display your hand? (y, n)");
        yn = input.nextLine();
        
            
        
        for(int i = 0; i < amount; i++){

            try{
            output += hand[i].Face() + " of " + hand[i].Suit();
            }catch(Exception e){
                System.out.println("YUP ITS STILL SHIT");
                e.printStackTrace();
            }
            output += "\n";
            }
            if(yn.charAt(0) == 'y'){
        return output;
        } else{
            return "";
        }
         //return handCards.ShowHand(amount);
    }
}