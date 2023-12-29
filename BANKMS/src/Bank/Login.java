package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{

    JButton login,signup,clear;
    JTextField cardTextField ;
    JPasswordField pinTextField;
    JLabel text,cardno,pin;

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);

         text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);

         cardno = new JLabel("Card No :");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120,150,150,30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300,150,230,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);

         pin = new JLabel("PIN :");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120,220,250,30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);

        login = new JButton("Sign-in");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);


        setSize(800,480);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed (ActionEvent ae)
    {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"'and pin = '"+pinnumber+"'";
            try
            {
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next())
                {
                    setVisible(false);
                    new Transaction(pinnumber).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"INCORRECT CARD NUMBER OR PIN");
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        } else if (ae.getSource() == signup) {

            setVisible(false);
            new SignupOne();
        }

    }

    public static void main(String[] args) {

        new Login();

    }

}