import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainLayout
{
    private static final String movieForm =  "Movie Form";
    private static final String actorForm = "Actor Form";    
    
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Movie Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // This JPanel is the base for CardLayout for other JPanels.
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout(20, 20));

        MovieForm win1 = new MovieForm();
        contentPane.add(win1, movieForm);
        ActorForm win2 = new ActorForm();
        contentPane.add(win2, actorForm);

        JPanel buttonPanel = new JPanel();
        String[] tables = { "Movies", "Actors" };
        JComboBox<String> tablesBox = new JComboBox<>(tables);
        
        buttonPanel.add(tablesBox);

        // Switches the card
        tablesBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
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

