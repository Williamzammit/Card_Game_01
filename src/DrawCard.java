//William Zammit
//November 15th
//Deck of Cards assignment

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
class DrawCard{

    //Card[] hand;


    public DrawCard(){
        

        
    }

    //This method gets the value of the card draw from the shuffled deck
    //It uses the data from the xml file to get the card ID and returns a String with the card
    public String Draw(int cardIndex, Element handElement) throws Exception{
        String FILENAME = "Card_File.xml";
        
        DocumentBuilderFactory docB = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = docB.newDocumentBuilder();
        Document doc = db.parse(new File(FILENAME));

        NodeList card = doc.getElementsByTagName("Card" + cardIndex);

        Node node = card.item(0);
                    Element element = (Element) node;


                    //**This is the most important part as this is how we are assigning variables values from the file**
                    String Face = element.getElementsByTagName("Face").item(0).getTextContent();
                    String Suit = element.getElementsByTagName("Suit").item(0).getTextContent();

                    
                
        
        return Face + " of " + Suit;
    }
}