import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;

public class Window{


public static void test() {
    
        JFrame test = new JFrame();
        JPanel testPanel = new JPanel();
        test.add(testPanel);
        test.setSize(350, 350);
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JLabel testLabel = new JLabel("🂡");
        testPanel.add(testLabel);
        testLabel.setFont(new Font("Serif", Font.PLAIN, 150));
        test.setVisible(true);
    }
}