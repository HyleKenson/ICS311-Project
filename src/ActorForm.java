import java.awt.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

class ActorForm extends JPanel implements ActionListener {
	
    private JButton submitButton;

    JTextField actorIDArea = new JTextField();
    JTextArea actorFnameArea = new JTextArea(1, 50);
    JTextArea actorLnameArea = new JTextArea(1, 50);
    JTextArea actorGenderArea = new JTextArea(1, 1);

    private int rowCount = 1;

    public ActorForm() {
        //construct components
        submitButton = new JButton ("Submit");

        //adjust size and set layout
        setPreferredSize (new Dimension (400, 550));
        setLayout (null);

        //set component bounds 
        actorIDArea.setBorder(BorderFactory.createTitledBorder("Actor ID"));
        actorIDArea.setBounds (0, 0, 400, 50);
        actorIDArea.setEditable(true);
        actorFnameArea.setBorder(BorderFactory.createTitledBorder("Actor First Name"));
        actorFnameArea.setBounds (0, 200, 400, 50);
        actorFnameArea.setEditable(false);
        actorLnameArea.setBorder(BorderFactory.createTitledBorder("Actor Last Name"));
        actorLnameArea.setBounds (0, 250, 400, 50);
        actorLnameArea.setEditable(false);
        actorGenderArea.setBorder(BorderFactory.createTitledBorder("Actor Gender"));
        actorGenderArea.setBounds (0, 300, 400, 50);
        actorGenderArea.setEditable(false);
        submitButton.setBounds (0, 100, 100, 50);


        //add components
        add(actorIDArea);
        add(actorFnameArea);
        add(actorLnameArea);
        add(actorGenderArea);

        add(submitButton);
        
        //button listers
        submitButton.addActionListener(this);
    }
    // Sets text area using first row from actor
    public void getRow(int actorID) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ics311project?user=root&password=ics311");
    		System.out.println("Connection Object Created : " + con);
    		Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from actor");
            rs.absolute(actorID);
            if(rs.next()) {
            	rs.absolute(actorID);
                actorFnameArea.setText(rs.getString(2));
                actorLnameArea.setText(rs.getString(3));
                actorGenderArea.setText(rs.getString(4));
            } else {
                actorFnameArea.setText("N/A");
                actorLnameArea.setText("N/A");
                actorGenderArea.setText("N/A");
            }
    		st.close();
            rs.close();
        } catch (Exception ex) { ex.printStackTrace();}
    }

	// Button private adapter class
	public void actionPerformed(ActionEvent event) {
		Object srcObj = event.getSource();
		if (srcObj == submitButton) {
			int actorID = Integer.parseInt(actorIDArea.getText());
            getRow(actorID);
		}
	}
}