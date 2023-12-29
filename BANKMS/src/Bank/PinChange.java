package Bank;

import javax.swing.*;//jframe etc
import java.awt.*;//image
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PinChange extends JFrame implements ActionListener
{
    JPasswordField pin,repin;
    JLabel text,pintext,repintext;
    JButton change,back;
    String pinnumber;
    PinChange(String pinnumber)

    {
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("ARABIC", Font.BOLD, 16));
        text.setBounds(250, 200, 500, 30);
        image.add(text);


        pintext = new JLabel("NEW PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("ARABIC", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("RALEWAY", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);


        repintext = new JLabel("Re-Enter NEW PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("ARABIC", Font.BOLD, 16));
        repintext.setBounds(165, 320, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("RALEWAY", Font.BOLD, 25));
        repin.setBounds(330, 320, 180, 25);
        image.add(repin);



        change = new JButton("Change");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }



    public void actionPerformed (ActionEvent ae)
    {
        if(ae.getSource()==change)
        {
            try
            {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (npin.equals(rpin))
                {
                    JOptionPane.showMessageDialog(null, "ENTERED PIN DOES NOT MATCH");
                    return;
                }
                if (npin.equals(" "))
                {
                    JOptionPane.showMessageDialog(null, "PLEASE ENTER PIN");
                    return;
                }
                if (rpin.equals(" "))
                {
                    JOptionPane.showMessageDialog(null, "PLEASE Re-ENTER PIN");
                    return;
                }
                Conn conn =new Conn();
                String query1 = "update bank set pin ='" + rpin + "'where pin='" + pinnumber + "'";
                String query2 = "update login set pin ='" + rpin + "'where pin='" + pinnumber + "'";
                String query3 = "update signupthree set pin ='" + rpin + "'where pin='" + pinnumber + "'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN CHANGED SUCCESSFULLY!!");

                setVisible(false);
                new Transaction(rpin).setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
        else
        {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }






    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
