package com.example.microsofthack;

import java.util.ArrayList;
import java.util.Set;

import android.support.v7.app.ActionBarActivity;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.Editable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Vibrator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	
	//private final IntentFilter intentFilter = new IntentFilter();
	//Channel mChannel;
	//WifiP2pManager mManager;
	
	private Player p1;
	private Grid grid;
	private int bounds;
	private int walls;
	private Sheep s;
	Vibrator v;
	EditText bounds_input;
	EditText walls_input;
	
	private String[] hexcodes = {"#88FF0000","#88FF1100","#88FF2300","#88FF3400",
			"#88FF4600","#88FF5700","#88FF6900","#88FF7B00","#88FF8C00","#88FF9E00",
			"#88FFAF00","#88FFC100","#88FFD300","#88FFE400","#88FFF600","#88F7FF00",
			"#88E5FF00","#88D4FF00","#88C2FF00","#88B0FF00","#889FFF00","#888DFF00",
			"#887CFF00","#886AFF00","#8858FF00","#8847FF00","#8835FF00","#8824FF00",
			"#8812FF00","#8800FF00"};
	
	private BluetoothAdapter BA;
	private Set<BluetoothDevice>pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        
        bounds_input = (EditText)findViewById(R.id.bounds_field);
        walls_input = (EditText)findViewById(R.id.num_Walls);
        
        BA = BluetoothAdapter.getDefaultAdapter();
        
/*        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null); */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Bluetooth_ON(View view){
    	 if (!BA.isEnabled()) {
             Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
             startActivityForResult(turnOn, 0);
             Toast.makeText(getApplicationContext(),"Turned on" 
             ,Toast.LENGTH_LONG).show();
          }
          else{
             Toast.makeText(getApplicationContext(),"Already on",
             Toast.LENGTH_LONG).show();
             }
    }
    public void Bluetooth_OFF(View view){
    	BA.disable();
        Toast.makeText(getApplicationContext(),"Turned off" ,
        Toast.LENGTH_LONG).show();
    }
    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.
        ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);

     }
    public void list(View view){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
           list.add(bt.getName());
        
        for (int i = 0; i < list.size(); i++){
        	Log.d("tag", list.get(i).toString());
        }
        // fix this

     }
    public void Next_Screen(View view){
    	bounds_input = (EditText)findViewById(R.id.bounds_field);
        walls_input = (EditText)findViewById(R.id.num_Walls);
         
    	setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#8800FF00"));
    	
        bounds = Integer.parseInt(bounds_input.getText().toString());
        walls = Integer.parseInt(walls_input.getText().toString());
        grid = new Grid(bounds);
        p1 = new Player(0,0);
        grid.setWalls(walls);
        s = new Sheep(bounds-1, bounds-1);
    }
    public void replay(View view){
    	setContentView(R.layout.starting_screen);
    	getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }
    
    public void Button_UP(View view){
    	Log.d("tag", "Up Button was PRESSED");
    	Boolean check = p1.swipe_Up(bounds, grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    	
    	double dist = p1.check_Dist(s.getX(), s.getY());

    	if (dist == 0){
    		setContentView(R.layout.end_screen);
    	}else{
    		s.start(bounds, grid.getGrid());
    		Log.d("tag", "" + dist);
    		String hex = dist_to_hex(dist);
    		Log.d("tag", hex);
    		getWindow().getDecorView().setBackgroundColor(Color.parseColor(hex));
    	}
    	
    }
    public void Button_DOWN(View view){
    	Log.d("tag", "Down Button was PRESSED");
    	Boolean check = p1.swipe_Down(grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    	double dist = p1.check_Dist(s.getX(), s.getY());

    	if (dist == 0){
    		setContentView(R.layout.end_screen);
    	}else{
    		s.start(bounds, grid.getGrid());
    		Log.d("tag", "" + dist);
    		String hex = dist_to_hex(dist);
    		Log.d("tag", hex);
    		getWindow().getDecorView().setBackgroundColor(Color.parseColor(hex));
    	}
    }
    public void Button_RIGHT(View view){
    	Log.d("tag", "Right Button was PRESSED");
    	Boolean check = p1.swipe_Right(bounds, grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    	double dist = p1.check_Dist(s.getX(), s.getY());

    	if (dist == 0){
    		setContentView(R.layout.end_screen);
    	}else{
    		s.start(bounds, grid.getGrid());
    		Log.d("tag", "" + dist);
    		String hex = dist_to_hex(dist);
    		Log.d("tag", hex);
    		getWindow().getDecorView().setBackgroundColor(Color.parseColor(hex));
    	}
    }
    public void Button_LEFT(View view){
    	Log.d("tag", "Left Button was PRESSED");
    	Boolean check = p1.swipe_Left(grid.getGrid());
    	if (!check){
    		v.vibrate(500);
    	}
    	Log.d("tag", p1.getX() + "-" + p1.getY());
    	double dist = p1.check_Dist(s.getX(), s.getY());
  
    	if (dist == 0){
    		setContentView(R.layout.end_screen);
    	}else{
    		s.start(bounds, grid.getGrid());
    		Log.d("tag", "" + dist);
    		String hex = dist_to_hex(dist);
    		Log.d("tag", hex);
    		getWindow().getDecorView().setBackgroundColor(Color.parseColor(hex));
    	}
    }
    public String dist_to_hex(double dist){
    	return hexcodes[(int)Math.round(dist*30/ (bounds*1.4))];
    }
    
    
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
