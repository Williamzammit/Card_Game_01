//William Zammit
//November 15th
//Deck of Cards assignment

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Scanner;

/*Nov 10th: Code is being a bitch
//Nov 12th: Right now best option is probably to delete the file in full and rewrite it every time an edit is made to the xml file
            Will talk with mct on Monday though
//Nov 13th: I have decided to use the method on deleting the file and it seems to work nicely even if there is a better way to do it
            Next steps are to draw multiple cards, include an option asking the user to save their hand at the end of the program(deleting the file)
            and an option at the beginning asking if the user would like to load the most recent hand (i.e. not re-writing a new file)
*/

class Main {
    
    public static void main(String[] args) throws Exception {
        Window test = new Window();
        test.test();
        

        //Boring xml stuff

        //When changing the FILENAME variable you must also change the matching variable in DrawCard.java
        String FILENAME = "Card_File.xml";
        String root = "Cards";

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);

        Element deckElement = document.createElement("Deck");
        rootElement.appendChild(deckElement);

        Element handElement = document.createElement("Hand");
        rootElement.appendChild(handElement);

        for(int i = 0; i <= 51; i++){
            Card card = new Card(i);
            Element cardElement = document.createElement("Card" + i);

            Element suit = document.createElement("Suit");
            suit.appendChild(document.createTextNode(card.Suit()));
            cardElement.appendChild(suit);

            Element face = document.createElement("Face");
            face.appendChild(document.createTextNode(card.Face()));
            cardElement.appendChild(face);

            deckElement.appendChild(cardElement);
        }

        //The transformerFactory and Transformer libraries are xml classes that help format the naturally messy structure of the code
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        //This code uses the transformer to implement the formatting/indenting
        StreamResult result = new StreamResult(new StringWriter());
        //This "yes" value is essentially a boolean that will decide whether to format the code or not
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //Number value ("5") determines the amount by which it will indent
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        transformer.transform(source, result);

        FileOutputStream fileOutputStream = null;
        File file;
        file = new File(FILENAME);
        
        try{
        
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
        

        //Shows user file writing is finished
        System.out.println("Done");

      //Catch statement  
    } catch (IOException e){
        e.printStackTrace();
        //Finally statement that will always run after the try statement to close the FileOutputStream
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

        
        DrawCard draw = new DrawCard();
        Shuffle shuffle = new Shuffle();
        
        //Scanner input = new Scanner(System.in);
        //String yn;
        String output;
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 
                       27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
        

       
        //System.out.println(card.Face() + " of " + card.Suit());


        /*This is our shuffle method, it takes the array above and randomly changes 
          the order around so that there will be a different card at the "top" of the deck each time
        */
        cards = shuffle.ShuffleCards(cards);


        //Call draw Card method here, pass a value of what card it should draw.
        //It will assign the output variable the String that is returned...

        //This method will take a value from an array that 
        output = draw.Draw(cards[0], handElement);

        System.out.println(output);
        


            //Code to access the xml file to take a value from the Deck node and copy it into our hand node.
            DocumentBuilderFactory docB = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = docB.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            NodeList card = doc.getElementsByTagName("Card" + cards[0]);
            Node node = card.item(0);
            Element element = (Element) node;

            Element cardElement = document.createElement("Card1");

            Element suit = document.createElement("Suit");
            suit.appendChild(document.createTextNode(element.getElementsByTagName("Suit").item(0).getTextContent()));

            Element face = document.createElement("Face");
            face.appendChild(document.createTextNode(element.getElementsByTagName("Face").item(0).getTextContent()));

            cardElement.appendChild(suit);
            cardElement.appendChild(face);

            handElement.appendChild(cardElement);
            //End of creating the new Nodes for the hand

            //The transformerFactory and Transformer libraries are xml classes that help format the naturally messy structure of the code
            TransformerFactory transformerFactory2 = TransformerFactory.newInstance();
            Transformer transformer2 = transformerFactory2.newTransformer();
            StreamResult result2 = new StreamResult(new StringWriter());
            //This "yes" value is essentially a boolean that will decide whether to format the code or not
            transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
            //Number value ("5") determines the amount by which it will indent
            transformer2.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
            transformer2.transform(source, result2);

            if(file.delete()){
                System.out.println("Re-writing file....");
            } else {
                System.out.println("File Deletion ERROR");
            }
            FileOutputStream fileOutputStream2 = null;
            try{
        
                fileOutputStream2 = new FileOutputStream(file);
        
                if(!file.exists()){
                    file.createNewFile();
                }
                //Creates the output variable and 
                String xmlString = result2.getWriter().toString();
                //Creats an array of bytes to store the data that will be written to the file
                byte[] contentsInBytes = xmlString.getBytes();
        
                //Writes the byten values to the file using the fileOutputStream
                fileOutputStream2.write(contentsInBytes);
                
        
                //Shows user file reading is finished
                System.out.println("Done");
        
              //Catch statement  
            } catch (IOException e){
                e.printStackTrace();
                //Finally statement that will always run after the try statement to close the FileOutputStream
            } finally {
                try {
                    if (fileOutputStream2 != null){
                        fileOutputStream2.close();
                    } 
                    }catch(IOException e){
                        e.printStackTrace();
                }  
            }


       //Delete file here at the end?
    }
    
}