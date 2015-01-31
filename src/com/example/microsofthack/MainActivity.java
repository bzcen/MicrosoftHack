package com.example.microsofthack;

import android.support.v7.app.ActionBarActivity;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.os.Vibrator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
	
	private Player p1;
	private Grid grid;
	private int bounds;
	Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        
        bounds = 5;
        grid = new Grid(bounds);
        p1 = new Player(0,0);
        grid.setWalls(2);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void Next_Screen(View view){
    	setContentView(R.layout.activity_main);
    }
    
    public void Button_UP(View view){
    	Log.d("tag", "Up Button was PRESSED");
    	Boolean check = p1.swipe_Up(bounds, grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    	
    }
    public void Button_DOWN(View view){
    	Log.d("tag", "Down Button was PRESSED");
    	Boolean check = p1.swipe_Down(grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    }
    public void Button_RIGHT(View view){
    	Log.d("tag", "Right Button was PRESSED");
    	Boolean check = p1.swipe_Right(bounds, grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    }
    public void Button_LEFT(View view){
    	Log.d("tag", "Left Button was PRESSED");
    	Boolean check = p1.swipe_Left(grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    }
    
    /*@Override
    public boolean onTouchEvent(MotionEvent event){
    	int action = MotionEventCompat.getActionMasked(event);
    	
    	switch(action) {
        case (MotionEvent.ACTION_DOWN) :
            Log.d("tag","Action was DOWN");
            return true;
        case (MotionEvent.ACTION_MOVE) :
            Log.d("tag","Action was MOVE");
            return true;
        case (MotionEvent.ACTION_UP) :
            Log.d("tag","Action was UP");
            return true;
        case (MotionEvent.ACTION_CANCEL) :
            Log.d("tag","Action was CANCEL");
            return true;
        case (MotionEvent.ACTION_OUTSIDE) :
            Log.d("tag","Movement occurred outside bounds " +
                    "of current screen element");
            return true;      
        default : 
            return super.onTouchEvent(event);}
    }      
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
