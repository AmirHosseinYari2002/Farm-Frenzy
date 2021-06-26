import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GUI {
    public static File buttonSound = new File("src\\pictures\\mouseMove.wav");

    public static File buttonClickSound = new File("src\\pictures\\click.wav");

    public static JPanel setBackground(String URL){
        JPanel panelc = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                g.drawImage(Toolkit.getDefaultToolkit().getImage(URL),0,0,this);
            }
        };
        return panelc;
    }

    public static JPanel setBackground(Color color1, Color color2){
        JPanel panelc = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0f, 0.0f, color1,
                        getWidth(), getHeight(), color2, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        return panelc;
    }

    public static void addButtonGUI(JButton button, int x, int y, int width, int height, Color color){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color);
                button.setBounds(x-10,y-5,width+20,height+10);
                playSound(buttonSound).start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new JButton().getBackground());
                button.setBounds(x,y,width,height);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playSound(buttonClickSound).start();
            }
        });
    }

    public static JFrame createFrame(int x, int y, int width, int height,String title){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(x,y,width,height);
        return frame;
    }

    public static JPanel createPanel(int x, int y, int width, int height, Color color){
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(null);
        panel.setBounds(x,y,width,height);
        panel.setBorder(BorderFactory.createLineBorder(Color.black,3));
        return panel;
    }

    public static JLabel createLabel(int x, int y, int width, int height, Color color, String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.DIALOG,Font.CENTER_BASELINE,15));
        label.setBackground(color);
        label.setBounds(x,y,width,height);
        return label;
    }

    public static JTextArea createTextArea(int x, int y, int width, int height, Color color, String text){
        JTextArea textArea = new JTextArea(text);
        Font font = new Font(Font.SERIF,Font.BOLD,25);
        textArea.setEditable(false);
        textArea.setFont(font);
        textArea.setBackground(color);
        textArea.setBounds(x,y,width,height);
        textArea.setBorder(BorderFactory.createLineBorder(Color.black,3));
        return textArea;
    }

    public static JLabel createImage(int x, int y, int width, int height, String URL){
        ImageIcon imageIcon = new ImageIcon(URL);
        JLabel image = new JLabel(imageIcon);
        image.setBounds(x,y,width,height);
        return image;
    }

    public static JTextField createTextField(int x, int y, int width, int height){
        JTextField textField = new JTextField(20);
        textField.setBounds(x,y,width,height);
        textField.setFont(new Font(Font.SERIF,Font.ITALIC,20));
        textField.setBorder(BorderFactory.createLineBorder(Color.black,3));
        textField.setVisible(true);
        return textField;
    }

    public static Clip playSound(File file){
        Clip clip = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            if (file.getPath().equals("src\\pictures\\Farmville.wav"))
                clip.loop(1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }
}
