package com.example.microsofthack;

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
}
