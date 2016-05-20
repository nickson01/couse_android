package com.walkingwithdev.nick.helloworld;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello;
    TextView edtHello;
    Button btnCopy;
    EditText editText1;
    EditText editText2;
    TextView tvResult;
    Button btnCalculate;
    RadioGroup rbg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(MainActivity.this,"x = " + width + "  y = " + height,Toast.LENGTH_LONG).show();
    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setText(Html.fromHtml("<b>He<u>ll</u>o</b> <i>World</i> <font color=\"ff00ff\">La La La</font>"));

        edtHello = (TextView) findViewById(R.id.edtHello);
        edtHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tvHello.setText(edtHello.getText());
                    return true;
                }
                return false;
            }
        });


        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        tvResult = (TextView) findViewById(R.id.tvResult);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        rbg1 = (RadioGroup) findViewById(R.id.rbg1);

        btnCalculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(edtHello.getText());
        } else if (v == btnCalculate) {
            int num1 = 0;
            int num2 = 0;
            int result = 0;


            try {
                num1 = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException e) {

            }

            try {
                num2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {

            }

            switch (rbg1.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = num1 + num2;
                    break;
                case R.id.rbMinus:
                    result = num1 - num2;
                    break;
                case R.id.rbMultiply:
                    result = num1 * num2;
                    break;
                case R.id.rbDivide:
                    result = num1 / num2;
                    break;
            }

            tvResult.setText(result + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
