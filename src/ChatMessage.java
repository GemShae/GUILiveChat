import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatMessage extends JPanel {
    JLabel backArrowImageLabel;
    JLabel profileImageLabel;
    JLabel nameLabel;
    JLabel availableLabel;
    JTextField typeMessageTextField;
    JButton sendMessageButton;
    JPanel topPanel;
    static JPanel chatBubblePanel;
    static JScrollPane chatAreaScrollPane;
    //JTextArea chatTextArea;
    boolean typing;

    Box verticalBox = Box.createVerticalBox();

    ChatMessage() {
        backArrowImageLabel = new JLabel(new ImageIcon("image/BackArrow.png"));
        profileImageLabel = new JLabel(new ImageIcon("image/Chat.png"));
        nameLabel = new JLabel("Customer");
        availableLabel = new JLabel("Active Now");
        typeMessageTextField = new JTextField();
        sendMessageButton = new JButton("Send");
        //chatTextArea = new JTextArea();
        chatBubblePanel = new JPanel();
        chatAreaScrollPane =  new JScrollPane(chatBubblePanel);

        nameLabel.setFont(new Font("Dialog",Font.BOLD, 30));
        availableLabel.setFont(new Font("Dialog", Font.ITALIC, 20));
        typeMessageTextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        sendMessageButton.setFont(new Font("Times New Roman",Font.PLAIN,16));
        //chatTextArea.setFont(new Font ("Times New Roman", Font.PLAIN,16));
        chatBubblePanel.setFont(new Font ("Times New Roman", Font.PLAIN,16));

        /*chatTextArea.setEditable(false);
        chatTextArea.setLineWrap(true);
        chatTextArea.setWrapStyleWord(true); */

        nameLabel.setForeground(Color.WHITE);
        availableLabel.setForeground(Color.WHITE);
        sendMessageButton.setForeground(Color.WHITE);
        sendMessageButton.setBackground(new Color(41,193,239));

        topPanel = new JPanel();

        topPanel.setLayout(null);
        topPanel.setBackground(new Color(41,193,239));
        topPanel.setBounds(0,0,450,70);

        backArrowImageLabel.setBounds(20,4,60,60);
        profileImageLabel.setBounds(90,7,60,60);
        nameLabel.setBounds(150,9,300,40);
        availableLabel.setBounds(210,35,300,40);
        typeMessageTextField.setBounds(1,532,315,30);
        sendMessageButton.setBounds(318,532,115,30);
        //chatTextArea.setBounds(0,70,450,458);
        chatAreaScrollPane.setBounds(0,70,450,458);

        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!typing) {
                    availableLabel.setText("Active Now");
                }
            }
        });

        timer.setInitialDelay(2000);

        typeMessageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                availableLabel.setText("Typing...");
                timer.stop();
                typing=true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                typing=false;
                if (!timer.isRunning()) {
                    timer.start();
                }
            }
        });

        backArrowImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Go back to the chat home panel
            }
        });

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String out = typeMessageTextField.getText();
                JPanel setRight = new JPanel(new BorderLayout());
                chatBubblePanel.setLayout(new BorderLayout());
                JPanel outputFromFormatingPanel = formatLabel(out);

                setRight.add(outputFromFormatingPanel, BorderLayout.LINE_END);
                verticalBox.add(setRight);

                verticalBox.add(Box.createVerticalStrut(15));

                chatBubblePanel.add(verticalBox,BorderLayout.PAGE_START);
                //chatBubblePanel.add(outputFromFormatingPanel);
                //chatTextArea.setText(chatTextArea.getText() + " \n\t\t\t" + out);
                typeMessageTextField.setText("");
            }
        });

        topPanel.add(backArrowImageLabel);
        topPanel.add(profileImageLabel);
        topPanel.add(nameLabel);
        topPanel.add(availableLabel);
        this.add(topPanel);
        //this.add(chatTextArea);
        //this.add(chatBubblePanel);
        this.add(chatAreaScrollPane);
        this.add(typeMessageTextField);
        this.add(sendMessageButton);

        this.setLayout(null);
        this.setSize(450, 600);
        this.setVisible(true);
    }

    public static JPanel formatLabel (String out) {
        JPanel outputTextPanel = new JPanel();
        JLabel outputTextLabel = new JLabel("<html><p style = \"width : 150px\">" + out +"</p></html>");
        JLabel timeStampLabel = new JLabel();

        outputTextPanel.setLayout(new BoxLayout(outputTextPanel, BoxLayout.Y_AXIS));

        outputTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        outputTextLabel.setBackground(new Color(51,141,230));
        outputTextLabel.setOpaque(true);
        outputTextLabel.setBorder(new EmptyBorder(15,15,15,50));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        timeStampLabel.setText(dateFormat.format(calendar.getTime()));

        outputTextPanel.add(outputTextLabel);
        outputTextPanel.add(timeStampLabel);
        return outputTextPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(450,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Need to change to hide on close
        frame.add(new ChatMessage());
        //frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
