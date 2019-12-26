package com.justin.review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText month;
    private EditText next;
    boolean isOn =false;
    private String data;
    private Switch aSwitch;
    String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        month = findViewById(R.id.monthly);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button button =findViewById(R.id.summit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caculate();
            }
        });
        aSwitch = findViewById(R.id.switch2);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isOn=isChecked;
            TextView textView =findViewById(R.id.type);
            textView.setText(isOn?getString(R.string.every):getString(R.string.monthly));
            }
        });
        Spinner spinner =findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,getResources().getStringArray(R.array.cities)[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void caculate() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                month.setText("");
            }
        };
        data = month.getText().toString();
        if (isOn==false) {
            if (!TextUtils.isEmpty(data)) {
                float monthn = Float.parseFloat(data);
                double outcome = 0;
                if (monthn >= 1 && monthn <= 10) {
                    outcome = monthn * 7.35f;
                } else if (monthn >= 11 && monthn <= 30) {
                    outcome = (monthn * 9.45f) - 21;
                } else if (monthn >= 31 && monthn <= 50) {
                    outcome = (monthn * 11.55f) - 84;
                } else if (monthn >= 51) {
                    outcome = (monthn * 12.075f) - 110.25f;
                }
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("fee:", outcome);
                startActivity(intent);
            }
        }else {
                float monthn = Float.parseFloat(data);
                double outcome = 0;
                if (monthn >= 1 && monthn <= 20) {
                    outcome = monthn * 7.35f;
                } else if (monthn >= 21 && monthn <= 60) {
                    outcome = monthn * 9.45f - 42;
                } else if (monthn >= 61 && monthn <= 100) {
                    outcome = monthn * 11.55f - 168;
                } else {
                    outcome = monthn * 12.075f - 220.5f;
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("格月抄表")
                        .setMessage(getString(R.string.fee1) + outcome)
                        .setPositiveButton("ok", listener)
                        .show();

        }
        /*else if (TextUtils.isEmpty(data)&&TextUtils.isEmpty(data1)){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Fail")
                    .setMessage("Wrong Enter")
                    .setPositiveButton("ok",null)
                    .show();
        }*/
    }

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
