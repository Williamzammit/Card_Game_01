import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.Scanner;
class Main {
    
    public static void main(String[] args) {
        String FILENAME = "Card_File.xml";
        DocumentBuilderFactory docB = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = docB.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();
        } catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }

        Deck deck = new Deck();
        Card card = new Card(37);
        
        Scanner input = new Scanner(System.in);
        String yn;
        

        //System.out.println(deck.ShowDeck());
       
        System.out.println(card.Face() + " of " + card.Suit());





/****** 
        System.out.println("Do you want to draw cards?");
        yn = input.nextLine();
        if(yn.charAt(0) == 'y'){
            DrawCards();
        }
       */
       
    }
    public static void DrawCards(){
        DrawCard hand = new DrawCard();
        Scanner input = new Scanner(System.in);
        int amount;
        System.out.println("How many cards would you like to draw? (Max 52)");
        amount = input.nextInt();
        System.out.println("Your hand: " + "\n" + hand.DrawAmount(amount));
    }
}