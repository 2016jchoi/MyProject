package com.example.jchoi.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String st;
    private int cDay;
    private int cMonth;
    private String selectedMonth;
    private int cHour;
    private int cMinute;
    private int cSecond;
    private int cTotal;
    private int mInterval = 1000; //1 second
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Getting actual time
        //http://stackoverflow.com/questions/5369682/get-current-time-and-date-on-android
        //http://stackoverflow.com/questions/2271131/display-the-current-time-and-date-in-an-android-application
        //http://stackoverflow.com/questions/26880063/how-to-get-current-date-and-time-in-android
        Calendar calendar = Calendar.getInstance();
        cDay = calendar.get(Calendar.DAY_OF_MONTH);
        cMonth = calendar.get(Calendar.MONTH) + 1;
        selectedMonth = "" + cMonth;
        cHour = calendar.get(Calendar.HOUR);
        cMinute = calendar.get(Calendar.MINUTE);
        cSecond = calendar.get(Calendar.SECOND);
        if(cMonth==1 || cMonth==3 || cMonth==5 || cMonth==7 || cMonth==8 || cMonth==10 || cMonth==12)
            cTotal = cMonth*2678400+cDay*86400+cHour*3600+cMinute*60+cSecond;
        else if (cMonth == 2)
            cTotal = cMonth*2505600+cDay*86400+cHour*3600+cMinute*60+cSecond;
        else
            cTotal = cMonth*2592000+cDay*86400+cHour*3600+cMinute*60+cSecond;

        Log.i(cHour+"", "CORRECT?");

        //Runnable (how long it's gonna run for)
        //Define behavior at the end of the second
        //Runs for entire app
        //https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=android%20runnable%20repeat%20one%20second
        //http://stackoverflow.com/questions/6242268/repeat-a-task-with-a-time-delay

        mHandler = new Handler();
        startRepeatingTask();

        //Calendar object - unix time
        //Get difference between current calendar and event calendar in milliseconds and convert that to days, minutes, seconds

        //choosing an event from the spinner (list)
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final ArrayAdapter<String> dataAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.value_array));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        st = spinner1.getSelectedItem()+"";
        //make a for-loop to "find" the object with that event to access time
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st = parent.getSelectedItem() + "";
                for(int i=0; i<10; i++)
                {
                    if()
                }
                //CountdownEvent c = new CountdownEvent(st, unix time);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //display event name
        TextView event = (TextView)findViewById(R.id.event);
        event.setText(st.toUpperCase() + "!!!");

        //add and delete events
        final EditText editT = (EditText)findViewById(R.id.name);
        Button buttonC = (Button) findViewById(R.id.create);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = editT.getText()+"";
                dataAdapter.add(str);
            }
        });
        Button buttonD = (Button) findViewById(R.id.delete);
        buttonD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = editT.getText() + "";
                dataAdapter.remove(str);
            }
        });
    }
    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    //might need to put into onCreate
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                mInterval = 1000;
                //this function can change value of mInterval.
                //DO I HAVE TO PUT SOMETHING HERE?!?!
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class CountdownEvent{
    private long time;
    private String event;

    public CountdownEvent(String s, long t){
        s = event;
        t = time;
    }
    public void setEvent(String s) {
        s = event;
    }
    public void setTime(long t) {
        t = time;
    }
    public String getEvent() {
        return event;
    }
    public long getTime() {
        return time;
    }
}
class CountdownEventManager{
    private long time;
    private String event;
    private CountdownEvent c;

    public CountdownEventManager(CountdownEvent count){
        count = c;
    }
    public void add(String s, long t)
    {
        c.setEvent(s);
    }
    public void delete(String s, long t)
    {

    }
}
