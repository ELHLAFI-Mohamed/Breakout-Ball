package game;

import javax.swing.JFrame;

import game.Game.Med;

public class Main  {
	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Game game1=new Game();

		obj.setBounds(10,10,700,630);
		obj.setTitle("Breakout ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game1);
	}

}
