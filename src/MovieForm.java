import java.awt.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

class MovieForm extends JPanel implements ActionListener {
	
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    JTextArea movieIDArea = new JTextArea(4, 50);
    JTextArea movieTitleArea = new JTextArea(4, 50);
    JTextArea movieYearArea = new JTextArea(4, 50);
    JTextArea movieRuntimeArea = new JTextArea(4, 50);
    JTextArea movieBudgetArea = new JTextArea(4, 50);
    JTextArea movieGrossArea = new JTextArea(4, 50);
    JTextArea movieContentRatingArea = new JTextArea(4, 50);
    private int rowCount = 1;

    public MovieForm() {
        //construct components
        firstButton = new JButton ("First");
        previousButton = new JButton ("Previous");
        nextButton = new JButton ("Next");
        lastButton = new JButton ("Last");
        
        //adjust size and set layout
        setPreferredSize (new Dimension (400, 550));
        setLayout (null);

        //set component bounds 
        movieIDArea.setBorder(BorderFactory.createTitledBorder("Movie ID"));
        movieIDArea.setBounds (0, 0, 400, 50);
        movieIDArea.setEditable(false);
        movieTitleArea.setBorder(BorderFactory.createTitledBorder("Movie Title"));
        movieTitleArea.setBounds (0, 50, 400, 50);
        movieTitleArea.setEditable(false);
        movieYearArea.setBorder(BorderFactory.createTitledBorder("Movie Year"));
        movieYearArea.setBounds (0, 100, 400, 50);
        movieYearArea.setEditable(false);
        movieRuntimeArea.setBorder(BorderFactory.createTitledBorder("Movie Runtime"));
        movieRuntimeArea.setBounds (0, 150, 400, 50);
        movieRuntimeArea.setEditable(false);
        movieBudgetArea.setBorder(BorderFactory.createTitledBorder("Movie Budget"));
        movieBudgetArea.setBounds (0, 200, 400, 50);
        movieBudgetArea.setEditable(false);
        movieGrossArea.setBorder(BorderFactory.createTitledBorder("Movie Gross"));
        movieGrossArea.setBounds (0, 250, 400, 50);
        movieGrossArea.setEditable(false);
        movieContentRatingArea.setBorder(BorderFactory.createTitledBorder("Movie Content Rating"));
        movieContentRatingArea.setBounds (0, 300, 400, 50);
        movieContentRatingArea.setEditable(false);

        firstButton.setBounds (0, 500, 100, 50);
        previousButton.setBounds (100, 500, 100, 50);
        nextButton.setBounds (200, 500, 100, 50);
        lastButton.setBounds (300, 500, 100, 50);

        //add components
        add(movieIDArea);
        add(movieYearArea);
        add(movieTitleArea);
        add(movieRuntimeArea);
        add(movieBudgetArea);
        add(movieGrossArea);
        add(movieContentRatingArea);
        add(firstButton);
        add(previousButton);
        add(nextButton);
        add(lastButton);
        
        //button listers
        firstButton.addActionListener(this);
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        lastButton.addActionListener(this);
        
    }

    // Sets text area
    public void getRow(String row) {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ics311project?user=root&password=ics311");
    		System.out.println("Connection Object Created : " + con);
    		Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from movie");
            int totalRows = 0;
            while(rs.next()){
                totalRows ++;
            }
            if(row == "First"){
                rowCount = 1;
                rs.absolute(rowCount);
            } else if(row == "Previous"){
                if(rowCount > 1){
                    rowCount --;
                }
                rs.absolute(rowCount);
            } else if(row == "Next"){
                if(rowCount < totalRows){
                    rowCount ++;
                }
                rs.absolute(rowCount);
            } else if(row == "Last"){
                rowCount = totalRows;
                rs.last();
            }
            
            // set text area based on columns
            movieIDArea.setText(rs.getString(1));
            movieTitleArea.setText(rs.getString(2));
            movieYearArea.setText(rs.getString(3));
            movieRuntimeArea.setText(rs.getString(4));
            movieBudgetArea.setText(rs.getString(5));
            movieGrossArea.setText(rs.getString(6));
            movieContentRatingArea.setText(rs.getString(7));
            
    		st.close();
            rs.close();
        } catch (Exception ex) { ex.printStackTrace();}
    }

	// Button private adapter class
	public void actionPerformed(ActionEvent event) {
		Object srcObj = event.getSource();
		if (srcObj == firstButton) {
            getRow("First");
		}
		else if (srcObj == previousButton) {
			getRow("Previous");
		}
		else if (srcObj == nextButton) {
			getRow("Next");
		}
		else if (srcObj == lastButton) {
			getRow("Last");
		}
	}
}