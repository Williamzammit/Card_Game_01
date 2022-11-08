//Having trouble with assigning some values with this file so will be not using it unless I have some sort of epiphany

public class Hand {
    DrawCard draw = new DrawCard();

    public String ShowHand(int amount){

        String output = "";
        for(int i = 0; i < amount; i++){

        try{
        output += draw.hand[i].Face() + " of " + draw.hand[i].Suit();
        }catch(Exception e){
            System.out.println("YUP ITS STILL SHIT");
            e.printStackTrace();
        }
        output += "\n";
        }
        System.out.println("TEST");
        return output;
    }
}
