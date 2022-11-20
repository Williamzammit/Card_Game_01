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

/*
//Nov 12th: Right now best option is probably to delete the file in full and rewrite it every time an edit is made to the xml file
            Will talk with mct on Monday though
//Nov 13th: I have decided to use the method on deleting the file and it seems to work nicely even if there is a better way to do it
            Next steps are to draw multiple cards, include an option asking the user to save their hand at the end of the program(deleting the file)
            and an option at the beginning asking if the user would like to load the most recent hand (i.e. not re-writing a new file)
//Nov 14th: After some review of the rubric made in class it would seem that I do meet the requirements for level 4 (not including the diagram)
            so my next focus will be finishing the diagram to ensure a level 4, I also have the shuffle method which is not required for my grade
            which should hopefully warrent a 100%
            Only big issue is that my main method is kind of a disaster but some commmenting should make it easier for Mct to read through
testing...
*/

class Main {
    
    public static void main(String[] args) throws Exception {

//Beginning of variable declaring
        Window window = new Window();
        Deck myDeck = new Deck();
        Scanner input = new Scanner(System.in);
        int lazyVariable;
        String root;
        String output;
        int[] cards = {};
        String SD;

//Variables for the xml file
        String FILENAME;
        File file;
        FileOutputStream fileOutputStream;
        DocumentBuilderFactory documentBuilderFactory;
        DocumentBuilder documentBuilder;
        Document document;
        DOMSource source;
        Element rootElement;
        Element deckElement;
        Element handElement;
        Element cardElement;
        Element suit;
        Element face;
        TransformerFactory transformerFactory;
        Transformer transformer;
        StreamResult result;
        String xmlString;
        byte[] contentsInBytes;
        Element element;
        TransformerFactory transformerFactory2;
        Transformer transformer2;
        StreamResult result2;
        FileOutputStream fileOutputStream2;
//End of variable declaring


        lazyVariable = 0;
        FILENAME = "Card_File.xml";
        file = new File(FILENAME);

//Asks if the user wants to load the most recent hand which will keep the xml file the same as it was the last time the program was ran
//Only asks this if the file exists
        if (file.exists()){
            System.out.println("Would you like to load the most recent hand?");
            SD = input.nextLine();
                if(Character.toLowerCase(SD.charAt(0)) == 'y'){
                    System.out.println("File Loaded.");
                } else if (Character.toLowerCase(SD.charAt(0)) == 'n'){
                    lazyVariable = 1;
                }
            } else {
                lazyVariable = 1;
            }
        
        fileOutputStream = null;
        root = "Cards";

//Nodes for the deck are created here using the Enums from Card.java
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        source = new DOMSource(document);
        rootElement = document.createElement(root);
        document.appendChild(rootElement);

        deckElement = document.createElement("Deck");
        rootElement.appendChild(deckElement);

        handElement = document.createElement("Hand");
        rootElement.appendChild(handElement);

    //Creates the Suit and Face nodes
        if(lazyVariable == 1){
        for(int i = 0; i <= 51; i++){
            Card card = new Card(i);
            cardElement = document.createElement("Card" + i);

            suit = document.createElement("Suit");
            suit.appendChild(document.createTextNode(card.Suit()));
            cardElement.appendChild(suit);

            face = document.createElement("Face");
            face.appendChild(document.createTextNode(card.Face()));
            cardElement.appendChild(face);

            deckElement.appendChild(cardElement);
        }

//Transformer code that formats it for easier reading
        //The transformerFactory and Transformer libraries are xml classes that help format the naturally messy structure of the code
        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        

        //This code uses the transformer to implement the formatting/indenting
        result = new StreamResult(new StringWriter());
        //This "yes" value is essentially a boolean that will decide whether to format the code or not
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //Number value ("5") determines the amount by which it will indent
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        transformer.transform(source, result);

        
//
        try{
        
        fileOutputStream = new FileOutputStream(file);

        if(!file.exists()){
            file.createNewFile();
        }
        //Creates the output variable and 
        xmlString = result.getWriter().toString();
        //Creats an array of bytes to store the data that will be written to the file
        contentsInBytes = xmlString.getBytes();

        //Writes the byten values to the file using the fileOutputStream
        fileOutputStream.write(contentsInBytes);

        //Shows user file writing is finished
        System.out.println("Done");

      //Catch statement for ERRORS
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
}        
        //This commented line prints out the entire deck to the terminal, already tested
        //System.out.println(myDeck.showDeck());
           
        cards = myDeck.ShuffleCards();
        Card draw = new Card(cards[0]);
    
        //Call draw Card method here, pass a value of what card it should draw.
        //It will assign the output variable the String that is returned...

        //This method will take a value from the shuffled array and run the Draw method in Card.java
        output = draw.Draw(cards[0]);
        //Runs the code to output the card onto a seperate window, wanted to practice using the window for future units
        window.test(cards[0]);
        //Prints out the selected card, used this to test if my Window code was accurate
        System.out.println(output);
        
            //Code to access the xml file to take a value from the Deck node and copy it into our hand node.
            Document doc = documentBuilder.parse(new File(FILENAME));
            NodeList card = doc.getElementsByTagName("Card" + cards[0]);
            Node node = card.item(0);
            element = (Element) node;

            cardElement = document.createElement("Card1");

            suit = document.createElement("Suit");
            suit.appendChild(document.createTextNode(element.getElementsByTagName("Suit").item(0).getTextContent()));

            face = document.createElement("Face");
            face.appendChild(document.createTextNode(element.getElementsByTagName("Face").item(0).getTextContent()));

            cardElement.appendChild(suit);
            cardElement.appendChild(face);

            handElement.appendChild(cardElement);
            //End of creating the new Nodes for the hand

            if(lazyVariable == 1){
            //The transformerFactory and Transformer libraries are xml classes that help format the naturally messy structure of the code
            transformerFactory2 = TransformerFactory.newInstance();
            transformer2 = transformerFactory2.newTransformer();
            result2 = new StreamResult(new StringWriter());
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
            fileOutputStream2 = null;
            try{
                fileOutputStream2 = new FileOutputStream(file);

                if(!file.exists()){
                    file.createNewFile();
                }
                //Creates the output variable and 
                xmlString = result2.getWriter().toString();
                //Creats an array of bytes to store the data that will be written to the file
                contentsInBytes = xmlString.getBytes();
                //Writes the byten values to the file using the fileOutputStream
                fileOutputStream2.write(contentsInBytes);
                //Shows user file writing is finished
                System.out.println("Done");
        
              //Catch statement  
            } catch (IOException e){
                e.printStackTrace();
            //Finally statement that will always run after the try statement to ensure FileOutputStream is closed
            } finally {
                try {
                    if (fileOutputStream2 != null){
                        fileOutputStream2.close();
                    } 
                    }catch(IOException e){
                        e.printStackTrace();
                }  
            }
        }

       //Ask user if they want to delete file or save hand
        delete(input, file);
    }


    public static void delete(Scanner input, File file){
        String YN;

        System.out.println("Would you like to save this hand? (y, n)");
            YN = input.nextLine();
            input.close();
            if(Character.toLowerCase(YN.charAt(0)) == 'y'){
                System.out.println("File Saved.");
                System.out.println("When launching the program again remember to load the most recent hand to view this hand again");
            }
            else if (Character.toLowerCase(YN.charAt(0)) == 'n'){
                System.out.println("Deleting File...");
                try{
                    file.delete();
                } catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("File Deleted");
            }
    }
}
