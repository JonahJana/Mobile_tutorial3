package name.tutorial1.captureclaimactivity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatePicker pickerOfDates;

    ImageView iv;


    Calendar cal = Calendar.getInstance();


    public static final int IMAGE_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText dateText = findViewById(R.id.date_text);

        final DatePickerDialog.OnDateSetListener dateThing = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                cal.set(Calendar.YEAR, i);
                cal.set(Calendar.MONTH, i1);
                cal.set(Calendar.DAY_OF_MONTH, i2);
                updateDate(dateText);
            }
        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,dateThing,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //pickerOfDates = findViewById(R.id.the_DatePicker);

        iv = findViewById(R.id.image_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, IMAGE_RESULT);
            }
        });


//        EditText dateText = (EditText) findViewById(R.id.date_text);
//
//        dateText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void updateDate(EditText dateText) {

        String display = "MM/dd/yy";
        SimpleDateFormat myFormat = new SimpleDateFormat(display, Locale.US);

        dateText.setText(myFormat.format(cal.getTime()));

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

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode,resultCode,data);

        if(reqCode == IMAGE_RESULT && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            iv.setImageBitmap(bitmap);
        }
    }
}
