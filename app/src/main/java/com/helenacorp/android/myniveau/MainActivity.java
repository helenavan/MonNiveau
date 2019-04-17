package com.helenacorp.android.myniveau;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView deltaY, deltaX;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deltaX= (TextView) findViewById(R.id.deltax);
        deltaY = (TextView) findViewById(R.id.deltay);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        deltaX.setText((int)(event.values[0]*100/ SensorManager.GRAVITY_EARTH)+" %");
        deltaY.setText((int)(event.values[1]*100/ SensorManager.GRAVITY_EARTH)+" %");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
