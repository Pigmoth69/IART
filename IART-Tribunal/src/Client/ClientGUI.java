package Client;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import org.json.JSONException;

public class ClientGUI {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
    private ArrayList<JTextField> form;
    private JCheckBox eliButt;
    private boolean eli;

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

      statusLabel.setSize(350,50);
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

       /*
       GAButton.setPreferredSize(new Dimension(400, 50));
       SAButton.setPreferredSize(new Dimension(400, 50));
       GASAButton.setPreferredSize(new Dimension(400, 50));
        */

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
            //String args[] = {"population size", "20", "mutation prob", "0.05", "generations", "150", "Number of tribunals", "50"};
            GAgui();
         }
         else if( command.equals( "Simulated Annealing" ) )  {
            statusLabel.setText("Simulated Annealing"); 
            restartWindow();
             //String args[] = {"Temperature","1.0", "alfa", "0.95", "generations", "500", "Number of tribunals", "50"};
             SAgui();
         }
         else if( command.equals("Generic + Simulated")){
            statusLabel.setText("Generic + Simulated");
            restartWindow();
             //String args[] = {"Temperature","alfa", "generations", "Number of tribunals"};
             GASAgui();
         }
         else if( command.equals("SubmitGA")){
             String eliS = "true";
             if (!eliButt.isSelected())
                 eliS = "false";

             System.out.println("eliS: " + eliS);

             String arr[] = {"GA", form.get(0).getText(), form.get(1).getText(), form.get(2).getText(), form.get(3).getText(), eliS};
             mainFrame.dispose();
             try {
                 Main abc = new Main(arr);
             } catch (JSONException e1) {
                 e1.printStackTrace();
             } catch (InterruptedException e1) {
                 e1.printStackTrace();
             }
         }
         else if( command.equals("SubmitSA")){
             String arr[] = {"SA", form.get(0).getText(), form.get(1).getText(), form.get(2).getText(), form.get(3).getText()};
             mainFrame.dispose();
             try {
                 Main abc = new Main(arr);
             } catch (JSONException e1) {
                 e1.printStackTrace();
             } catch (InterruptedException e1) {
                 e1.printStackTrace();
             }
          }
         else if( command.equals("SubmitGASA")){
             String eliS = "true";
             if (!eliButt.isSelected())
                 eliS = "false";

             String arr[] = {"GASA", form.get(0).getText(), form.get(1).getText(), form.get(2).getText(), form.get(3).getText(), form.get(4).getText(), form.get(5).getText(), form.get(6).getText(), eliS};
             mainFrame.dispose();
             try {
                 Main abc = new Main(arr);
             } catch (JSONException e1) {
                 e1.printStackTrace();
             } catch (InterruptedException e1) {
                 e1.printStackTrace();
             }
         }
      }
   }

    private void GAgui() {
        String args[] = {"population size", "20", "mutation prob", "0.05", "generations", "150", "Number of tribunals", "50"};

        form = new ArrayList<JTextField>();

        JTextField popSize = new JTextField("20", 8);
        form.add(popSize);
        JTextField mutProb = new JTextField("0.05", 8);
        form.add(mutProb);
        JTextField gen = new JTextField("150", 8);
        form.add(gen);
        JTextField nTribunais = new JTextField("50", 8);
        form.add(nTribunais);

        controlPanel.add(new JLabel("population size"));
        controlPanel.add(popSize);
        controlPanel.add(new JLabel("mutation prob"));
        controlPanel.add(mutProb);
        controlPanel.add(new JLabel("generations"));
        controlPanel.add(gen);
        controlPanel.add(new JLabel("Number of tribunals"));
        controlPanel.add(nTribunais);
        eliButt = new JCheckBox("Elitist");
        eliButt.setSelected(true);
        controlPanel.add(eliButt);

        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setActionCommand("SubmitGA");
        SubmitButton.addActionListener(new ButtonClickListener());

        controlPanel.add(SubmitButton);
    }

    private void SAgui() {
        String args[] = {"Temperature","1.0", "alfa", "0.95", "generations", "500", "Number of tribunals", "50"};

        form = new ArrayList<JTextField>();

        JTextField temp = new JTextField(args[1], 8);
        form.add(temp);
        JTextField alfa = new JTextField(args[3], 8);
        form.add(alfa);
        JTextField gen = new JTextField(args[5], 8);
        form.add(gen);
        JTextField nTribunais = new JTextField(args[7], 8);
        form.add(nTribunais);

        controlPanel.add(new JLabel("Temperature"));
        controlPanel.add(temp);
        controlPanel.add(new JLabel("Alfa"));
        controlPanel.add(alfa);
        controlPanel.add(new JLabel("Generations"));
        controlPanel.add(gen);
        controlPanel.add(new JLabel("Number of tribunals"));
        controlPanel.add(nTribunais);

        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setActionCommand("SubmitSA");
        SubmitButton.addActionListener(new ButtonClickListener());

        controlPanel.add(SubmitButton);
    }

    private void GASAgui() {
        String args[] = {"Temperature","1.0", "alfa", "0.95", "generations", "500", "Number of tribunals", "50"};

        form = new ArrayList<JTextField>();

        JTextField popSize = new JTextField("20", 8);
        form.add(popSize);
        JTextField mutProb = new JTextField("0.05", 8);
        form.add(mutProb);
        JTextField genGA = new JTextField("150", 8);
        form.add(genGA);


        controlPanel.add(new JLabel("GA population size"));
        controlPanel.add(popSize);
        controlPanel.add(new JLabel("GA Mutation probability"));
        controlPanel.add(mutProb);
        controlPanel.add(new JLabel("GA generations"));
        controlPanel.add(genGA);
        eliButt = new JCheckBox("Elitist");
        eliButt.setMnemonic(KeyEvent.VK_E);
        eliButt.setSelected(true);
        controlPanel.add(eliButt);

        JTextField temp = new JTextField(args[1], 8);
        form.add(temp);
        JTextField alfa = new JTextField(args[3], 8);
        form.add(alfa);
        JTextField genSA = new JTextField(args[5], 8);
        form.add(genSA);
        JTextField nTribunais = new JTextField("50", 8);
        form.add(nTribunais);


        controlPanel.add(new JLabel("SA Temperature"));
        controlPanel.add(temp);
        controlPanel.add(new JLabel("SA Alfa"));
        controlPanel.add(alfa);
        controlPanel.add(new JLabel("SA Generations"));
        controlPanel.add(genSA);
        controlPanel.add(new JLabel("Number of tribunals"));
        controlPanel.add(nTribunais);

        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setActionCommand("SubmitGASA");
        SubmitButton.addActionListener(new ButtonClickListener());

        controlPanel.add(SubmitButton);
        //mainFrame.pack();
    }



    private void restartWindow(){
	   mainFrame.dispose();
	   mainFrame = new JFrame("Java SWING Examples");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(1, 1));

	      headerLabel = new JLabel("",JLabel.CENTER );
	      statusLabel = new JLabel("",JLabel.CENTER);        

	      statusLabel.setSize(400,400);
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