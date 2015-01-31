package com.example.microsofthack;

import java.util.Random;

import android.util.Log;

public class Sheep extends Player{

	public Sheep(int x, int y){
		super(x,y);
	}
	public void start(int bounds, Boolean[][] grid){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
			int direction = rand.nextInt(4);
			if (direction == 0){
				swipe_Up(bounds, grid);
				Log.d("sheep", getX() + "-" + getY());
			}else if (direction == 1){
				swipe_Right(bounds, grid);
				Log.d("sheep", getX() + "-" + getY());
			}else if (direction == 2){
				swipe_Down(grid);
				Log.d("sheep", getX() + "-" + getY());
			}else{
				swipe_Left(grid);
				Log.d("sheep", getX() + "-" + getY());
			}

	}
}
