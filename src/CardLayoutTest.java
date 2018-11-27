import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Here we are first declaring our class that will act as the
 * base for other panels or in other terms the base for CardLayout.
 */

public class CardLayoutTest
{
    private static final String CARD_JBUTTON =  "Card JButton";
    private static final String CARD_JTEXTFIELD = "Card JTextField";    
    private static final String CARD_JRADIOBUTTON = "Card JRadioButton";

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Movie Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // This JPanel is the base for CardLayout for other JPanels.
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout(20, 20));

        /* Here we be making objects of the Window Series classes
         * so that, each one of them can be added to the JPanel 
         * having CardLayout. 
         */
        MovieForm win1 = new MovieForm();
        contentPane.add(win1, CARD_JBUTTON);
        Window2 win2 = new Window2();
        contentPane.add(win2, CARD_JTEXTFIELD);


        /* We need two JButtons to go to the next Card
         * or come back to the previous Card, as and when
         * desired by the User.
         */

        JPanel buttonPanel = new JPanel();
        String[] tables = { "Movies", "Actors" };
        final JComboBox tablesBox = new JComboBox(tables);
        
        buttonPanel.add(tablesBox);


        /* Adding the ActionListeners to the JButton,
         * so that the user can see the next Card or
         * come back to the previous Card, as desired.
         */
        tablesBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.previous(contentPane);
            }
        });

        // Adding the contentPane (JPanel) and buttonPanel to JFrame.
        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
} 

