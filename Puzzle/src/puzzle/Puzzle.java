package puzzle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static puzzle.StartingMenu.imageList;

/**
 *
 * @author Jay Lean
 */
public class Puzzle extends JFrame implements ActionListener{
    
    private JPanel centerPanel;
    private JLabel label;
    private JButton[][] button = new JButton[4][3];
    private Image source;
    private Image image;
    int pos[][], width, height;
    
    
    public Puzzle() {
            pos = new int[][]{
                    {0,1,2},
                    {3,4,5},
                    {6,7,8},
                    {9,10,11}
    
                            };
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4,3));
        
        ImageIcon icona = new ImageIcon((String)imageList.getSelectedItem());      //casting 
        source = icona.getImage();
        width = icona.getIconWidth();
        height = icona.getIconHeight();
        
        add(Box.createRigidArea(new Dimension(0,5)), BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                if(j==2 && i == 3){                                                 //l'ultima casella, quindi quella vuota 
                    label = new JLabel("");
                    //centerPanel.add(label);
                }else{
                    button[i][j] = new JButton();
                    button[i][j].addActionListener(this);
                   //centerPanel.add(button[i][j]);
                    image = createImage(new FilteredImageSource(source.getSource(),                    //creo l'immagine tagliuzzato 
                            new CropImageFilter(j*width/3, i*height/4, (width/3)+1, (height/4))        //con questo metodo tagliuzzo l'immagine 
                                                                )
                                        );
                    button[i][j].setIcon(new ImageIcon(image));                                               //assegno ogni pezzettino ad un bottone nella determinata casella
                }
                }  
        }
        
        disordina();
        
        setSize(1500,1000);
        setTitle("Puzzle di Lin");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                         //mi chiude solo la finestra corrente, e quindi non la finestra menù
        setVisible(true);
        
    }
    
    public void disordina(){
                                                                                 //aggiungo i bottoni disordinatamente, ma che sia solvibile l'immagine (l'ho testato personalmente e funziona!)
        centerPanel.add(button[0][0]);
        centerPanel.add(button[0][1]);
        centerPanel.add(button[0][2]);
        centerPanel.add(button[1][1]);
        centerPanel.add(button[2][0]);
        centerPanel.add(button[1][2]);
        centerPanel.add(button[1][0]);
        centerPanel.add(button[3][1]);
        centerPanel.add(button[2][1]);
        centerPanel.add(button[3][0]);
        centerPanel.add(button[2][2]);
        centerPanel.add(label);
    }
    
    public static void main(String[] args) {
        
        new StartingMenu();
        //new Puzzle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                                                                                 //in questa parte del programma dico al bottone cosa deve fare quando viene premuto
        JButton button = (JButton) e.getSource();
        Dimension size = button.getSize();

        int labelX = label.getX();
        int labelY = label.getY();
        int buttonX = button.getX();
        int buttonY = button.getY();
        int buttonPosX = buttonX / size.width;
        int buttonPosY = buttonY / size.height;
        int buttonIndex = pos[buttonPosY][buttonPosX];


        //i vari casi di spostametno 
        
        if (labelX == buttonX && (labelY - buttonY) == size.height ) {

             int labelIndex = buttonIndex + 3;

             centerPanel.remove(buttonIndex);
             centerPanel.add(label, buttonIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();
             
           
        }

        if (labelX == buttonX && (labelY - buttonY) == -size.height ) {

             int labelIndex = buttonIndex - 3;
             centerPanel.remove(labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.add(label, buttonIndex);
             centerPanel.validate();
             
           
        }

        if (labelY == buttonY && (labelX - buttonX) == size.width ) {

             int labelIndex = buttonIndex + 1;

             centerPanel.remove(buttonIndex);
             centerPanel.add(label, buttonIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();
             
            
        }

        if (labelY == buttonY && (labelX - buttonX) == -size.width ) {

             int labelIndex = buttonIndex - 1;

             centerPanel.remove(buttonIndex);
             centerPanel.add(label, labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();
             
             
        }
        
        gameWinning();
    }
 
    private void gameWinning() {                                                    //questo metodo serve per comunicare all'utente la vittoria
        int c=0, q=0;
        Component[] comp = new Component[12];
        for(int i=0; i<12; i++){
            comp[i] = centerPanel.getComponent(i);
        }
        
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
               
                if(i== 3 && j== 2){
                    
                    if(comp[q] == (JLabel) label)
                        c++;
                    //else break;
                } 
                
                else{
                    if(comp[q] == (JButton) button[i][j])
                    c++;
                    //else break;
                }
                q++;
               System.out.println(c);
               
            }
        }
        
        if(c==12)
            JOptionPane.showMessageDialog(this, "Congratulazioni hai vinto!","Puzzle di Lin", JOptionPane.INFORMATION_MESSAGE ); 
            }
        
          
    }


