package com.example.porag.orientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView one,two,three;
    SensorManager mng;
    Sensor sns;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        mng = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sns = mng.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        dbref=FirebaseDatabase.getInstance().getReference("Bending");



    }

    @Override
    protected void onResume() {
        super.onResume();
        mng.registerListener(this, sns, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mng.registerListener(this, sns, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float azimuth_angle = sensorEvent.values[0];
        float pitch_angle = sensorEvent.values[1];
        float roll_angle = sensorEvent.values[2];

        one.setText(Float.toString(azimuth_angle));
        two.setText(Float.toString(pitch_angle));
        three.setText(Float.toString(roll_angle));
        dbref.child("value").setValue(roll_angle);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
