package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGUI {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public ClientGUI(){
      prepareGUI();
   }

   public static void main(String[] args){
	   ClientGUI swingControlDemo = new ClientGUI();  
      swingControlDemo.showEventDemo();       
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 3));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      }); 
      
      controlPanel = new JPanel();
	   controlPanel.setLayout(new FlowLayout());
	
	   mainFrame.add(headerLabel);
	   mainFrame.add(controlPanel);
	   mainFrame.add(statusLabel);
	   mainFrame.setVisible(true);
   }

   private void showEventDemo(){
      headerLabel.setText("Control in action: Button"); 

      JButton GAButton = new JButton("Generic Algorithm");
      /*
      JButton guilhermeButton1 = new JButton("Guilherme1");
      JButton guilhermeButton2 = new JButton("Guilherme2");
      JButton guilhermeButton3 = new JButton("Guilherme3");
      JButton guilhermeButton4 = new JButton("Guilherme4");
      JButton guilhermeButton5 = new JButton("Guilherme5");
      JButton guilhermeButton6 = new JButton("Guilherme6");
      JButton guilhermeButton7 = new JButton("Guilherme7");
      JButton guilhermeButton8 = new JButton("Guilherme8");
      JButton guilhermeButton9 = new JButton("Guilherme9");
      JButton guilhermeButton10 = new JButton("Guilherme10");
      */
      JButton SAButton = new JButton("Simulated Annealing");
      JButton GASAButton = new JButton("Generic + Simulated");

      GAButton.setActionCommand("Generic Algorithm");
      SAButton.setActionCommand("Simulated Annealing");
      GASAButton.setActionCommand("Generic + Simulated");

      GAButton.addActionListener(new ButtonClickListener()); 
      SAButton.addActionListener(new ButtonClickListener()); 
      GASAButton.addActionListener(new ButtonClickListener()); 

      controlPanel.add(GAButton);
      controlPanel.add(SAButton);
      controlPanel.add(GASAButton);       

      mainFrame.setVisible(true);  
   }

   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         if( command.equals( "Generic Algorithm" ))  {
            statusLabel.setText("Generic Algorithm");
            restartWindow();
            String args[] = {"8","0.01", "30", "10"};
            addButtons(args[]);
         }
         else if( command.equals( "Simulated Annealing" ) )  {
            statusLabel.setText("Simulated Annealing"); 
            restartWindow();
         }
         else if( command.equals("Generic + Simulated")){
            statusLabel.setText("Generic + Simulated");
            restartWindow();
         }  	
      }		
   }
   
   private void restartWindow(){
	   mainFrame.dispose();
	   mainFrame = new JFrame("Java SWING Examples");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 3));

	      headerLabel = new JLabel("",JLabel.CENTER );
	      statusLabel = new JLabel("",JLabel.CENTER);        

	      statusLabel.setSize(350,100);
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
	         }        
	      });    
	   controlPanel = new JPanel();
	   controlPanel.setLayout(new FlowLayout());
	
	   mainFrame.add(headerLabel);
	   mainFrame.add(controlPanel);
	   mainFrame.add(statusLabel);
	   mainFrame.setVisible(true); 
   }
}