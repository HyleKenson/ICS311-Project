import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Window1 extends JPanel
{
    /*
     * Here this is our first Card of CardLayout, which will
     * be added to the contentPane object of JPanel, which
     * has the LayoutManager set to CardLayout.
     * This card consists of Two JButtons.
     */  
    private ActionListener action;

    public Window1() 
    {
        init();
    }

    private void init() 
    {
        final JButton clickButton = new JButton("CLICK ME");
        final JButton dontClickButton = new JButton("DON\'T CLICK ME");     

        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == clickButton)
                {
                    JOptionPane.showMessageDialog(null, "Hello there dude!"
                                                , "Right Button", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (ae.getSource() == dontClickButton)
                {
                    JOptionPane.showMessageDialog(null, "I told you not to click me!"
                                                        , "Wrong Button", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        clickButton.addActionListener(action);
        dontClickButton.addActionListener(action);

        add(clickButton);
        add(dontClickButton);
    }
}