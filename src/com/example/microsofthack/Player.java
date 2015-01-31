package com.example.microsofthack;


public class Player {
	private int coord_X;
	private int coord_Y;
	
	public Player(int x, int y){
		coord_X = x;
		coord_Y = y;
	}
	public int getX(){
		return coord_X;
	}
	public int getY(){
		return coord_Y;
	}
	public Boolean swipe_Up(int bounds, Boolean[][] g){
		if (coord_Y + 1 < bounds){
			if (!g[coord_X][coord_Y+1]){
				coord_Y++;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public Boolean swipe_Right(int bounds, Boolean[][] g){
		if (coord_X + 1 < bounds){
			if (!g[coord_X + 1][coord_Y]){
				coord_X++;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public Boolean swipe_Down(Boolean[][] g){
		if (coord_Y - 1 >= 0){
			if (!g[coord_X][coord_Y - 1]){
				coord_Y--;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public Boolean swipe_Left(Boolean[][] g){
		if (coord_X - 1 >= 0){
			if (!g[coord_X -1][coord_Y]){
				coord_X--;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
