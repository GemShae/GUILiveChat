import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChatHome extends JPanel {
    JLabel nameOfClientLabel;
    JLabel doToHelpLabel;
    JLabel startAConversationLabel;
    JLabel profileImageLabel;
    JTextArea personsOnlineTextArea;
    JButton newConversationButton;
    JButton logOffButton;
    JPanel topPanel;
    JPanel middleActiveClientsPanel;
    Border blueLine;
    Border raisedBevel;
    Border compoundActiveClientsBorder;
    Color blueBackground;


    ChatHome () {
        blueBackground = new Color(41,193,239);

        profileImageLabel = new JLabel(new ImageIcon("image/Chat.png"));
        nameOfClientLabel = new JLabel("Hi, Customer 1!");
        doToHelpLabel = new JLabel("What can we do to help?");
        startAConversationLabel = new JLabel("Start a Conversation");

        topPanel = new JPanel();
        middleActiveClientsPanel = new JPanel();

        personsOnlineTextArea = new JTextArea();

        newConversationButton = new JButton("New Conversation");
        logOffButton = new JButton("Log Off");

        blueLine = BorderFactory.createLineBorder(blueBackground);
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        compoundActiveClientsBorder = BorderFactory.createCompoundBorder(blueLine,raisedBevel);

        topPanel.setLayout(null);
        middleActiveClientsPanel.setLayout(null);

        topPanel.setBackground(blueBackground);
        newConversationButton.setBackground(blueBackground);
        logOffButton.setBackground(blueBackground);

        nameOfClientLabel.setForeground(Color.WHITE);
        doToHelpLabel.setForeground(Color.WHITE);
        newConversationButton.setForeground(Color.WHITE);
        logOffButton.setForeground(Color.WHITE);

        middleActiveClientsPanel.setBorder(compoundActiveClientsBorder);

        nameOfClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        doToHelpLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        startAConversationLabel.setFont(new Font("Tahoma", Font.PLAIN,20));
        personsOnlineTextArea.setFont(new Font("Tahoma",Font.PLAIN,20));

        topPanel.setBounds(0,0,450,170);
        middleActiveClientsPanel.setBounds(5,180,425,300);
        profileImageLabel.setBounds(70,40,60,60);
        nameOfClientLabel.setBounds(75,90,400,40);
        doToHelpLabel.setBounds(90,120,400,40);
        startAConversationLabel.setBounds(110,5,200,30);
        personsOnlineTextArea.setBounds(10,40,400,250);
        newConversationButton.setBounds(10,500,200,50);
        logOffButton.setBounds(230,500,200,50);

        personsOnlineTextArea.setEditable(false);

        topPanel.add(profileImageLabel);
        topPanel.add(nameOfClientLabel);
        topPanel.add(doToHelpLabel);

        middleActiveClientsPanel.add(startAConversationLabel);
        middleActiveClientsPanel.add(personsOnlineTextArea);

        this.add(topPanel);
        this.add(middleActiveClientsPanel);
        this.add(newConversationButton);
        this.add(logOffButton);

        this.setLayout(null);
        this.setSize(450, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(450,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChatHome());
        frame.setVisible(true);
    }
}
