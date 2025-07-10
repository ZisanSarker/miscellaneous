import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Multiplication_Table extends JFrame{
    private Container c;
    private JLabel imglb,lb1;
    private ImageIcon icon,cover;
    private Font f1;
    private JTextField tf;
    private JButton btn;
    private JTextArea ta;
    Multiplication_Table(){
        initComponenet();
    }
    private void initComponenet(){
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.orange);
        cover = new ImageIcon("E:\\Project-Hub\\Java Project\\Multiplication Table\\kid123.jpg");
        f1 = new Font("Areal",Font.BOLD,20);
        imglb = new JLabel(cover);
        imglb.setBounds(20, 10, 350, 200);
        lb1 = new JLabel("Enter Any Number : ");
        lb1.setBounds(20, 220, 250, 40);
        lb1.setFont(f1);
        tf = new JTextField();
        tf.setBackground(Color.green);
        tf.setFont(f1);
        tf.setBounds(270, 225, 100, 40);
        btn = new JButton("Clear");
        btn.setFont(f1);
        btn.setBackground(Color.green);
        btn.setBounds(270, 270, 100, 40);
        ta = new JTextArea();
        ta.setFont(f1);
        ta.setBounds(20, 340, 350, 300);
        ta.setBackground(Color.green);
        c.add(imglb);
        c.add(lb1);
        c.add(tf);
        c.add(btn);
        c.add(ta);
        tf.addActionListener((ActionListener) new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"You didn't put any number");
                    return;

                }
                ta.setText("");
                int n = Integer.parseInt(tf.getText());
                ta.append("\n");
                for(int i=1;i<=10;i++){
                    int result = n*i;
                    String r = String.valueOf(result);
                    String first = String.valueOf(i);
                    String second = String.valueOf(n);
                    ta.append(second+" * "+first+" = "+r+"\n");
                }
            }
        });
        Handler handler = new Handler();
        btn.addActionListener(handler);

    }
    class Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==btn){
                tf.setText("");
                ta.setText("");
            }
        }
    }
    public static void main(String[] args){
        Multiplication_Table frame = new Multiplication_Table();
        frame.setBounds(400, 150, 400, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Multiplication Table");
        ImageIcon icon = new ImageIcon("E:\\Project-Hub\\Java Project\\Multiplication Table\\maths.png");
        frame.setIconImage(icon.getImage());
    }
}