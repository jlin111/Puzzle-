package puzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jay Lean
 */
public class StartingMenu extends JFrame{
    
    private JPanel pannelloPrincipale, pannelloTitolo, pannelloCentrale;
    private JLabel titolo, sotto_titolo;
    private JButton play_button, quit_button;
    public static JComboBox imageList;
    private String[] imageString = {"","thanos.jpg", "Captain_America.jpg", "IronMan.jpg", "Thor.jpg", "Hulk.jpg", "Doctor_Strange.jpg", "SpiderMan.jpg", "Black_Panther.jpg", "Black_Widow.jpg"};
    
    
    public StartingMenu(){
        pannelloPrincipale = new JPanel(new GridLayout(2,1));
        pannelloPrincipale.setBackground(Color.WHITE);
        pannelloTitolo = new JPanel(new GridLayout(2,1));       
        pannelloTitolo.setBackground(new Color(255,238,186));
        pannelloCentrale = new JPanel(new GridLayout(3,1));
        //pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
        pannelloCentrale.setBackground(Color.WHITE);
        titolo = new JLabel("Puzzle"); 
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.BOTTOM);
        titolo.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 81)); 
        titolo.setForeground(Color.ORANGE);
        
        sotto_titolo = new JLabel("-Avengers limited edition-"
                + "   v1.0"); 
        sotto_titolo.setHorizontalAlignment(JLabel.CENTER);
        sotto_titolo.setVerticalAlignment(JLabel.NORTH);
        sotto_titolo.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 18)); 
        sotto_titolo.setForeground(new Color(90,39,41));
        
        imageList = new JComboBox(imageString);
        imageList.setBackground(new Color(176,117,56));
        //imageList.setPreferredSize(new Dimension(1,25));
        imageList.setSize(100, imageList.getPreferredSize().height);
                
        play_button = new JButton("PLAY");
        quit_button = new JButton("QUIT");
        play_button.setBackground(new Color(242,209,107));
        quit_button.setBackground(new Color(242,209,107));
        play_button.addActionListener(play_action);
        quit_button.addActionListener(quit_action);
        
       // this.getContentPane().add(BorderLayout.NORTH, pannelloCentrale);
        
        pannelloTitolo.add(titolo);
        pannelloTitolo.add(sotto_titolo);
        pannelloCentrale.add(imageList);
        pannelloCentrale.add(play_button);
        pannelloCentrale.add(quit_button);
        pannelloPrincipale.add(pannelloTitolo, BorderLayout.CENTER);
        pannelloPrincipale.add(pannelloCentrale, BorderLayout.CENTER);
        add(pannelloPrincipale);

        setSize(400,600);
        setTitle("Puzzle Game");
        setResizable(false);      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
           
        ActionListener play_action = new ActionListener() {
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            
            new Puzzle();
        }
        };
      
private JFrame frame;
       
            ActionListener quit_action = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            frame = new JFrame();       
        if(JOptionPane.showConfirmDialog(frame, "Confermi di voler uscire? ", "Puzzle", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) 
            System.exit(0);
        }
                
            };
  
}
