package com.jessicathornsby.lightsensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private Sensor lightSensor;
    private SensorManager lightSensorManager;
    private TextView lightTextiew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightTextiew = (TextView) findViewById(R.id.lightTextiew);

        lightSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);

        lightSensor = lightSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        String sensor_error = getResources().getString(R.string.no_sensor);
        if (lightSensor == null) { lightTextiew.setText(sensor_error); }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (lightSensor != null) {
            lightSensorManager.registerListener(this, lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        lightSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];
        lightTextiew.setText(getResources().getString(
                R.string.light_sensor, currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //To do//

    }
}