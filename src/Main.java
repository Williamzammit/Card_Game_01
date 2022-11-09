import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Scanner;
class Main {
    
    public static void main(String[] args) throws Exception {

        //Boring xml stuffp
       String FILENAME = "Card_File.xml";
       String root = "Cards";
       DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);

        for(int i = 0; i <= 51; i++){
            Card card = new Card(i);
            Element cardElement = document.createElement("Card" + i);

            Element suit = document.createElement("Suit");
            suit.appendChild(document.createTextNode(card.Suit()));
            cardElement.appendChild(suit);

            Element face = document.createElement("Face");
            face.appendChild(document.createTextNode(card.Face()));
            cardElement.appendChild(face);

            rootElement.appendChild(cardElement);
        }

        //The transformerFactory and Transformer libraries are xml classes that help format the naturally messy structure of the code
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        //This code uses the transformer objects to implement the formatting/indenting
        StreamResult result = new StreamResult(new StringWriter());
        //This "yes" value is essentially a boolean that will decide whether to indent the code or not
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //Number value ("5") determines the amount by which it will indent
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        transformer.transform(source, result);

        FileOutputStream fileOutputStream = null;
        File file;

        try{
        //Creates the aliens text file
        file = new File(FILENAME);
        fileOutputStream = new FileOutputStream(file);

        if(!file.exists()){
            file.createNewFile();
        }
        //Creates the output variable and 
        String xmlString = result.getWriter().toString();
        //Creats an array of bytes to store the data that will be written to the file
        byte[] contentsInBytes = xmlString.getBytes();

        //Writes the byten values to the file using the fileOutputStream
        fileOutputStream.write(contentsInBytes);
        fileOutputStream.flush();
        //Closes the writer
        fileOutputStream.close();

        //Shows user file reading is finished
        System.out.println("Done");

      //Catch statement  
    } catch (IOException e){
        e.printStackTrace();
        //Finally statement that will always run after the try statement
    } finally {
        try {
            if (fileOutputStream != null){
                fileOutputStream.close();
            } 
            }catch(IOException e){
                e.printStackTrace();
        }
        
    }
    //End of boring xml stuff

        //Deck deck = new Deck();
        Card card = new Card(0);
        DrawCard draw = new DrawCard();
        Shuffle shuffle = new Shuffle();
        
        //Scanner input = new Scanner(System.in);
        //String yn;
        String output;
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 
                       27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
        

        //System.out.println(deck.ShowDeck());
       
        //System.out.println(card.Face() + " of " + card.Suit());


        /*This is our shuffle method, it takes the array above and randomly changes 
          the order around so that there will be a different card at the "top" of the deck each time
        */
        cards = shuffle.ShuffleCards(cards);


        //Call draw Card method here, pass a value of what card it should draw.
        //It will assign the output variable the String that is returned...

        //This method will take a value from an array that 
        output = draw.Draw(cards[0]);

        
        //...And that will be outputed here.
        System.out.println(output);
        for(int i = 0; i < 52; i++){
        System.out.println(cards[i]);
        }




/****** 
        System.out.println("Do you want to draw cards?");
        yn = input.nextLine();
        if(yn.charAt(0) == 'y'){
            DrawCards();
        }
       */
       

       //Delete code here at the end?
    }
    
}