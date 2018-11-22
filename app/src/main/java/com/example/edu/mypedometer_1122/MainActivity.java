package com.example.edu.mypedometer_1122;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    float proviousY, currentY, steps, acceleration, threshold , x, y, z;
    Button btn_reset;
    TextView textView1, textView2, textView3, textView;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        threshold = 3;

        SeekBar sb  = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }

        );

        proviousY = currentY = steps = 0;
        acceleration = 0.0f;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
                );
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        currentY = y;
        if(Math.abs(currentY - proviousY ) > threshold){
            steps++;
            textView.setText(String.valueOf(steps));
        }
        textView1.setText(String.valueOf(x));
        textView2.setText(String.valueOf(y));
        textView3.setText(String.valueOf(z));
        proviousY = y;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        proviousY = x = y = z = steps = 0 ;
        textView1.setText(String.valueOf(x));
        textView2.setText(String.valueOf(y));
        textView3.setText(String.valueOf(z));
    }
}
