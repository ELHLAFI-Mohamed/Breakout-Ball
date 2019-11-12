package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Map {
public int[][] map;
public int brickwidth;
public int brickHeight;
public Map(int row,int col) {
	map=new int[row][col];
	for(int i=0;i<map.length;i++) {
		for(int j=0;j<map[0].length;j++) {
			map[i][j]=1;
		}
	}
	brickwidth=540/col;
	brickHeight=150/row;
}
public void draw(Graphics2D g) {
	for(int i=0;i<map.length;i++) {
		for(int j=0;j<map[0].length;j++) {
			if(map[i][j]>0) {
				g.setColor(Color.BLUE);
				g.fillRect(j*brickwidth+80,i*brickHeight+50,brickwidth,brickHeight);
			   g.setStroke(new BasicStroke(3));
			    g.setColor(Color.WHITE);
			    g.drawRect(j*brickwidth+80,i*brickHeight+50,brickwidth,brickHeight);
		
			
			}
		}
	}
	
	
}
public void setValue(int value,int row,int col) {
	map[row][col]=value;
}
}
