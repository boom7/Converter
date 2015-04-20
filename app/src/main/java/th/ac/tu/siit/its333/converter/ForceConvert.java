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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ForceConvert extends ActionBarActivity {
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force);
        helper = new DBHelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_force, menu);
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
    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public void buttonClicked(View v) {


        EditText etInput = (EditText)findViewById(R.id.etInput);
        String s = etInput.getText().toString();

        double v1 = Double.parseDouble(s);
        double c = 0;
        double o = 0;
        String t;
        String j;

        RadioGroup rgFrom = (RadioGroup)findViewById(R.id.rgFrom);
        int selFrom = rgFrom.getCheckedRadioButtonId();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues r = new ContentValues();
        r.put("grade", (v1));


        if (selFrom == R.id.rbFrmI) { //The user wants to convert from Celsius.
            c = v1;
            j = "Newton";
            r.put("value", String.valueOf(j));
        }
        else if (selFrom == R.id.rbFrmC) { //The user wants to convert from Celsius.
            c = v1 / 0.0002248 ;
            j="Kip";
            r.put("value", String.valueOf(j));
        }


        else if (selFrom == R.id.rbFrmF) {
            c = v1 /0.2248;
            j="Pound";
            r.put("value", String.valueOf(j));
    }






        RadioGroup rgTo = (RadioGroup)findViewById(R.id.rgTo);
        int selTo = rgTo.getCheckedRadioButtonId();

        if (selTo == R.id.rbToI) { //The user wants to convert from Celsius.
            o = round(c,2);
            t="Newton";
            r.put("credit",String.valueOf(t));

        }

        else if (selTo == R.id.rbToC) { //The user wants to convert from Celsius.
            o = round(c * 0.0002248,2);
            t="Kip";
            r.put("credit",String.valueOf(t));
        }

        else if (selTo == R.id.rbToF){
            o = round(c* 0.2248,2);
            t="Pound";
            r.put("credit",String.valueOf(t));

        }


        TextView tv;
        tv = (TextView) findViewById(R.id.tvOutput);
        //The variable tv is referring to the TextView widget in the layout.
        tv.setText(Double.toString(o));





        r.put("code", String.valueOf(o));




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
