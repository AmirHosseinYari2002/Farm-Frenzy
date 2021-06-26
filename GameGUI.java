import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;

public class GameGUI {
    private static Player player;
    private static Authentication authentication = new Authentication();
    public static String username;
    public void loadApp(){
        JFrame loadFrame = GUI.createFrame(400,200,800,500,"SUT Farm Frenzy");
        loadFrame.setContentPane(GUI.setBackground(Color.white,Color.green));
        JTextArea title = new JTextArea("\tLoading SUT Farm Frenzy..." +
                "\n       Created by Mohammad Hossein Shafizadegan" +
                "\n                           and AmirHossein Yari");
        title.setFont(new Font(Font.SERIF,Font.BOLD,20));
        title.setBackground(Color.white);
        title.setEditable(false);
        title.setBorder(BorderFactory.createLineBorder(Color.black,5));
        title.setBounds(150,20,500,90);
        ImageIcon imageIcon = new ImageIcon("src\\pictures\\loading.gif");
        JLabel image = new JLabel(imageIcon);
        image.setBounds(250,110,254,254);
        loadFrame.add(image);
        loadFrame.add(title);
        JProgressBar progressBar = new JProgressBar(0,100);
        progressBar.setVisible(true);
        progressBar.setBounds(150,350,500,50);
        progressBar.setBorder(BorderFactory.createLineBorder(Color.black,2));
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.blue);
        loadFrame.add(progressBar);
        loadFrame.setLayout(null);
        for (int i = 0; i < 100; i++) {
            loadFrame.setVisible(true);
            progressBar.setValue(i);
            progressBar.setVisible(true);
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loadFrame.dispose();
    }
    public void StartMenu(){
        JFrame startFrame = GUI.createFrame(400,200,800,500,"SUT Farm Frenzy");
        startFrame.setContentPane(GUI.setBackground(Color.GREEN,new Color(200,255,0)));
        JTextArea welcome = GUI.createTextArea(150,50,500,50,new Color(200,255,200),"               Welcome to Sharif Market");
        startFrame.add(welcome);
        JLabel image = GUI.createImage(300,160,150,120,"src\\pictures\\LogIn.png");
        startFrame.add(image);
        JButton sigIn = new JButton("Sign in");
        JButton signUp = new JButton("Sign up");
        sigIn.setBounds(225,300,150,50);
        signUp.setBounds(390,300,150,50);
        GUI.addButtonGUI(sigIn,225,300,150,50,new Color(0,150,255));
        GUI.addButtonGUI(signUp,390,300,150,50,new Color(0,150,255));
        ActionListener signInProcess = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn();
            }
        };
        ActionListener signUpProcess = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp();
            }
        };
        sigIn.addActionListener(signInProcess);
        signUp.addActionListener(signUpProcess);
        startFrame.add(signUp);
        startFrame.add(sigIn);
        startFrame.setLayout(null);
        startFrame.setVisible(true);
    }
    public void SignIn() {
        JFrame signInFrame = GUI.createFrame(400, 200, 800, 500, "Sign in");
        signInFrame.setContentPane(GUI.setBackground(Color.green, new Color(150, 255, 150)));
        JTextArea info = GUI.createTextArea(200, 50, 400, 50, Color.white, "                  Sign in panel");
        JLabel Username = GUI.createLabel(250, 120, 300, 50, new Color(1, 200, 255), "   Enter your User name :");
        Username.setIcon(new ImageIcon("src\\pictures\\username.png"));
        JTextField getUserName = GUI.createTextField(250, 170, 300, 40);
        JLabel Password = GUI.createLabel(250, 230, 300, 40, new Color(1, 200, 255), "   Enter your Password :");
        Password.setIcon(new ImageIcon("src\\pictures\\password.png"));
        JTextField getPassword = GUI.createTextField(250, 270, 300, 40);
        JButton SignIn = new JButton("Sign in");
        SignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (getPassword.getText().equals("") || getUserName.getText().equals(""))
                    SignIn.setBackground(new Color(255, 70, 0));
                else
                    SignIn.setBackground(new Color(0,150,255));
                SignIn.setBounds(340, 320, 120, 60);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SignIn.setBackground(new JButton().getBackground());
                SignIn.setBounds(350, 330, 100, 50);
            }
        });
        SignIn.setBounds(350, 330, 100, 50);
        ActionListener confirm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = getUserName.getText();
                String password = getPassword.getText();
                authentication.initUserPass(getUserName.getText(), getPassword.getText());
                if (userName.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(signInFrame, "ERROR! Username and password field is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (!authentication.checkUserPass()) {
                    JOptionPane.showMessageDialog(signInFrame, "ERROR! your username or password is incorrect.", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showConfirmDialog(signInFrame, "Sign successfully", "Welcome", JOptionPane.CLOSED_OPTION);
                    FileManager.addToFile(GameHandler.getInstance(), new Date().toString() + " [" + Log.INFO + "] " + "Signed In successfully");
                    signInFrame.dispose();
                    MainMenu(userName);
                }
            }
        };
        SignIn.addActionListener(confirm);
        SignIn.setVisible(true);
        signInFrame.add(info);
        signInFrame.add(SignIn);
        signInFrame.add(Username);
        signInFrame.add(getUserName);
        signInFrame.add(Password);
        signInFrame.add(getPassword);
        signInFrame.setLayout(null);
        signInFrame.setVisible(true);
    }
    public void SignUp() {
        JFrame signUpFrame = GUI.createFrame(400, 200, 800, 500, "Sign in");
        signUpFrame.setContentPane(GUI.setBackground(Color.green, new Color(150, 255, 150)));
        JTextArea info = GUI.createTextArea(200, 50, 400, 50, Color.white, "                  Sign in panel");
        JLabel Username = GUI.createLabel(250, 120, 300, 50, new Color(1, 200, 255), "   Enter your User name :");
        Username.setIcon(new ImageIcon("src\\pictures\\username.png"));
        JTextField getUserName = GUI.createTextField(250, 170, 300, 40);
        JLabel Password = GUI.createLabel(250, 230, 300, 40, new Color(1, 200, 255), "   Enter your Password :");
        Password.setIcon(new ImageIcon("src\\pictures\\password.png"));
        JTextField getPassword = GUI.createTextField(250, 270, 300, 40);
        JButton SignIn = new JButton("Sign in");
        SignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (getPassword.getText().equals("") || getUserName.getText().equals(""))
                    SignIn.setBackground(new Color(255, 70, 0));
                else
                    SignIn.setBackground(new Color(0,150,255));
                SignIn.setBounds(340, 320, 120, 60);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SignIn.setBackground(new JButton().getBackground());
                SignIn.setBounds(350, 330, 100, 50);
            }
        });
        SignIn.setBounds(350, 330, 100, 50);
        ActionListener confirm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = getUserName.getText();
                String password = getPassword.getText();
                authentication.initUserPass(getUserName.getText(), getPassword.getText());
                if (userName.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(signUpFrame, "ERROR! Username and password field is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (!authentication.checkNewUsername(userName)) {
                    JOptionPane.showMessageDialog(signUpFrame, "ERROR! this username is already existed", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showConfirmDialog(signUpFrame, "Sign successfully", "Welcome", JOptionPane.CLOSED_OPTION);
                    FileManager.addToFile(GameHandler.getInstance(), new Date().toString() + " [" + Log.INFO + "] " + "Signed up successfully");
                    authentication.addUser();
                    signUpFrame.dispose();
                    MainMenu(userName);
                }
            }
        };
        SignIn.addActionListener(confirm);
        SignIn.setVisible(true);
        signUpFrame.add(info);
        signUpFrame.add(SignIn);
        signUpFrame.add(Username);
        signUpFrame.add(getUserName);
        signUpFrame.add(Password);
        signUpFrame.add(getPassword);
        signUpFrame.setLayout(null);
        signUpFrame.setVisible(true);
    }
    public static void MainMenu(String username){
        player = authentication.initPlayer(username);
        JFrame menuFrame = GUI.createFrame(0,0,1920,1080,"SUT Farm Frenzy");
        menuFrame.setContentPane(GUI.setBackground("src\\pictures\\wallpaper.jpg"));
        menuFrame.add(TIME.showTime(300,680,1000,50));
        Clip menuSound = GUI.playSound(new File("src\\pictures\\Farmville.wav"));
        menuSound.start();
        JPanel info = GUI.createPanel(300,70,1000,100,new Color(200,255,200));
        String[] userData = FileManager.getUserData(authentication.getUsers(),username);
        String text = "Player: "+userData[0]+"\t\t\tCoins: "+userData[3];
        JLabel info1 = GUI.createLabel(400,10,300,30,null,"SUT Farm Frenzy Menu");
        info1.setFont(new Font(Font.SERIF,Font.BOLD,25));
        info.add(info1);
        JTextArea info2 = GUI.createTextArea(200,50,600,30,null,text);
        info2.setBorder(null);
        info.add(info2);
        menuFrame.add(info);
        JLabel image = GUI.createImage(750,350,300,295,"src\\pictures\\farmer3.gif");
        menuFrame.add(image);
        JPanel help = showHelp(image);
        menuFrame.add(help);
        JPanel options = GUI.createPanel(300,225,400,400,new Color(200,255,200));
        JButton[] optionB = new JButton[5];
        String[] buttonName = {"Levels","Help","Customer profile","Sign out","Exit"};
        for (int i = 0; i < 5; i++) {
            optionB[i] = new JButton(buttonName[i]);
            optionB[i].setFont(new Font(Font.SERIF,Font.CENTER_BASELINE,20));
            optionB[i].setBounds(150,70*i+50,200,50);
            optionB[i].setVisible(true);
            options.add(optionB[i]);
            GUI.addButtonGUI(optionB[i],150,70*i+50,200,50,new Color(0,200,255));
            String URL = "";
            if (i == 2) {
                URL = "src\\pictures\\edit.png";
            }
            else if (i == 3) {
                URL = "src\\pictures\\option3.png";
            }
            else {
                URL = "src\\pictures\\option"+(i+1)+".png";
            }
            JLabel optionsImage = GUI.createImage(50,70*i+50,50,50,URL);
            options.add(optionsImage);
        }
        ActionListener showLevels = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel levels = showLevels(image,menuFrame,menuSound);
                menuFrame.add(levels);
                image.setVisible(false);
                levels.setVisible(true);
            }
        };
        optionB[0].addActionListener(showLevels);
        ActionListener signOut = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(menuFrame,"Are you sure to Sign out?","Sign out",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (status == 0){
                    menuFrame.dispose();
                }
            }
        };
        ActionListener showHelp = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.setVisible(false);
                help.setVisible(true);
            }
        };
        optionB[1].addActionListener(showHelp);
        optionB[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel edit = editProfile(info2,image);
                menuFrame.add(edit);
                edit.setVisible(true);
                image.setVisible(false);
            }
        });
        optionB[3].addActionListener(signOut);
        ActionListener exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(menuFrame,"Are you sure to exit?","Quit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (status == 0){
                    menuFrame.dispose();
                    System.exit(0);
                }
            }
        };
        optionB[4].addActionListener(exit);
        options.setVisible(true);
        menuFrame.add(options);
        menuFrame.setLayout(null);
        menuFrame.setVisible(true);
    }
    public static JPanel showLevels(JLabel image, JFrame frame, Clip clip){
        JPanel levelPanel = GUI.createPanel(750,225,550,400,new Color(200,255,200));
        levelPanel.setLayout(null);
        levelPanel.setVisible(false);
        JLabel info = GUI.createLabel(100,30,200,50,null,"Select a level to start game");
        levelPanel.add(info);
        JButton[] levels = new JButton[player.getLevel()];
        for (int i = 0; i < player.getLevel(); i++) {
            levels[i] = new JButton("level "+(i+1));
            levels[i].setFont(new Font(Font.SERIF,Font.CENTER_BASELINE,14));
            if (i > 2 && i <= 5){
                levels[i].setBounds(220,60*(i%3)+100,100,40);
                GUI.addButtonGUI(levels[i],210,60*(i%3)+100,100,40,new Color(0,200,255));
            }
            else if (i < 3){
                levels[i].setBounds(100,60*i+100,100,40);
                GUI.addButtonGUI(levels[i],90,60*i+100,100,40,new Color(0,200,255));
            }
            else{
                levels[i].setBounds(340,60*(i%3)+100,100,40);
                GUI.addButtonGUI(levels[i],330,60*(i%3)+100,100,40,new Color(0,200,255));
            }
            int finalI = i;
            levels[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clip.stop();
                    frame.dispose();
                    startLevel((finalI +1));
                }
            });
            levels[i].setVisible(true);
            levelPanel.add(levels[i]);
        }
        JButton back = new JButton("return");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelPanel.setVisible(false);
                image.setVisible(true);
            }
        });
        back.setBounds(200,350,150,30);
        GUI.addButtonGUI(back,200,350,150,30,Color.cyan);
        levelPanel.add(back);
        return levelPanel;
    }
    public static JPanel showHelp(JLabel image){
        JPanel helpPanel = GUI.createPanel(750,225,550,400,new Color(200,255,200));
        helpPanel.setLayout(null);
        helpPanel.setVisible(false);
        JLabel info = GUI.createLabel(100,30,300,50,null,"Introduction of SUT Farm Frenzy");
        helpPanel.add(info);
        String text = "If you thought life in the big city was crazy, wait until you get a load of the country life in Farm Frenzy," +
                "\na super-fun arcade game that lets you show Old MacDonald how it's done!" +
                "\nDaily chores have never been more entertaining as you cultivate your fields, feed your animals and gather the items they produce." +
                "\nBetter keep the bears away from your ducks, though, or you won't have any eggs to sell at the market!" +
                "\nYou can spend the money you earn on upgrading your farm to churn out more products faster than before.\n" +
                "\n" +
                "Offering plenty of mouse-clicking action, just enough strategy to keep things interesting and an amusing cast of characters," +
                "\nSUT Farm Frenzy is a game the whole family can enjoy!" +
                "\n Created and designed by Mohammad Hossein Shafizadegan" +
                "\n and Amir Hossein Yari" +
                "\n all rights reserved.";
        JTextArea info1 = GUI.createTextArea(50,100,400,200,null,text);
        info1.setBorder(null);
        info1.setFont(new Font(Font.DIALOG,Font.CENTER_BASELINE,15));
        JScrollPane pane = new JScrollPane(info1);
        pane.setBounds(50,100,450,200);
        pane.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),5));
        helpPanel.add(pane);
        JButton back = new JButton("return");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpPanel.setVisible(false);
                image.setVisible(true);
            }
        });
        back.setBounds(200,350,150,30);
        GUI.addButtonGUI(back,200,350,150,30,Color.cyan);
        helpPanel.add(back);
        return helpPanel;
    }
    public static JPanel editProfile(JTextArea info2, JLabel image){
        JPanel editPanel = GUI.createPanel(750,225,550,400,new Color(200,255,200));
        JLabel info = GUI.createLabel(100,40,300,30,null,"You can edit your profile hear.");
        editPanel.add(info);
        String[] labelNames = {"Enter username: ", "Enter password: "};
        JLabel[] jLabels = new JLabel[2];
        for (int i = 0; i < 2; i++) {
            jLabels[i] = GUI.createLabel(150,80*i+100,200,40,null,labelNames[i]);
            jLabels[i].setFont(new Font(Font.DIALOG,Font.CENTER_BASELINE,14));
            editPanel.add(jLabels[i]);
        }
        String[] userData = FileManager.getUserData(authentication.getUsers(), player.getName());
        JTextField[] jTextFields = new JTextField[2];
        for (int i = 0; i < 2; i++) {
            jTextFields[i] = GUI.createTextField(150,80*i+140,200,40);
            jTextFields[i].setFont(new Font(Font.DIALOG,Font.ITALIC,14));
            jTextFields[i].setText(userData[i]);
            editPanel.add(jTextFields[i]);
        }
        JButton confirm = new JButton("confirm");
        GUI.addButtonGUI(confirm,200,350,150,30,Color.green);
        confirm.setBounds(200,350,150,30);
        ActionListener confirming = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = false;
                for (int i = 0; i < 2; i++) {
                    if (jTextFields[i].getText().equals(""))
                        b = true;
                }
                if (b)
                    JOptionPane.showMessageDialog(editPanel,"Some fields are empty","ERROR",JOptionPane.ERROR_MESSAGE);
                else {
                    String input = jTextFields[0].getText()+","+jTextFields[1].getText()+","+player.getLevel()+","+player.getCoins();
                    username = jTextFields[0].getText();
                    FileManager.remove(authentication.getUsers(),username);
                    FileManager.addToFile(authentication.getUsers(),input);
                    String[] userData = FileManager.getUserData(authentication.getUsers(),username);
                    String text = "Player: "+userData[0]+"\t\t\tCoins: "+userData[3];
                    info2.setText(text);
                    JOptionPane.showMessageDialog(editPanel,"Your profile edited successfully","Edit profile",JOptionPane.INFORMATION_MESSAGE);
                    editPanel.setVisible(false);
                    image.setVisible(true);
                }
            }
        };
        confirm.addActionListener(confirming);
        editPanel.add(confirm);
        editPanel.setLayout(null);
        editPanel.setVisible(false);
        return editPanel;
    }
    public static void startLevel(int levelNum){
        System.out.println(levelNum);
    }
}
