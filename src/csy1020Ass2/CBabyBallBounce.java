/** 
Program:   Assignment 2: Application – Baby Ball Bounce 
Filename:  CBabyBallBounce.java 
@author:   © Ashleigh Claire Foster                        
Course:    BSc Business Computing (Systems) Year 1         
Module:    CSY1020 Problem Solving & Programming        
Tutor:     Gary Hill                                    
@version:  2.0 Incorporates Artificial Intelligence!   
Date:      17/01/17                                     
*/
package csy1020Ass2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Frame;
 
public class CBabyBallBounce extends JFrame implements ActionListener, ChangeListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton JBact, JBrun, JBreset, JB2player, JB4player, JBMulti, JBExit, JBTrans, JBUp, JBDown, JBLeft, JBRight, JBMiddle, JBBaby1, JBBaby2, JBBall, JBWall, JBCompass;
    private JButton[] JBGridbutton = new JButton[208];
	private JPanel JCentre, JRight, JBottom, JBottomL, JBottomR, JDs, JOpt, JArrows, JCompass, JFour;
    private JSlider slider;
    private JLabel jLTimer, jLScore, jLOption, jLSquare, jLDirection, jLColon, jLLeftRight, JLSpeed, JLGapLabel;
    private JTextField jTText1, jTText2, jTText3, jTText4, jTText5, jTGapField, jTOplay, JT101, JTDirect;
    private Icon iconAct, iconRun, iconReset, iconBaby1, iconBaby2,iconBaby3,iconBaby4,iconBall, iconCompass, iconWall, iconPause, iconFoot;
    private Random random;
    private int slidertime;	
    private javax.swing.Timer timerSecs,timerball,timerbaby;
    private int ticks = 0;
    private JMenuBar menubar;
    private JMenuItem exit, helptopic, about;
    private JMenu scenario, edit, controls, help;
    private int PositionBall = 100,baby1pos=99,baby2pos=110,baby3pos=166,baby4pos=171;
    int nGame=0;
    int baby3edge=1,baby4edge=1;
    int baby1total, baby2total = 0;
    int run=1, pause=2;
 
    public static void main(String[] args)
    {
    	CBabyBallBounce frame = new CBabyBallBounce(); //Declaring a new frame called CBabyBallBounce
        frame.setSize(825, 585); //Setting the frame size
        frame.createGUI(); //Setting the GUI
        frame.setVisible(true); //Setting the frame to display.
        frame.setResizable(false); //This code stops the end-user from resizing the application.
        frame.setLocationRelativeTo(null); //This code sets the application in the centre of the screen. I got this code from here: How to center a window in java? (2017) Available at: http://stackoverflow.com/questions/144892/how-to-center-a-window-in-java (Accessed: 10 February 2017).
        frame.setTitle("CBabyBallBounce - Baby Ball Bounce Application"); //This code sets the title on top of the window of the application.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This code allows the end-user to exit the application.
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("greenfoot_l.png")));; //Setting the icon on the GUI window using the following link: Hill, G., WXYZ, 200 and Course (no date) SECTION A - JAVA INTRODUCTION - © Gary Hill 2005. Available at: http://www.computing.northampton.ac.uk/~gary/csy3019/CSY3019SectionA.html#13_-_GUI:_Look_and_Feel (Accessed: 4 March 2017).
        
        //Referencing menubar = Mr. Java Help (2009) Java GUI- add MENU BAR TUTORIAL, help swing, JMenuBar, JMenu. Available at: https://www.youtube.com/watch?v=dwLkDGm5EBc (Accessed: 22 January 2017).
    }
 //This next part creates a graphical user interface.
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
        
        //Creating a menu bar above the GUI
        
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        //Creating menus within the Menu Bar
        scenario = new JMenu ("Scenario");
        menubar.add(scenario);
        
        
        edit = new JMenu ("Edit");
        menubar.add(edit);
        
        
        controls = new JMenu ("Controls");
        menubar.add(controls);
        
        
        help = new JMenu ("Help");
        menubar.add(help);
        
        //Add list items
        exit = new JMenuItem ("Exit");
        exit.addActionListener(this);
        scenario.add(exit);
        
        helptopic = new JMenuItem ("Help Topic");
        about = new JMenuItem ("About");
        help.add(helptopic);
        help.add(about);
        about.addActionListener(this);
        helptopic.addActionListener(this);
        
       
        //Adding the babies to the game.
        
        try {
        	iconBaby1 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("baby1.png")));
            iconBaby3 =  new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("baby1.png")));
        
        }
        
        catch (Exception b1) {
        	System.err.println("Baby1 Icon ImageIcon" +b1);
        }
        
        try {
        	iconBaby2 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("baby2.png")));
            iconBaby4 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("baby2.png")));
        
        
        }
        
        catch (Exception b2) {
        	System.err.println("Baby2 Icon ImageIcon" +b2);
        }
        //Adding ball actions
        try {
        	iconBall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("ball.png")));
        }
        
        catch (Exception ball) {
        	System.err.println("Ball Icon ImageIcon" +ball);
        }
        //Adding the wall actions
        try {
        	iconWall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("bricks2.jpg")));
        }
        
        catch (Exception wall) {
        	
        	System.err.println("Wall Icon ImageIcon" +wall);
        }
        //Adding the pause icon.
        try {
        	iconPause = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("pause.png")));
        }
        catch (Exception pause) {
        	System.err.println("Pause Icon ImageIcon" +pause);
        }
        //Adding the greenfoot icon.
        try {
        	iconFoot = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("greenfoot.png")));
        }
        catch (Exception GFoot) {
        	System.err.println("Greenfoot Icon ImageIcon" +GFoot);
        }
        
        //Adding images etc... to the java game application came from NILE assessment information assignment 2 area: (No Date) Available at: https://nile.northampton.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_64620_1&content_id=_3067632_1 (Accessed: 22 January 2017).
        
        //Panels for inside the GUI
 
        JCentre = new JPanel();
        JCentre.setPreferredSize(new Dimension(610, 475));
        JCentre.setBackground(Color.white);
        window.add(JCentre);
        JCentre.setLayout(new GridLayout(13, 16));
        
        //Adding the buttons to the central panel.
        for (int count =0; count<208; count++)
        {
        	JBGridbutton[count] = new JButton("");
        	JBGridbutton[count].setBackground(Color.white);
        	JBGridbutton[count].setBorderPainted(false);
        	JBGridbutton[count].setMargin(new Insets(0, 0, 0, 0));
        	
        	if (count == 67)
        	{
        		JBGridbutton[count].setForeground(Color.black);
        	}
        	
        	//Adding the wall to the central panel.
        	
        	int wall = 0;
        	wall = count%16;
        	
        
        		if (wall == 8 || wall == 9 )
        		
        		{
        			JBGridbutton[count].setIcon(iconWall);
        		}
        	
        	//Adding image icons to the buttons - Code structure came from the 14th February 2017 lesson
        	
        	//Adding babies to the buttons in the central panel
        	if (count==99)
        	{
        		JBGridbutton[count].setIcon(iconBaby1);        		
        	}
        	if (count==110) 
        	{
        		JBGridbutton[count].setIcon(iconBaby2);
        	}
        	//Adding a ball to the buttons in the central panel
        	if (count==100)
        	{
        		JBGridbutton[count].setIcon(iconBall);
        	}
        	
        	//Adding the grid buttons to the central panel in order to place the images in the appropriate places.
        	JCentre.add(JBGridbutton[count]);
        	JBGridbutton[count].addActionListener(this);
        }
        //Adding Baby1 to the Centre panel
        JBBaby1 = new JButton();
        JBBaby1.setIcon(iconBaby1);
        JBBaby1.setBorderPainted(false);
        JBBaby1.setBackground(Color.white);
        JBBaby1.addActionListener(this);
        //Adding Baby2 to the Centre panel
        JBBaby2 = new JButton();
        JBBaby2.setIcon(iconBaby2);
        JBBaby2.setBorderPainted(false);
        JBBaby2.setBackground(Color.white);
        JBBaby2.addActionListener(this);
        //Adding ball to the Centre panel
        JBBall = new JButton();
        JBBall.setIcon(iconBall);
        JBBall.setBorderPainted(false);
        JBBall.setBackground(Color.white);
        JBBall.addActionListener(this);
        //Adding wall to the Centre panel
        JBWall = new JButton();
        JBWall.setIcon(iconWall);
        JBWall.setBorderPainted(false);
        JBWall.addActionListener(this);
        
        JRight = new JPanel();
        JRight.setPreferredSize(new Dimension(170, 475));
        JRight.setBackground(Color.lightGray);
        window.add(JRight);
        
        JBottom = new JPanel();
        JBottom.setPreferredSize(new Dimension(775, 42));
        JBottom.setBackground(Color.lightGray);
        window.add(JBottom);
        
        JBottomL = new JPanel();
        JBottomL.setPreferredSize(new Dimension (380, 33));
        JBottomL.setBackground(Color.lightGray);
        JBottom.add(JBottomL);
        
        JBottomR = new JPanel();
        JBottomR.setPreferredSize(new Dimension (380, 33));
        JBottomR.setBackground(Color.lightGray);
        JBottom.add(JBottomR);
        
        //Panels for inside the right column

        JDs = new JPanel();
        JDs.setPreferredSize(new Dimension (170, 90));
        JDs.setBackground(Color.lightGray);
        JRight.add(JDs);
        
        
        JOpt = new JPanel();
        JOpt.setPreferredSize(new Dimension (170, 90));
        JOpt.setBackground(Color.lightGray);
        JRight.add(JOpt);
        
        JArrows = new JPanel();
        JArrows.setPreferredSize(new Dimension (170, 90));
        JArrows.setBackground(Color.lightGray);
        JRight.add(JArrows);
        
        
        try {
        	iconCompass = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("north.jpg")));
        }
        
        catch (Exception co) {
        	System.err.println("Compass Icon ImageIcon" +co);
        }
        
        JCompass = new JPanel();
        JCompass.setPreferredSize(new Dimension (170, 90));
        JCompass.setBackground(Color.lightGray);
        JRight.add(JCompass);
        JCompass.setLayout(new GridLayout(1,1));
        //Adding Compass
        JBCompass = new JButton();
        JCompass.add(JBCompass);
        JBCompass.setIcon(iconCompass);
        JBCompass.addActionListener(this);
        JBCompass.setBackground(Color.lightGray);
        
        JFour = new JPanel();
        JFour.setPreferredSize(new Dimension (170, 90));
        JFour.setBackground(Color.black);
        JFour.setLayout(new GridLayout(2,2));
        JRight.add(JFour);
        
        //Buttons and icons for the bottom of the GUI
        
        try {
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("step.png")));
        }
        
        catch (Exception e) {
        	System.err.println("Act Icon ImageIcon" +e);
        }
        
        JBact = new JButton("Act");
        JBottomL.add(JBact);
        JBact.setIcon(iconAct);
        JBact.addActionListener(this);
        
        try {
        	iconRun = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("run.png")));
        }
        
        catch (Exception r) {
        	System.err.println("Run Icon ImageIcon" +r);
        }
        
        JBrun = new JButton("Run");
        JBottomL.add(JBrun);
        JBrun.setIcon(iconRun);
        JBrun.addActionListener(this);
        
        try {
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("reset.png")));
        }
        
        catch (Exception re) {
        	System.err.println("Reset Icon ImageIcon" +re);
        }
        
        JBreset = new JButton("Reset");
        JBottomL.add(JBreset);
        JBreset.setIcon(iconReset);
        JBreset.addActionListener(this);
        
        //Buttons for right column
        JB2player = new JButton("2 Player");
        JFour.add(JB2player);
        JB2player.addActionListener(this);
        
        JB4player = new JButton("4 Player");
        JFour.add(JB4player);
        JB4player.addActionListener(this);
        
        JBMulti = new JButton("Multi");
        JFour.add(JBMulti);
        JBMulti.addActionListener(this);
        
        JBExit = new JButton("Exit");
        JFour.add(JBExit);
        JBExit.addActionListener(this);
        
        //Adding JLabel to the bottom panel
        JLSpeed = new JLabel ("Speed:");
        JBottomR.add(JLSpeed);
        
        //jTGapField = new JTextField();
        //JBottomR.add(jTGapField);
        
        
        //Adding slider to the bottom panel
        slider = new JSlider(JSlider.HORIZONTAL, 1000, 1000);
        //JBottomR.add(slider);
        JBottomR.add(slider);
        slider.setInverted(true);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(200);
        slider.setPaintTicks(true);
        
        
        //jTGapField.setText(Integer.toString(slider.getValue()));
        //timerDigital = new javax.swing.Timer(1000, this);

        
        //Text fields and labels to black panel
        jLTimer = new JLabel ("            DIGITAL TIMER              ");
        JDs.add(jLTimer);
        jLTimer.setForeground(Color.black);
        
        jTText1 = new JTextField ("00", 2);
        JDs.add(jTText1);
        
        jLColon = new JLabel (":");
        JDs.add(jLColon);
        
        jTText2 = new JTextField ("00", 2);
        JDs.add(jTText2);
        
        jLColon = new JLabel (":");
        JDs.add(jLColon);
        
        jTText3 = new JTextField ("00", 2);
        JDs.add(jTText3);
     //   timerSecs = new javax.swing.Timer(1000, this);
        
        
        jLScore = new JLabel("                 SCORE                  ");
        JDs.add(jLScore);
        jLScore.setForeground(Color.black);
        
        jTText4 = new JTextField ("", 2);
        JDs.add(jTText4);
        
        jLLeftRight = new JLabel ("<L : R>");
        JDs.add(jLLeftRight);
        jLLeftRight.setForeground(Color.black);
        
        jTText5 = new JTextField ("", 2);
        JDs.add(jTText5);
        
        JDs.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //Referencing: Tutorys MicroLearning (2014) How to use JLabel and JTextField in java? Available at: https://www.youtube.com/watch?v=_Se_q3L2JwQ (Accessed: 22 January 2017).
        //Referencing: JLabel color change (2017) Available at: http://stackoverflow.com/questions/15882886/jlabel-color-change (Accessed: 22 January 2017).
        
        //Buttons and labels for the pink panel
        jLOption = new JLabel ("Option:");
        JOpt.add(jLOption);
        jLOption.setForeground(Color.black);
        
        jTOplay = new JTextField("");
        JOpt.add(jTOplay);
        jTOplay.addActionListener(this);
        
        jLSquare = new JLabel ("Square:");
        JOpt.add(jLSquare);
        jLSquare.setForeground(Color.black);
        
        JT101 = new JTextField ("");
        JOpt.add(JT101);
        JT101.addActionListener(this);
        
        jLDirection = new JLabel ("Direction:");
        JOpt.add(jLDirection);
        jLDirection.setForeground(Color.black);
        
        JTDirect = new JTextField ("");
        JOpt.add(JTDirect);
        JTDirect.addActionListener(this);
        
        //Setting flow layout to dark pink panel
        JOpt.setLayout(new GridLayout(3,3));
        
        //Buttons added to the orange panel
        
        //First column
        JBTrans = new JButton (" ");
        JArrows.add(JBTrans);
        JBTrans.setEnabled(false);
        JBTrans.setBackground(Color.lightGray);
        
        JBUp = new JButton("^");
        JArrows.add(JBUp);
        JBUp.addActionListener(this);
        
        JBTrans = new JButton (" ");
        JArrows.add(JBTrans);
        JBTrans.setEnabled(false);
        JBTrans.setBackground(Color.lightGray);
        
        //Second column
        JBLeft = new JButton("<");
        JArrows.add(JBLeft);
        JBLeft.addActionListener(this);
        
        JBMiddle = new JButton("");
        JArrows.add(JBMiddle);
        JBMiddle.setIcon(iconBall);
        JBMiddle.setBorderPainted(false);
        JBMiddle.addActionListener(this);
        
        JBRight = new JButton(">");
        JArrows.add(JBRight);
        JBRight.addActionListener(this);
        
        //Third column
        JBTrans = new JButton (" ");
        JArrows.add(JBTrans);
        JBTrans.setEnabled(false);
        JBTrans.setBackground(Color.lightGray);
        
        JBDown = new JButton ("v");
        JArrows.add(JBDown);
        JBDown.addActionListener(this);
        
        JBTrans = new JButton (" ");
        JArrows.add(JBTrans);
        JBTrans.setEnabled(false);
        JBTrans.setBackground(Color.lightGray);
        
        //Setting flow layout to orange panel
        JArrows.setLayout(new GridLayout(3,3)); //Setting the layout 3x3 for the JArrows panel.
             
          
    }
    
    
    //Implementing a change listener
      public void stateChanged(ChangeEvent e)
      {
          slidertime = slider.getValue();
           
           if(timerball.isRunning()){
        	   timerball = new javax.swing.Timer(slidertime, this);
        	   timerball.start();
           }
//          jTGapField.setText(Integer.toString(slidertime));
//          timerDigital.setDelay(slidertime);
      }
      
      //This next section allows the ball to move on both the "act" button and when pressing the "run" button. 
      
    public void runforward(){
    	
    	//
    	if(PositionBall+1==110)
		{
			//do nothing
		}
		else if(PositionBall+1==99)
		{
			//do nothing
		}
		else if(nGame==1 && PositionBall+1 == 131) 
		{
			//do nothing
		}
			
		else if(nGame == 1 && PositionBall+1 == 142) 
		{
			//do nothing
		}
		
		else if(nGame == 2 && PositionBall+1 == 166)
		{
			//Do nothing
		}
		
		else if(nGame == 2 && PositionBall+1 == 171)
		{
			//Do nothing
		}
			
		else
		{
    		JBGridbutton[PositionBall+1].setIcon(iconBall); // +1 means that the ball is going to the right.
    		JBGridbutton[PositionBall].setIcon(null);
    		
    		PositionBall++;
    		
    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/east.jpg")); //Setting the compass image to the direction of east.
    		JTDirect.setText("East"); //Setting the text field to display east.
    		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
		}
		
		//Setting the right side of the perimeter of the pitch.
		
		if(PositionBall %16 ==15) 
		{
    			JBGridbutton[PositionBall].setIcon(null);
    			PositionBall=100;
    			JBGridbutton[PositionBall].setIcon(iconBall);
				baby1total++;
				jTText5.setText(Integer.toString(baby1total));
				//System.out.println("hello");
				if(ticks > 1)
				{
					timerball.stop();
					timerSecs.stop();
				}
				
		}
		
		//meetedge(); //Calling the edge function
    }
    
    public void runbackward(){
    	//
    	
    	if(PositionBall % 16 == 0)
		{
			JBGridbutton[PositionBall].setIcon(null);
			PositionBall=100;
			JBGridbutton[PositionBall].setIcon(iconBall);
			baby2total++;
			jTText4.setText(Integer.toString(baby2total));
			//System.out.println("test");
			//timerball.stop();
			//timerSecs.stop();
		}
		// Only if 4 Player is Selected
		if(count == 1)
		{
			if(nGame==2 && PositionBall-1 == 131)
			{
				//do nothing
			}
				else if (PositionBall-1 == 99) 
			{
			//do nothing
			}
				else if(nGame == 2 && PositionBall-1 == 142)
	    		{
	    			//do nothing
	    		}
				else if(nGame == 2 && PositionBall-1 == 166)
	    		{
	    			//do nothing
	    		}
	    		
	    		else if(nGame == 2 && PositionBall-1 == 171)
	    		{
	    			//do nothing
	    		}
				else
				{
					JBGridbutton[PositionBall-1].setIcon(iconBall); //-1 means setting the balls position to move left.
		    		JBGridbutton[PositionBall].setIcon(null);
		    		
		    		PositionBall--;
		    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/west.jpg")); //Setting the compass image to west when moving to the left.
		    		JTDirect.setText("West"); //Setting the text to west in the text field when moving left/west.
		    		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
		    		
				}
		}
		// End of 4 Player Selected
		// Start of JBLeft (2 Player)
		else if(PositionBall-1==110)
		{
			//do nothing
		}
		else if(PositionBall-1==99)
		{
			//do nothing
		}
		
		else if(nGame == 1 && PositionBall-1 == 142)
		{
			//do nothing
		}
		else if(nGame == 1 && PositionBall-1 == 131)
		{
			//do nothing
		}
		
		else{
		JBGridbutton[PositionBall-1].setIcon(iconBall);
		JBGridbutton[PositionBall].setIcon(null);
		
		PositionBall--;
		
		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/west.jpg"));
		JTDirect.setText("West");
		JT101.setText(""+PositionBall);
	}
    	
    	//
    	/*JBGridbutton[PositionBall-1].setIcon(iconBall);
		JBGridbutton[PositionBall].setIcon(null);
		
		PositionBall--;
		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/west.jpg"));
		JTDirect.setText("West");
		JT101.setText(""+PositionBall); */
		
		//meetedge(); //Calling the edge function
		
		//Setting the left side of the perimeter of the pitch.
		if(PositionBall % 16 == 0)
		{
			JBGridbutton[PositionBall].setIcon(null);
			PositionBall=100;
			JBGridbutton[PositionBall].setIcon(iconBall);
			baby2total++;
			jTText4.setText(Integer.toString(baby2total));
			//System.out.println("test");
			if(ticks > 1)
			{
				timerball.stop();
				timerSecs.stop();
			}
			//timerball.stop();
			//timerSecs.stop();
		}
    }
    
    public void runball()
    //This code makes the ball move when either the act button or the run button is pressed depending on the button pressed.
    {
       	
       	if(PositionBall+1==baby2pos){
       		
       		if(baby2pos==101){
       			runforward();
       		}
       		runbackward();
       		baby2pos=baby2pos-1;
       		
       	}
       	else{
       		
       		baby2pos=110;
       		runforward();
       	}
       	
       }
    
    //The next part of the code is for moving baby3 and baby4 up and down rather than in all different directions instead of left and right.
    
    public void baby34(){
    	
    	if(baby3edge==1){
    		
    		JBGridbutton[baby3pos-16].setIcon(iconBaby3);
    		JBGridbutton[baby3pos].setIcon(null);
    		baby3pos = baby3pos - 16;
    		
    	}
    	
    	if(baby3edge==2){
    		JBGridbutton[baby3pos+16].setIcon(iconBaby3);
    		JBGridbutton[baby3pos].setIcon(null);
    		baby3pos = baby3pos + 16;
	
    	}
    	
    	if(baby3pos==6){
    		baby3edge=2;
    	}
    	

    	if(baby3pos==198){
    		baby3edge=1;
    	}
    	

    	if(baby4edge==1){
    		
    		JBGridbutton[baby4pos-16].setIcon(iconBaby4);
    		JBGridbutton[baby4pos].setIcon(null);
    		baby4pos = baby4pos - 16;
    		
    	}
    	
    	if(baby4edge==2){
    		JBGridbutton[baby4pos+16].setIcon(iconBaby4);
    		JBGridbutton[baby4pos].setIcon(null);
    		baby4pos = baby4pos + 16;
	
    	}
    	
    	if(baby4pos==11){
    		baby4edge=2;
    	}
    	

    	if(baby4pos==203){
    		baby4edge=1;
    	}
    	
    }
    //This next part of the code is for implementing the scoring system so that when the ball detects the right or left edge then it sets the score.
    public void meetedge() {
    	for(int rightedge=15; rightedge<208; rightedge=rightedge+16) {
    		if(PositionBall==rightedge) {
    			JBGridbutton[PositionBall].setIcon(null);
    			PositionBall=100;
    			JBGridbutton[PositionBall].setIcon(iconBall);
				baby1total++;
				jTText5.setText(Integer.toString(baby1total));
    		}
    	}
    	for(int leftedge=0; leftedge<192; leftedge=leftedge+16) {
    		if(PositionBall==leftedge) {
    			JBGridbutton[PositionBall].setIcon(null);
    			PositionBall=100;
    			JBGridbutton[PositionBall].setIcon(iconBall);
    			baby2total++;
    			jTText4.setText(Integer.toString(baby2total));
    		}
    	}
    }
    
    int count =0;
    public void actionPerformed(ActionEvent event)
    {
    	//This code is for implementing the brick wall in the middle of the games screen.
    	for (int count =0; count<208; count++){
    	int wall = 0;
    	wall = count%16;
    	
    
    		if (wall == 8 || wall == 9 )
    		
    		{
    			JBGridbutton[count].setIcon(iconWall);
    		}
    	}
    	//The code below is for setting the 2 player, 4 player and multiplayer babies to the game.
    	Object source = event.getSource();
    	if (source == JB2player)
    	{
    		nGame = 0;
    		//System.out.println(""+nGame);
    		jTOplay.setText("2 Player");
    		JBGridbutton[142].setIcon(null);
    		JBGridbutton[131].setIcon(null);
    		JBGridbutton[baby3pos].setIcon(null);
    		JBGridbutton[baby4pos].setIcon(null);
    		//JBGridbutton[ballPos].setIcon(null);
    		//ballPos = 100;
    		//JBGridbutton[ballPos].setIcon(ball);
    	}
    	
    	else if (source == JB4player)
    	{
    		//nGame++;
    		nGame = 1;
    		jTOplay.setText("4 Player");
    		JBGridbutton[142].setIcon(iconBaby2);
    		JBGridbutton[131].setIcon(iconBaby1);
    		
    	}
    	
    	else if (source == JBMulti) 
    	{
    		nGame = 2;
    		jTOplay.setText("Multiplayer");
    		JBGridbutton[166].setIcon(iconBaby3);
    		JBGridbutton[171].setIcon(iconBaby4);
    		
    	}
    	//This code sets the timers.
    	
    	else if(source==timerball){
    		
    		 runball();
    	}
    	
    	else if(source==timerbaby){
    		
    		baby34();
    	}
    	//This code sets the run button.
    	else if (source ==JBrun)
    	{
    		if (run==1)
    			
    		{
    		 
    		 timerSecs = new javax.swing.Timer(1000, this);//Making the timer interval
    		 timerSecs.start(); //Starting the timer when clicking the "run" button.
    		 
    		 
    		 timerball = new javax.swing.Timer(1000, this);
    		 timerball.start(); //Starting the ball timer.
    		 JBrun.setText("Pause"); //Setting the text to pause when the "run" button is pressed.
      		 JBrun.setIcon(iconPause);
      		 //pause=1;
      		 run=2;
    		 
    		 timerbaby = new javax.swing.Timer(1000, this);
    		 if (nGame == 2)
    		 {
    			 timerbaby.start(); //Starting the baby timer.
    			 //System.out.println(""+nGame);
    		 }
    		 else
    		 {
    			 
    		 }
    		}
    		else {
          		 run=1;
          		 JBrun.setText("Run"); //Setting the text to pause when the "run" button is pressed.
          		 JBrun.setIcon(iconRun); //Setting the pause icon to the "run" button.
          		 timerSecs.stop();
          		 timerball.stop();
          		 timerbaby.stop();
          		 //run=1;
          		//}
    		/*else {
    			run = 1;
    			timerSecs.start();
    			timerball.start();
    			if (nGame == 1)
    			{
    				timerbaby.start();
    			}
    		}*/
    		}
    	} 
    	
    	//This code sets all the different pop-up boxes as well as the exit buttons.
    	
    	else if (source == about) //Getting a hold of the "about" JItem and then implementing the following code below (message dialog).
    	{
    		JOptionPane.showMessageDialog(null, "Designed & Created By Ashleigh Claire Foster.", "About CBabyBallBounce", JOptionPane.INFORMATION_MESSAGE, iconFoot); //This code is for implementing an "about" dialog box as a way of showing who designed and programmed the game as well as including a Greenfoot icon and title at the top.
    	}
    	else if (source == helptopic) //Getting a hold of the "help topic" JItem and then implementing the following code below in a message dialog box.
    	{
    		JOptionPane.showMessageDialog(null, "For more help and information please visit www.northampton.ac.uk", "Help CBabyBallBounce", JOptionPane.INFORMATION_MESSAGE); //This code is for implementing the "help topic" dialog box to show the user where to get more help and guidance from.
    	}
    	else if (source == exit) //This exit basically means the exit item in the JMenuBar.
    	{
    		System.exit(0); //This code exits the application as soon as the exit JItem is selected.
    	}
    	else if (source == JBExit) //This exit basically means the exit button the right hand side of the game.
    	{
    		System.exit(0); //This code exits the application as soon as the exit JButton is selected.
    	}
    	else if (source == JBRight) //When pressing the right button.
    	{
    		runforward();
    		    		
    		/*
    		//if the next position of the ball is equal to the baby right position don't move - else move as normal
    		if(PositionBall+1==110)
    		{
    			//do nothing
    		}
    		else if(PositionBall+1==99)
    		{
    			//do nothing
    		}
    		else if(nGame==2 && PositionBall+1 == 131) 
    		{
    			//do nothing
    		}
    			
    		else if(nGame == 2 && PositionBall+1 == 142) 
    		{
    			//do nothing
    		}
    		
    		else if(nGame == 2 && PositionBall+1 == 166)
    		{
    			//Do nothing
    		}
    		
    		else if(nGame == 2 && PositionBall+1 == 171)
    		{
    			//Do nothing
    		}
    			
    		else
    		{
        		JBGridbutton[PositionBall+1].setIcon(iconBall); // +1 means that the ball is going to the right.
        		JBGridbutton[PositionBall].setIcon(null);
        		
        		PositionBall++;
        		
        		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/east.jpg")); //Setting the compass image to the direction of east.
        		JTDirect.setText("East"); //Setting the text field to display east.
        		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
    		}

    		*/
    	}
    	else if (source == JBLeft) //When pressing the left button.
    	{
    		runbackward();
    		/*
    		if(PositionBall % 16 == 0)
    		{
    			JBGridbutton[PositionBall].setIcon(null);
    			PositionBall=100;
    			JBGridbutton[PositionBall].setIcon(iconBall);
    			baby2total++;
    			jTText4.setText(Integer.toString(baby2total));
    			System.out.println("test");
    			//timerball.stop();
    			//timerSecs.stop();
    		}
    		// Only if 4 Player is Selected
    		if(count == 1)
    		{
    			if(nGame==2 && PositionBall+1 == 131)
				{
					//do nothing
				}
    				else if (PositionBall-1 == 99) 
				{
				//do nothing
				}
    				else if(nGame == 2 && PositionBall-1 == 142)
    	    		{
    	    			//do nothing
    	    		}
    				else if(nGame == 2 && PositionBall-1 == 166)
    	    		{
    	    			//Do nothing
    	    		}
    	    		
    	    		else if(nGame == 2 && PositionBall-1 == 171)
    	    		{
    	    			//Do nothing
    	    		}
    				else
    				{
    					JBGridbutton[PositionBall-1].setIcon(iconBall); //-1 means setting the balls position to move left.
    		    		JBGridbutton[PositionBall].setIcon(null);
    		    		
    		    		PositionBall--;
    		    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/west.jpg")); //Setting the compass image to west when moving to the left.
    		    		JTDirect.setText("West"); //Setting the text to west in the text field when moving left/west.
    		    		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
    		    		
    				}
    		}
    		// End of 4 Player Selected
    		// Start of JBLeft (2 Player)
    		else if(PositionBall-1==110)
    		{
    			//do nothing
    		}
    		else if(PositionBall-1==99)
    		{
    			//do nothing
    		}
    		
    		else if(nGame == 2 && PositionBall-1 == 142)
    		{
    			//do nothing
    		}
    		else if(nGame == 2 && PositionBall-1 == 131)
    		{
    			//Do nothing
    		}
    		
    		else{
    		JBGridbutton[PositionBall-1].setIcon(iconBall);
    		JBGridbutton[PositionBall].setIcon(null);
    		
    		PositionBall--;
    		
    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/west.jpg"));
    		JTDirect.setText("West");
    		JT101.setText(""+PositionBall);
    	}*/
    		}
    	else if (source == JBUp) //When pressing the up button.
    	{
    		if(PositionBall-16==99) {
    			//Do nothing
    		}
    		else if(PositionBall-16==110) {
    			//Do nothing
    		}
    		else if(nGame == 1 && PositionBall-16 == 131) {
    			//Do nothing
    		}
    		else if(nGame == 1 && PositionBall-16 == 142) {
    			//Do nothing
    		}
    		else if(nGame == 2 && PositionBall-16 == 166)
    		{
    			//Do nothing
    		}
    		
    		else if(nGame == 2 && PositionBall-16 == 171)
    		{
    			//Do nothing
    		}
    		
    		
    		else{
    		JBGridbutton[PositionBall-16].setIcon(iconBall); // -16 basically means to move the ball upwards.
    		JBGridbutton[PositionBall].setIcon(null);
    		
    		PositionBall = PositionBall - 16;
    		
    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/north.jpg")); //Setting the compass image to North when the ball goes up.
    		JTDirect.setText("North"); //Setting the text field to North when the ball moves upwards.
    		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
    		}
    	}
    	else if (source == JBDown) //When pressing the down button.
    	{
    		if(PositionBall+16==99) {
    			//Do nothing
    		}
    		else if(PositionBall+16==110) {
    			//Do nothing
    		}
    		
    		else if (nGame == 1 && PositionBall+16 == 131) {
    			//Do nothing
    		}
    		
    		else if(nGame == 1 && PositionBall+16 == 142) {
    			//Do nothing
    		}
    		else if(nGame == 2 && PositionBall+16 == 166)
    		{
    			//Do nothing
    		}
    		
    		else if(nGame == 2 && PositionBall+16 == 171)
    		{
    			//Do nothing
    		}
    		
    		else{
    		JBGridbutton[PositionBall+16].setIcon(iconBall); // +16 means to move the ball downwards.
    		JBGridbutton[PositionBall].setIcon(null);
    		
    		PositionBall = PositionBall + 16;
    		
    		JBCompass.setIcon(new ImageIcon ("src/csy1020Ass2/south.jpg")); //Setting the compass to South when the ball goes down
    		JTDirect.setText("South"); //Setting the text field to South when the ball goes downwards.
    		JT101.setText(""+PositionBall); //Setting the text field to increment every time the ball moves a different position.
    		}
    	}
    	
    	else if (source ==JBreset)
    	{
    		jTText1.setText("00"); //Setting the text in the timer when clicking on the reset button.
    		jTText2.setText("00"); //Setting the text in the timer when clicking on the reset button.
    		jTText3.setText("00"); //Setting the text in the timer when clicking on the reset button.
    		timerSecs.stop(); //Stopping the timer when the reset button is pressed.
    		timerball.stop(); //Stopping the timer on the ball when the reset button is pressed.
    		timerbaby.stop(); //Stopping the timer on the babies when the reset button is pressed.
    		ticks = 0; //Implementing the relevant ticks.
    		JBrun.setText("Run"); //Setting the text back to run when pressing the "reset" button.
    		JBrun.setIcon(iconRun); //Resetting the icon so that it goes back to the green play icon.
    		//Resetting the different elements to the GUI.
    		JBGridbutton[PositionBall].setIcon(null);
    		PositionBall = 100;
    		JBGridbutton[PositionBall].setIcon(iconBall); 
    		//Resetting the babies
    		JBGridbutton[142].setIcon(null);
    		JBGridbutton[131].setIcon(null);
    		JBGridbutton[baby3pos].setIcon(null);
    		JBGridbutton[baby4pos].setIcon(null);
    		jTOplay.setText("2 Player");
    		run=1;
    		baby1total = 0;
    		baby2total = 0;
    		jTText4.setText("");
    		jTText5.setText("");
    		nGame = 0;
    		slider.setValue(1000); //This code resets the slider in the application.  JSlider, How. "How To Change Default/Start Value Of Jslider". Stackoverflow.com. N.p., 2017. Web. 11 Apr. 2017.
    	}
    	
    	//When the "act" button is pressed the code below calls the "runball" method and implements the relevant steps.
    	else if (source ==JBact){
    		runball();
    		
    	}
    	
    	else if(source==timerSecs)
    		//Implementing the ticks in the timer.
        	{
        	
    		jTText1.setText(Integer.toString(ticks / 3600)); // (60*60) 1 hour = 3600 seconds - UnitJuggler (2008) Convert hours to seconds - time converter. Available at: https://www.unitjuggler.com/convert-time-from-h-to-s.html (Accessed: 3 February 2017).
    		jTText2.setText(Integer.toString(ticks / 60));
    		jTText3.setText(Integer.toString(ticks % 60));
            ticks = ticks +1;
          //  runball(); //The runball method uses the initial "act" codes method and then implements a timer to it by +1 each time the ball moves.
         //   baby34(); //The baby34 method is called and then it is implemented by a timer which increments the babies movement of upwards and downwards by +1 of the timers ticks.
        	}
    	}
 
}