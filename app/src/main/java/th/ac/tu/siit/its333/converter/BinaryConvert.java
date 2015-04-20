package th.ac.tu.siit.its333.converter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BinaryConvert extends ActionBarActivity {
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary);
        helper = new DBHelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_binary, menu);
        return true;
    }

    /* public void addClicked(View v) {
         EditText etCode = (EditText)findViewById(R.id.etCode);
         EditText etCR = (EditText)findViewById(R.id.etCR);
         RadioGroup rg = (RadioGroup)findViewById(R.id.rgGrade);

         String sCode = etCode.getText().toString();
         String sCR = etCR.getText().toString();

         if (sCode.trim().length() == 0 || sCR.trim().length() == 0) {
             Toast t = Toast.makeText(this.getApplicationContext(),
                     "Both course code and credit are necessary.",
                     Toast.LENGTH_SHORT);
             t.show();
         }
         else {
             Intent result = new Intent();
             result.putExtra("code", sCode);
             result.putExtra("credit", Integer.valueOf(sCR));
             int rID = rg.getCheckedRadioButtonId();
             String grade = ((RadioButton)findViewById(rID)).getText().toString();
             result.putExtra("grade", grade);
             this.setResult(RESULT_OK, result);
             this.finish();
         }
     } */

    public void buttonClicked(View v) {


        EditText etInput = (EditText) findViewById(R.id.etInput);
        String s = etInput.getText().toString();

        int v1 = Integer.parseInt(s);
        int c = 0;
        int o = 0;
        String t;
        String j;

        RadioGroup rgFrom = (RadioGroup) findViewById(R.id.rgFrom);
        int selFrom = rgFrom.getCheckedRadioButtonId();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues r = new ContentValues();
        r.put("grade", (v1));


        if (selFrom == R.id.rbFrmI) { //The user wants to convert from Celsius.
            c = v1;
            j = "Decimal";
            r.put("value", String.valueOf(j));

        } else if (selFrom == R.id.rbFrmC) { //The user wants to convert from Celsius.
            String bin;
            bin = Integer.toString(v1);
            String binary = bin;
            c = Integer.parseInt(binary, 2);
            j = "Binary";
            r.put("value", String.valueOf(j));



        }


        RadioGroup rgTo = (RadioGroup) findViewById(R.id.rgTo);
        int selTo = rgTo.getCheckedRadioButtonId();

        if (selTo == R.id.rbToI) { //The user wants to convert from Celsius.


            o = c;
            TextView tv;
            tv = (TextView) findViewById(R.id.tvOutput);
            //The variable tv is referring to the TextView widget in the layout.
            tv.setText(Double.toString(o));
            t = "Decimal";
            r.put("valueone",String.valueOf(t));
            r.put("code", String.valueOf(o));





        } else if (selTo == R.id.rbToC) { //The user wants to convert from Celsius.
            o = c;

            TextView tv;
            tv = (TextView) findViewById(R.id.tvOutput);
            //The variable tv is referring to the TextView widget in the layout.

            tv.setText(Integer.toString(c, 2));
            t = "Binary";
            r.put("valueone",String.valueOf(t));
            r.put("code", Integer.toString(c, 2));



        }





        long new_id = db.insert("course", null, r);
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
