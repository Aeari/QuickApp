/*
Created by Ari
TODO: Have the retrieve function to work when I specify a friend
*/
package quickapp;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
/**
 * @author REL
 */
public class QuickApp  {
    //Initialize all the variables I'm going to use
    JButton Sendbutton;
    JButton Getbutton;
    JTextField agef,weightf,swagf,namef;
    JLabel label,alerts;
    String msg;
    Gson gson;
    public static void main(String[] args) {
        // Start the app after creating a new instace of it
        // Should make QuickApp an immutable object
        QuickApp gui = new QuickApp();
        gui.go();
        
    }
    public Statement getState()
    //Make the JBDC connection to mysql and return a statement
    {
        Connection con;
        Statement stmt = null;
        try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/NAME OF DATABASE", "ENTER YOUR USERNAME HERE", "ENTER YOUR PASSWORD HERE");
        stmt = (Statement) con.createStatement();
        }
     catch(Exception e){} 
        return stmt;
    }
    public void go() {
//Created the frame and panel and put all the labels and text fields in, not great at this...        
 
JFrame frame = new JFrame();
JPanel panel = new JPanel();
agef = new JTextField("Age here",10);
weightf = new JTextField("Weight here",10);
swagf = new JTextField("Swag here",10);
namef = new JTextField("Name here",10);
alerts = new JLabel("Alerts");
panel.add(agef);
panel.add(weightf);
panel.add(swagf);
panel.add(namef);
label = new JLabel("To Database:");
Sendbutton = new JButton("Send to DB");
Sendbutton.addActionListener(new SendListener());
frame.getContentPane().add(BorderLayout.CENTER, panel);
frame.getContentPane().add(BorderLayout.SOUTH, Sendbutton);
frame.getContentPane().add(BorderLayout.NORTH, label);
frame.getContentPane().add(BorderLayout.WEST, alerts);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(220,240);
frame.setVisible(true);
    }
    public class SendListener implements ActionListener{
        //Not sure why I need an override here since I'm saying I'm implimenting ActionListener
        @Override
        public void actionPerformed(ActionEvent ae)
        {
        //Getting a Statement to use MySQL and then creating a Friend Object
        Statement stmt = getState();
        Friend John = getFriend();
        //Using the Java Object to JSON Library from Google
        Gson gson = new Gson();
        String MyJSONString = gson.toJson(John);
        //Concantinating the string to be sent to MySQL
        String msgtosend = "INSERT INTO 'NAME OF YOUR SQL TABLE' VALUES ('"+MyJSONString +"')";
        try { 
            //Sending the Full message to MySQL with the executeUpdate method
            stmt.executeUpdate(msgtosend);
        } catch (SQLException ex) {
            Logger.getLogger(QuickApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Closing connection after I sent the message
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuickApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public Friend getFriend()
    {
       // Returns a friend Object given all 4 fields have proper variables
       // agef, weightf, swagf, namef
        int tage,tweight,tswag;
        agef.selectAll();
        String agefield = agef.getSelectedText(); 
        agef.setText("");
        weightf.selectAll();
        String weightfield = weightf.getSelectedText();
        weightf.setText("");
        swagf.selectAll();
        String swagfield = swagf.getSelectedText();
        swagf.setText("");
        namef.selectAll();
        String tname = namef.getSelectedText();
        namef.setText("");
        tage = Integer.parseInt(agefield);
        tweight = Integer.parseInt(weightfield);
        tswag = Integer.parseInt(swagfield);
        
        Friend John;
        John = new Friend.Builder().age(tage).weight(tweight).swagpoints(tswag).name(tname).build();
        John.Speak();
        return John;
    }
}

