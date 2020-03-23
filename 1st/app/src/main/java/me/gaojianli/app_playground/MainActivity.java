package me.gaojianli.app_playground;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity Started");
        Button btn1=findViewById(R.id.button);
        final TextView text1=findViewById(R.id.textView);
        btn1.setOnClickListener(v->text1.setText("test"));
        RadioGroup rg1=findViewById(R.id.radioGroup1);
        rg1.setOnCheckedChangeListener((g,index)->{
            int id=g.getCheckedRadioButtonId();
            switch (id){
                case R.id.choose1button:
                    Log.d(TAG, "choose1 is chosen");
                    break;
                case R.id.choose2button:
                    Log.d(TAG, "choose2 is chosen");
                    break;
            }
        });
        CheckBox checkbox=findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener((box,value)->{
            Log.d(TAG, value?"box checked":"box unchecked");
        });
    }
}
