package puzzle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jay Lean
 */
public class Puzzle extends JFrame implements ActionListener{
    
    private JPanel centerPanel;
    private JLabel label;
    private JButton button;
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
        centerPanel.setLayout(new GridLayout(4,4));
        
        ImageIcon icona = new ImageIcon("guanto-di-thanos-colpisce-google.jpg");
        source = icona.getImage();
        width = icona.getIconWidth();
        height = icona.getIconHeight();
        
        add(Box.createRigidArea(new Dimension(0,5)), BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                if(j==2 && i == 3){                                                 //l'ultima casella, quindi quella vuota 
                    label = new JLabel("");
                    centerPanel.add(label);
                }else{
                    button = new JButton();
                    button.addActionListener(this);
                    centerPanel.add(button);
                    image = createImage(new FilteredImageSource(source.getSource(),                    //creo l'immagine tagliuzzato 
                            new CropImageFilter(j*width/3, i*height/4, (width/3)+1, (height/4))        //con questo metodo tagliuzzo l'immagine 
                                                                )
                                        );
                    button.setIcon(new ImageIcon(image));                                               //assegno ogni pezzettino ad un bottone nella determinata casella
                }
                }
        }
        
        setSize(1200,900);
        setTitle("Puzzle di Lin");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
            
    }
    

    public static void main(String[] args) {
        
        new Puzzle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        Dimension size = button.getSize();

        int labelX = label.getX();
        int labelY = label.getY();
        int buttonX = button.getX();
        int buttonY = button.getY();
        int buttonPosX = buttonX / size.width;
        int buttonPosY = buttonY / size.height;
        int buttonIndex = pos[buttonPosY][buttonPosX];



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
    }
  
}
