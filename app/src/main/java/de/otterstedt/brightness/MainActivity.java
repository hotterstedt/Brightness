package de.otterstedt.brightness;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar  seekBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int brightness = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setMax(255); // System.SCREEN_BRIGHTNESS nimmt Werte zwischen 0 und 255 an
        textView = (TextView) findViewById(R.id.textView1);

        try {
            brightness = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        // Ursprümglichen Wert setzen
        seekBar.setProgress(brightness);
        textView.setText("Brightness: " + brightness);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int new_brightness = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progres, boolean from) {
                new_brightness = progres;
                // Geänderten Wert setzen
                android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, new_brightness);
                textView.setText("Brightness: " + new_brightness);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
