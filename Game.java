package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.*;


public class Game extends JPanel implements KeyListener,ActionListener  {
private boolean play=false;
private int score=0;
private int totalBricks=21;
private Timer timer;
private int delay=8;
private int playerX=310;
private int ballpositionX=120;
private int ballpositionY=350;
private int ballXdir=-1;
private int ballYdir=-2;
 Map a=new Map(3,7);
 
public Game() {
	
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	timer=new Timer(delay,this);
	timer.start();
}
public void paint(Graphics g) {
	//background
	g.setColor(Color.WHITE);
	g.fillRect(1, 1, 692,592);
	//drawing map 
	a.draw((Graphics2D)g);
	//borders
	g.setColor(Color.RED);
	g.fillRect(0,0,3,592);
	g.fillRect(0,0,692,3);
	g.fillRect(691,0,3,592);
	//scores
	g.setColor(Color.BLACK);
	g.setFont(new Font("serif",Font.BOLD,25));
	g.drawString(""+score, 599, 30);
	//the paddle
	g.setColor(Color.BLUE);
	g.fillRect(playerX, 550,100,8);
	//the ball
	g.setColor(Color.RED);
	g.fillOval(ballpositionX,ballpositionY,20,20);
	
	
	if(ballpositionY >570) {
		play=false;
		ballXdir=0;
		ballYdir=0;
		g.setColor(Color.RED);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString("Game over ,Scores: ", 190, 300);
		
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString("pres enter to restart", 230, 350);
	}
	 if(totalBricks==0) {
		 play=false;
			ballXdir=0;
			ballYdir=0;
		 g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Congratulation !!!", 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("pres enter to restart", 230, 350);
     	
     }
	g.dispose();
	
}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play) {
			if(new Rectangle(ballpositionX,ballpositionY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir=-ballYdir;
			}
			A:for(int i=0;i<a.map.length;i++) {
				for(int j=0;j<a.map[0].length;j++) {
					if(a.map[i][j] > 0) {
						int brickX=j*a.brickwidth+80;
						int brickY=i*a.brickHeight+50;
						int brickwidth=a.brickwidth;
						int brickHeight=a.brickHeight;
						Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickHeight);
						Rectangle balrec=new Rectangle(ballpositionX,ballpositionY,20,20);
						Rectangle brickrect=rect;
						if(balrec.intersects(brickrect)) {
							a.setValue(0,i,j);
							totalBricks--;
							score+=5;
							
							if(ballpositionX + 19<=brickrect.x||ballpositionX+1>=brickrect.x+brickrect.width) {
								ballXdir=-ballXdir;
							}else {
							ballYdir=-ballYdir;	 
							}
							break A;
						}
						
						
						
					}
				}
			}
			ballpositionX += ballXdir;
			ballpositionY+= ballYdir;
           
            
			if(ballpositionX<0) {
				ballXdir=-ballXdir;
			}
			if(ballpositionY<0) {
				ballYdir=-ballYdir;
			}
			if(ballpositionX>670) {
				ballXdir=-ballXdir;
			}
		}
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerX >=600) {
				playerX=600;
			}else {
				moveRight();
			}
			
			
			
		}
         if(e.getKeyCode()==KeyEvent.VK_LEFT) {
        	 if(playerX <10) {
 				playerX=10;
 			}else {
 				moveLeft();
 			}
 			
			
		}
         if(e.getKeyCode()==KeyEvent.VK_ENTER) {
        	 if(!play) {
        		 play=true;
        		 ballpositionX=120;
        		 ballpositionY=350;
        		 ballXdir=-1;
        		 ballYdir=-2;
        		 playerX=310;
        		 score=0;
        		 totalBricks=21;
        		 a=new Map(3,7);
        		 repaint();
        	 }
         }
		
	}
	public void moveRight() {
		play=true;
		playerX+=20;
	}
	public void moveLeft() {
		play=true;
		playerX-=20;
	}
	
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
