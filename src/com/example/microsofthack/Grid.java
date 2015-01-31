package com.example.microsofthack;

import java.util.Random;

public class Grid {
	
	private int max_Bounds;
	private Boolean [][] grid;
	
	public Grid(int b){
		max_Bounds = b;
		grid = new Boolean[b][b];
		for (int i = 0; i < b; i++){
			for (int j = 0; j < b; j++){
				grid[i][j] = false;
			}
		}
		
	}
	public int getBounds(){
		return max_Bounds;
	}
	public Boolean[][] getGrid(){
		return grid;
	}
	public void setWalls(int n){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < n; i++){
			
			int x = rand.nextInt(max_Bounds);
			int y = rand.nextInt(max_Bounds);
			grid[x][y] = true;
			
		}
	}
}
