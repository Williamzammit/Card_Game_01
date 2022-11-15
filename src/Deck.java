
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//William Zammit
//November 15th
//Deck of Cards assignment

public class Deck {

    public String showDeck() throws Exception{
        String output = "";
        String FILENAME = "Card_File.xml";
        
        DocumentBuilderFactory docB = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = docB.newDocumentBuilder();
        Document doc = db.parse(new File(FILENAME));

        
        for(int i = 0; i < 52; i++){
        NodeList card = doc.getElementsByTagName("Card" + i);
        Node node = card.item(0);
        Element element = (Element) node;

        //**This is the most important part as this is how we are assigning variables values from the file**
        String Face = element.getElementsByTagName("Face").item(0).getTextContent();
        String Suit = element.getElementsByTagName("Suit").item(0).getTextContent();
            output += Face + " of " + Suit + "\n";
        }

        return output;
    }

/*  
    This is our shuffle method, it takes the array above and randomly changes 
    the order around so that there will be a different card at the "top" of the deck each time
*/
    public int[] ShuffleCards(){
        
        int[] deck = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 
            27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

        for(int i = 0; i < 52; i++){
        int x = (int)(Math.random()*52); // 37
        int x2 = deck[x]; // 36
        int y = (int)(Math.random()*52); // 46
        int y2 = deck[y]; // 47

            deck[x] = y2;
            deck[y] = x2;
        }
        

        return deck;
    }
    
}   