package cn.rexwear.wearrex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "Wear ReX TAG";
    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.time);

        TimeThread timeThread = new TimeThread(textViewTime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间


    }
}