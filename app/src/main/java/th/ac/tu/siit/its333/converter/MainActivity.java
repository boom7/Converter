package th.ac.tu.siit.its333.converter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

/*
    @Override
    protected void onResume() {

        super.onResume();

        helper = new CourseDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT credit, FROM course;", null);
        cursor.moveToFirst();

        double totalcredit = cursor.getDouble(0); // get the first row
        double totalGP = cursor.getDouble(1); // get the first column
        // This method is called when this activity is put foreground.

        String GPA = String.format("%.2f", totalGP / totalcredit);

        TextView tvGp = (TextView)findViewById(R.id.tvGP);
        TextView tvCr = (TextView)findViewById(R.id.tvCR);
        TextView tvGpa = (TextView)findViewById(R.id.tvGPA);
        tvGp.setText(String.format("%.1f",totalGP));
        tvCr.setText(String.format("%.0f",totalcredit));
        tvGpa.setText(GPA);

    }
*/
    public void buttonClicked(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.btBinary:
                i = new Intent(this, BinaryConvert.class);
                startActivity(i);
                break;
            case R.id.btAdd:
                i = new Intent(this, LengthConvert.class);
                startActivity(i);
                break;
            case R.id.btForce:
                i = new Intent(this, ForceConvert.class);
                startActivity(i);
                break;

            case R.id.btShow:
                i = new Intent(this, ListHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.imageButton:
                i = new Intent(this, Exchange.class);
                Log.d("Test","Image button clicked");
                startActivity(i);
                break;


            case R.id.btReset:
                helper = new DBHelper(this);
                SQLiteDatabase db = helper.getWritableDatabase();
                db.delete("course","",null);

                onResume();
                break;
        }
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                String code = data.getStringExtra("code");
                int credit = data.getIntExtra("credit", 0);
                String grade = data.getStringExtra("grade");
                helper = new CourseDBHelper(this);

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues r = new ContentValues();
                r.put("code", code);
                r.put("credit", credit);
                r.put("grade", grade);
                r.put("value", gradeToValue(grade));




                long new_id = db.insert("course", null, r);
            }
        }

        Log.d("course", "onActivityResult");
    }
*/
    double gradeToValue(String g) {
        if (g.equals("A"))
            return 4.0;
        else if (g.equals("B+"))
            return 3.5;
        else if (g.equals("B"))
            return 3.0;
        else if (g.equals("C+"))
            return 2.5;
        else if (g.equals("C"))
            return 2.0;
        else if (g.equals("D+"))
            return 1.5;
        else if (g.equals("D"))
            return 1.0;
        else
            return 0.0;
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
