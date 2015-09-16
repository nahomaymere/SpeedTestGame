package speedTest;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SpeedUI {
	int mButtonPressed = 0;
	final Timer mTm;
	final Timer mTm2;
	JFrame jf;
	JButton mB1;
	JButton mB2;
	JButton mB3;
	JButton mB4;
	JButton start;
	JButton highScore;
	JButton quit;
	JLabel mJl;
	JLabel mJ2;
	JLabel HighScoreLabel;
	JLabel GO;
	JTextField bestPlayer;
	int[] rightpath = new int[100];
	int[] upath = new int[100];
	int delay = 2000;
	int randomBumber;
	int random = 10;
	
	
	int answer;
	
	int i = 0;
	int j=0;
	
	
	int index;
	Font fntHight=new Font("Arial",Font.PLAIN,20);

	int count = 1;

	int score = 0;
	int Highscore=0;
	String str;
	String playersName;
	String myHighscoreName="User :0";
	boolean alreadyIncreased=false;
	FileReader myfile=null;
	BufferedReader reader=null;
	FileWriter myWriter=null;
	BufferedWriter writer=null;
	

	
	
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int SCALE = 2;


	class MySecondButtonHandler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {

			
			// Get action command return string from button
			// Shown to user. --> Action string.
			if (e.getActionCommand() == "1") {
				answer = 1;
				index++;
				
			
				System.out.println("Button1");
				alreadyIncreased=false;
			}
			else if (e.getActionCommand() == "2") {
				answer = 2;
				index++;
				System.out.println("Button2");
				alreadyIncreased=false;
			
			} 
			else if (e.getActionCommand() == "3") {
				answer = 3;
				index++;
				System.out.println("Button3 pressed");
				alreadyIncreased=false;
			
			} else if (e.getActionCommand() == "4") {
				answer = 4;
				index++;
				System.out.println("Button4 pressed");
				alreadyIncreased=false;

		
			} else if (e.getActionCommand() == "NEW GAME") {
				
				System.out.println("START pressed");
				score = 0;
				BacktoGameMenu();
				mTm.restart();

			} else if (e.getActionCommand() == "HighScore")
			{
				System.out.println("pause pressed");
				mJl.setVisible(true);
				mJ2.setVisible(false);
				
				
				mB1.setVisible(false);
				mB2.setVisible(false);
				mB3.setVisible(false);
				mB4.setVisible(false);
				GO.setVisible(false);
				mTm.stop();
				mTm2.stop();
				HighScoreLabel.setFont(fntHight);
				HighScoreLabel.setText(myHighscoreName);
				HighScoreLabel.setVisible(true);
				
				
				
				
			} else if (e.getActionCommand() == "QUIT") {
				System.out.println("quit pressed");
				
				System.exit(1);
			}
			
			
			mButtonPressed++;
			System.out.println("Inside MySecondButtonHandler " + "actionPerformed" + mButtonPressed);
		}
	}

	SpeedUI() {
		System.out.println("Inside SpeedUI Constructor");

		jf = new JFrame("SpeedTest");

		jf.setSize(WIDTH, HEIGHT);

		// call Gui class to draw the menu and add it on the frame
		// GUI menu= new GUI();
		// jf.getContentPane().add( menu );
		jf.setLayout(null);

		// 2. Optional: What happens when the frame closes?
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//initialize high score
		if(myHighscoreName.equals(""))
		{
			myHighscoreName=this.getHighScore();
		}
		

		

		// 3. Create components and put them in the frame.
		// ...create emptyLabel...
		Font fnt0 = new Font("Arial", Font.BOLD, 20);
		Font fnt1 = new Font("Arial", Font.BOLD, 16);
		Font fnt2 = new Font("Arial", Font.BOLD, 14);

		mJl = new JLabel("SPEED TEST GAME");
		mJl.setForeground(Color.black);
		mJl.setFont(fnt0);
		mJl.setSize(200, 50);
		mJl.setLocation(250, 20);
		jf.getContentPane().add(mJl);
		// Game Over Label
		GO = new JLabel("GAME OVER  YOUR SCORE IS " + score);
		GO.setForeground(Color.red);
		GO.setFont(fnt0);
		GO.setSize(400, 100);
		GO.setLocation(150, 100);
		GO.setVisible(false);
		jf.getContentPane().add(GO);
		
		//High score label
		HighScoreLabel= new JLabel("New Highscore ");
		HighScoreLabel.setForeground(Color.black);
		HighScoreLabel.setFont(fnt2);
		HighScoreLabel.setSize(300,50);
		HighScoreLabel.setLocation(250,180);
		HighScoreLabel.setVisible(false);
		jf.getContentPane().add(HighScoreLabel);
		//Text box
		bestPlayer=new JTextField("Enter your Name");
		bestPlayer.setForeground(Color.black);
		bestPlayer.setFont(fnt2);
		bestPlayer.setSize(200, 50);
		bestPlayer.setLocation(220, 235);
		bestPlayer.setEnabled(false);
		bestPlayer.setVisible(false);
		jf.getContentPane().add(bestPlayer);
		
		// score board
		mJ2 = new JLabel("SCORE  " + score);

		mJ2.setFont(fnt2);
		mJ2.setSize(400, 100);
		mJ2.setLocation(100, 50);
		mJ2.setForeground(Color.black);
		mJ2.repaint();

		jf.getContentPane().add(mJ2);

		// This handles all events from all buttons
		MySecondButtonHandler msc = new MySecondButtonHandler();

		// Button1
		mB1 = new JButton("1");
		mB1.setSize(100, 100);
		mB1.setLocation(50, 150);
		mB1.addActionListener(msc);
		jf.getContentPane().add(mB1);

		// Button2
		mB2 = new JButton("2");
		mB2.setSize(100, 100);
		mB2.setLocation(180, 150);
		mB2.addActionListener(msc);
		jf.getContentPane().add(mB2);

		// Button3
		mB3 = new JButton("3");
		mB3.setSize(100, 100);
		mB3.setLocation(310, 150);
		mB3.addActionListener(msc);
		jf.getContentPane().add(mB3);
		// Button 4
		mB4 = new JButton("4");
		mB4.setSize(100, 100);
		mB4.setLocation(440, 150);
		mB4.addActionListener(msc);
		jf.getContentPane().add(mB4);

		// start Button

		start = new JButton("NEW GAME");
		start.setSize(200, 50);
		start.setLocation(WIDTH / 3, HEIGHT - 200);
		start.addActionListener(msc);
		start.setFont(fnt1);
		jf.getContentPane().add(start);
		// Stop Button
		highScore = new JButton("HighScore");
		highScore.setSize(200, 50);
		highScore.setLocation(WIDTH / 3, HEIGHT - 140);
		highScore.addActionListener(msc);
		highScore.setFont(fnt1);
		jf.getContentPane().add(highScore);

		// Quit button
		quit = new JButton("QUIT");
		quit.setSize(200, 50);
		quit.setLocation(WIDTH / 3, HEIGHT - 80);
		quit.addActionListener(msc);
		quit.setFont(fnt1);
		jf.getContentPane().add(quit);

		// Set defult BG color for buttons
		mB1.setBackground(Color.gray);
		mB1.setOpaque(true);
		mB1.setBorderPainted(false);

		mB2.setBackground(Color.gray);
		mB2.setOpaque(true);
		mB2.setBorderPainted(false);

		mB3.setBackground(Color.gray);
		mB3.setOpaque(true);
		mB3.setBorderPainted(false);

		mB4.setBackground(Color.gray);
		mB4.setOpaque(true);
		mB4.setBorderPainted(false);

		start.setBackground(Color.GREEN);
		start.setOpaque(true);
		start.setBorderPainted(false);

		highScore.setBackground(Color.yellow);
		highScore.setOpaque(true);
		highScore.setBorderPainted(false);

		quit.setBackground(Color.red);
		quit.setOpaque(true);
		quit.setBorderPainted(false);
		
		
		//Creating an Image Icon on the JFrame
		ImageIcon icon=new ImageIcon("res/pirate1.png","pirate");
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setSize(600, 600);
		jf.getContentPane().add(iconLabel);
		
		//timer for game over state		
		mTm2=new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				mJl.setVisible(false);
				mJ2.setVisible(false);
			
				GO.setVisible(true);
				
				
				mB1.setVisible(false);
				mB2.setVisible(false);
				mB3.setVisible(false);
				mB4.setVisible(false);
				
				checkHighscore();
			
			}
		});
		
		//timer for Game play state
		mTm = new Timer(delay, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("Timer action happen " + delay);
				count++;
				// When timer expires, randomly define, which
				// of the buttons lights up. Save that value
				// into array to check if user press correct
				// button

				mTm.getActionListeners();
				if(j>=99)
					 j=0;
				if(index>=99)
					 index=0;
			
				
				if(j>0)
				{
					upath[index]=answer;
					rightpath[j]=randomBumber+1;
					if(upath[index]==rightpath[index])
					{
						System.out.println("They are Equal");
						if(upath[index]!=0 && !alreadyIncreased)
						{
							
							score+=10;
							alreadyIncreased=true;
						}
					}
					else if(upath[index]!=rightpath[index]){
						System.out.println("They are Not equal");
						System.out.println("right path: "+rightpath[index]+" user path: "+ upath[index]);
						System.out.println("index is "+index);
						System.out.println("J is"+j);
						
						System.out.println("J is "+j);
						
						
						GameOver();
						
						
					}
				
						
				}
				 System.out.println("right path: "+rightpath[index]+" user path: "+ upath[index]);
				
				Random rnd = new Random();
				
				//Make so that the random number does not repeat itself 
				while (randomBumber == random) {
					randomBumber = rnd.nextInt(4);

				}
				random = randomBumber;
				j++;
				i++;
				

				mJ2.setText("SCORE  " + score);
				GO.setText("GAME OVER  YOUR SCORE IS " + score);
				
				 //Decrease timer delay when user have played 9 buttons successfully
				if (i == 9 && delay != 0) {

					delay -= 50;
					i= 0;
				}
				
				GO.setVisible(false);
				HighScoreLabel.setVisible(false);
				bestPlayer.setVisible(false);
				bestPlayer.setEnabled(false);
				
				mJl.setVisible(true);
				mJ2.setVisible(true);
				// Set all buttons to default values
				mB1.setBackground(Color.gray);
				mB1.setOpaque(true);
				mB1.setBorderPainted(false);
				mB1.setVisible(true);

				mB2.setBackground(Color.gray);
				mB2.setOpaque(true);
				mB2.setBorderPainted(false);
				mB2.setVisible(true);

				mB3.setBackground(Color.gray);
				mB3.setOpaque(true);
				mB3.setBorderPainted(false);
				mB3.setVisible(true);

				mB4.setBackground(Color.gray);
				mB4.setOpaque(true);
				mB4.setBorderPainted(false);
				mB4.setVisible(true);

				// Light up selected button

				if (randomBumber == 0) {
					mB1.setBackground(Color.red);
					mB1.setOpaque(true);
					mB1.setBorderPainted(false);
				}
				if (randomBumber == 1) {
					mB2.setBackground(Color.yellow);
					mB2.setOpaque(true);
					mB2.setBorderPainted(false);
				}
				if (randomBumber == 2) {
					mB3.setBackground(Color.blue);
					mB3.setOpaque(true);
					mB3.setBorderPainted(false);
				}
				if (randomBumber == 3) {
					mB4.setBackground(Color.green);
					mB4.setOpaque(true);
					mB4.setBorderPainted(false);
				}



				

			}

		});

		// 5. Show it.
		jf.getContentPane().setBackground(Color.black);
		
		jf.setVisible(true);

	}

	public void GameOver() {
		mTm.stop();
		j=0;
		i=0;
		index=0;
		answer=0;
		mTm2.restart();
	}
	public void BacktoGameMenu()
	{
		score = 0;
		delay = 2000;
		index=0;
		answer=0;
		j=0;
		score=0;
		i=0;
		mTm2.stop();
	}
	public void checkHighscore()
	{
		if(score>Integer.parseInt(myHighscoreName.split(":")[1]))
		{
			bestPlayer.setVisible(true);
			bestPlayer.setEnabled(true);
			HighScoreLabel.setText("New Highscore");
			HighScoreLabel.setVisible(true);
			Highscore=score;
			
			HighScoreLabel.setFont(fntHight);
			bestPlayer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					playersName=bestPlayer.getText();
					bestPlayer.setVisible(false);
					bestPlayer.setEnabled(false);
					
					HighScoreLabel.setVisible(false);
					
					myHighscoreName= bestPlayer.getText() +" :"+score;
					
				}
			});
			
			File myScorefile=new File("res/HigScoreFile.txt");
			 try 
			{
				myWriter=new FileWriter(myScorefile);
				writer=new BufferedWriter(myWriter);
				writer.write(myHighscoreName);
			}
			 catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 finally
			 {
				 if(writer!=null) 
					try 
				 	{
						writer.close();
				 	} 
				 	catch (IOException e1)
				 	{
				 		// TODO Auto-generated catch block
				 		e1.printStackTrace();
				 	}
			 }
			 
			
		}
	}
	public String getHighScore(){
		
		try 
		{
			myfile=new FileReader("res/HigScoreFile.txt");
			reader=new BufferedReader(myfile);
			try {
				return reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
				return "User:0";
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "User:0";
		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
