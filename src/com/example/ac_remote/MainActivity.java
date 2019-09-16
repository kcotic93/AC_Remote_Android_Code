package com.example.ac_remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	int timerCounter = 0;
	int fanCounter=0;
	int tempCounter=22;
	int countermode=0;
	String countermod[]={"A","C","D","F","H"};
	public static String PORT;
	public static String IP;
	public TextView temp;
	public TextView humy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Read IP address from preferences!!
		SharedPreferences settings =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		IP= settings.getString("address", "");
		PORT= settings.getString("port", "");
		new JSONTask().execute("http://"+IP+":"+PORT+"/?cmd=test");
		
		temp=(TextView)findViewById(R.id.texttemp);
		Typeface temp1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	    temp.setTypeface(temp1);
	    humy=(TextView)findViewById(R.id.texthumy);
		Typeface humy1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	    humy.setTypeface(humy1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
		Intent intent =new Intent(this,SettingsActivity.class);
		startActivity(intent);
		return true;	
			
		}
		if (id == R.id.action_about) {
			Intent intent =new Intent(this,About.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void OnClickBtn(View v) {
		  //Buttons and Textviews declarations
		  ToggleButton acOn = (ToggleButton) findViewById(R.id.toggleButton1);
	      Button tempDown = (Button) findViewById(R.id.button_tempdown);
	      Button tempUp = (Button) findViewById(R.id.button_tempup);
	      Button timerUp = (Button) findViewById(R.id.button_timerplus);
	      Button timerDown = (Button) findViewById(R.id.timerminus);
	      Button fan = (Button) findViewById(R.id.butn_fan);
	      Button mode = (Button) findViewById(R.id.button_mode);
		  //Seven Segment font for the timer counter
	      TextView timercounter=(TextView)findViewById(R.id.textTimer);
	      Typeface timercounter1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      timercounter.setTypeface(timercounter1);
	      TextView fancounter=(TextView)findViewById(R.id.textFan);
	      Typeface fancounter1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      fancounter.setTypeface(fancounter1);
	      TextView modecounter=(TextView)findViewById(R.id.textmode);
	      Typeface modecounter1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      modecounter.setTypeface(modecounter1);
	      TextView temperature = (TextView) findViewById(R.id.texttemperature);
	      temperature.setText("Temperature");
	      Typeface temperature1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      temperature.setTypeface(temperature1);
	      TextView humidity = (TextView) findViewById(R.id.texthumidity);
	      humidity.setText("Humidity");
	      Typeface humidity1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      humidity.setTypeface(humidity1);
	      TextView fanspeed = (TextView) findViewById(R.id.textfanspeed);
	      fanspeed.setText("Fan Speed");
	      Typeface fanspeed1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
	      fanspeed.setTypeface(fanspeed1);
	      TextView timer = (TextView) findViewById(R.id.texttimer);
		  timer.setText("Timer");
		  Typeface timer1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
		  timer.setTypeface(timer1);
		  TextView modetxt = (TextView) findViewById(R.id.textmode1);
		  modetxt.setText("Mode");
		  Typeface modetxt1=Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
		  modetxt.setTypeface(modetxt1);
		  
	      
		  switch (v.getId()) {
	          case R.id.toggleButton1:
			      //If ACON is checked
	        	  boolean on = acOn.isChecked();
			        
			        if (on) {
					    //Enable all  buttons and turns on the AC
			        	tempDown.setEnabled(true); 
			            tempUp.setEnabled(true);
			            timerUp.setEnabled(true);
			            timerDown.setEnabled(true);
			            fan.setEnabled(true);
			            mode.setEnabled(true);
			            fanspeed.setVisibility(TextView.VISIBLE);
			            temperature.setVisibility(TextView.VISIBLE);
			            humidity.setVisibility(TextView.VISIBLE);
			            timer.setVisibility(TextView.VISIBLE);
			            modetxt.setVisibility(TextView.VISIBLE);
			            modecounter.setText(countermod[countermode]);
			            modecounter.setVisibility(TextView.VISIBLE);
			            //?cmd=AC_ON- the webpage for the command we created in the arduino
			            new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=AC_ON");                   
			            
			        } else {
					    //else disable all buttons and turn off the AC
			        	tempDown.setEnabled(false);
			            tempUp.setEnabled(false);
			            timerUp.setEnabled(false);
			            timerDown.setEnabled(false);
			            fan.setEnabled(false);
			            mode.setEnabled(false);
			            timercounter.setVisibility(TextView.INVISIBLE);
			            fancounter.setVisibility(TextView.INVISIBLE);
			            modecounter.setVisibility(TextView.INVISIBLE);
			            timer.setVisibility(TextView.INVISIBLE);
			            temperature.setVisibility(TextView.INVISIBLE);
			            fanspeed.setVisibility(TextView.INVISIBLE);
			            humidity.setVisibility(TextView.INVISIBLE);
			            modetxt.setVisibility(TextView.INVISIBLE);
			            timerCounter=0;
			            fanCounter=0;
			            new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=AC_OFF");
			        }
	            break;
	          case R.id.button_tempup:
			      //Increase Temperature
	        	  tempCounter=tempCounter+1;
	        	  if (tempCounter > 30){
	        		  tempCounter=30;
	        	   	  }
	        	  
	        	  temp.setText(String.valueOf(tempCounter+"\u00b0"+"C"));
	        	  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=TEMP_UP");
	        	  new JSONTask().execute("http://"+IP+":"+PORT+"/?cmd=test");
	        	  	        	  
	            break;
	          case R.id.button_tempdown:
			      //Decrease Temperature
	        	  tempCounter=tempCounter-1;
	        	  if (tempCounter < 18){
	        		  tempCounter=18;
	        	   	  }
	        	  
	        	  temp.setText(String.valueOf(tempCounter+"\u00b0"+"C"));
	        	  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=TEMP_DOWN");
	        	  new JSONTask().execute("http://"+IP+":"+PORT+"/?cmd=test");
	        	  
	            break;
	          case R.id.button_mode:
			      //Mode fan,auto...
	        	  
	        	  countermode = countermode+1;
	        	  if (countermode > 4){
	        		  countermode=0;
	        	   	  }
	        		  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=MODE");
	        		 modecounter.setText(countermod[countermode]);
	            break;
	          case R.id.button_timerplus:
			      //Timer Increase 
	        	  //AC had a maximum of 12 hours from timer.
	        	  timercounter.setVisibility(TextView.VISIBLE);
	        	  timerCounter = timerCounter+1;
	        	  if (timerCounter > 12){
	        		  timerCounter=0;
	        	   	  }
	        		  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=TIMER_UP");
	        		 timercounter.setText(String.valueOf(timerCounter));      	  
	        	     break;
	          case R.id.timerminus:
	        	  //Timer Decrease 
	        	  timercounter.setVisibility(TextView.VISIBLE);
	        	  timerCounter = timerCounter-1;
	        	  if (timerCounter < 0){
	        		  timerCounter=12;
	        	   	  }
	        		  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=TIMER_DOWN");
	        		  timercounter.setText(String.valueOf(timerCounter));
	        		  break;
	          case R.id.butn_fan:
	        	  //Fan Speed
	        	  fancounter.setVisibility(TextView.VISIBLE);
	        	  fanCounter = fanCounter+1;
	        	  if (fanCounter > 3){
	        		  fancounter.setText("A");
	        		  fanCounter=0;
	        	   	  }
	        	  else
	        	  {
	        		  fancounter.setText(String.valueOf(fanCounter));
	        	  }
	        	  new RequestTask().execute("http://"+IP+":"+PORT+"/?cmd=FAN");
	        	  break;
	          }
	    }
		
	    
	    
	    class RequestTask extends AsyncTask<String, String, String>{
	    	//AsyncTask for send command to webpage
	        @Override
	        protected String doInBackground(String... uri) {
	           
	        String responseString = null;
	            try {
		            	URL url = new URL(uri[0]);
						HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
						urlConnection.setConnectTimeout(5000);
						int code = urlConnection.getResponseCode();
						if(code==200)
						{
								 responseString=String.valueOf(code);
						}
						else
						{
			                    //Closes the connection.
			            }
					
					} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					return responseString ="Error";
					} catch (IOException e) {
					// TODO Auto-generated catch block
					return responseString ="Error";
					} 
	            
	            return responseString;
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            super.onPostExecute(result);
	            //Do anything with response..
				//If the response is 200 means its OK, the command was executed. Any other response means the command didnt execute.
	            if (result.equals("200")){
	            	
	            Toast.makeText(getApplicationContext(),"Command processed",Toast.LENGTH_LONG).show();
	            }else{
	                  Toast.makeText(getApplicationContext(),"AC not reachable or no internet connection!",Toast.LENGTH_LONG).show();
	            }	            
	        }
	    } 
	    public class JSONTask extends AsyncTask<String, String, String>{
	    	//AsyncTask for getting JSON object from webpage
	        @Override
	        protected String doInBackground(String... params) {
	        	HttpURLConnection connection=null;
	            BufferedReader reader=null;
	            try {
					URL url = new URL(params[0]);
					
						connection = (HttpURLConnection) url.openConnection();
						connection.setConnectTimeout(5000);
						connection.connect();
						InputStream stream=connection.getInputStream();
						reader=new BufferedReader(new InputStreamReader(stream));
						StringBuffer buffer=new StringBuffer();
						String line="";
						while((line=reader.readLine())!=null)
						{
							buffer.append(line);
						}
						String finalJson=buffer.toString();
						JSONObject parrentObject=new JSONObject(finalJson);
						JSONArray parrentArray=parrentObject.getJSONArray("res");//JSON object Title,parent object
						JSONObject finalObject=parrentArray.getJSONObject(0);
						
						String tempvalue=finalObject.getString("temp"); //JSON temperature value
						String humyvalue=finalObject.getString("humi"); //JSON humidity value
						String TempPlusHumy=tempvalue+"-"+humyvalue;
						
						return TempPlusHumy;
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
					try{
						if(reader!=null){
							reader.close();
						}
						
					}catch
						(IOException e){
							e.printStackTrace();
						}
					}
				
	            return null;
	 
	         }

	        @Override
	        protected void onPostExecute(String result) 
	        {
	            super.onPostExecute(result);
	            if (result==null)
	            {
	            	 Toast.makeText(getApplicationContext(),"AC not reachable or no internet connection!",Toast.LENGTH_LONG).show();
	            }
	            else
	            {
	            	//Split temperature and humidity
	            	String[] parts = result.split("-");
	        		String temperature = parts[0];
	        		String humidity = parts[1];
	        		temp.setText(temperature+"\u00b0"+"C");
	        		humy.setText(humidity+"%");
	            }
	        }
	      }
}