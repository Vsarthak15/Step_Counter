package com.sv15.stepcounter;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView tv_1,tv_2;
    SensorManager sensorManager;
    Boolean running= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1= findViewById(R.id.textView);
        tv_2= findViewById(R.id.textView2);
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    @Override
    public void onResume(){
        super.onResume();
        running=true;
        Sensor countSensor= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        running= false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            tv_2.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
