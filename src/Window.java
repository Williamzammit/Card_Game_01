//William Zammit
//November 15th
//Deck of Cards assignment

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;

public class Window{

public String getCardImage(int cardNumber){
    switch (cardNumber){
        case 0:
        return "🃑";
        case 1:
        return "🃒";
        case 2:
        return "🃓";
        case 3:
        return "🃔";
        case 4:
        return "🃕";
        case 5:
        return "";
        case 6:
        return "";
        case 7:
        return "";
        case 8:
        return "";
        case 9:
        return "";
        case 10:
        return "";
        case 11:
        return "";
        case 12:
        return "";
        case 13:
        return "";
        case 14:
        return "";
        case 15:
        return "";
        case 16:
        return "";
        case 17:
        return "";
        case 18:
        return "";
        case 19:
        return "";
        case 20:
        return "";
        case 21:
        return "";
        case 22:
        return "";
        case 23:
        return "";
        case 24:
        return "";
        case 25:
        return "";
        case 26:
        return "";
        case 27:
        return "";
        case 28:
        return "";
        case 29:
        return "";
        case 30:
        return "";
        case 31:
        return "";
        case 32:
        return "";
        case 33:
        return "";
        case 34:
        return "";
        case 35:
        return "";
        case 36:
        return "";
        case 37:
        return "";
        case 38:
        return "";
        case 39:
        return "";
        case 40:
        return "";
        case 41:
        return "";
        case 42:
        return "";
        case 43:
        return "";
        case 44:
        return "";
        case 45:
        return "";
        case 46:
        return "";
        case 47:
        return "";
        case 48:
        return "";
        case 49:
        return "";
        case 50:
        return "";
        case 51:
        return "";
    }
    return "CARD ERROR";
}
public void test(int cardNumber) {
    
        JFrame test = new JFrame();
        JPanel testPanel = new JPanel();
        test.add(testPanel);
        test.setSize(350, 350);
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JLabel testLabel = new JLabel(getCardImage(cardNumber));
        testPanel.add(testLabel);
        testLabel.setFont(new Font("Serif", Font.PLAIN, 150));
        test.setVisible(true);
    }
}