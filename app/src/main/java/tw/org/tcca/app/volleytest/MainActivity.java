package tw.org.tcca.app.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainApp = (MainApp)getApplication();

    }

    public void getTest1(View view) {
    }
}
