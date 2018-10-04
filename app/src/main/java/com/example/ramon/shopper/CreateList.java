package com.example.ramon.shopper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateList extends AppCompatActivity {

    // declare and Intent
    Intent intent;

    // declare EditTexts - used to reference the EditText in the resource file
    EditText nameEditText;
    EditText storeEditText;
    EditText dateEditText;

    // declare Calendar - used to map the date selected in the DatePickerDialog to date EditText
    Calendar calendar;

    // declare DBHandler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditTexts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        storeEditText = (EditText) findViewById(R.id.storeEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        // initialize Calendar
        calendar = Calendar.getInstance();

        // create and initialize a DatePickerDialog and register an OnDateSetListener to it
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            /**
             * This method gets called when a date is set in the DatePickerDialog
             * @param datePicker DatePicker object
             * @param year year that was set
             * @param monthOfYear month that was set
             * @param dayOfMonth day that was set
             */
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                // set the Calendatr year, month, and day to the year, month, and fay set in the
                // DatePicker
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // call a method that updates the date EditText with the date that was set in the
                // DatePicker
                updateDueDate();
            }
        };

        // register an OnClickListener to the date EditText
        dateEditText.setOnClickListener(new View.OnClickListener(){

            /**
             * This method gets called when the date EditText is clicked
             * @param v because the dateEditText that calls this method is technically considered a
             *          view, we must pass the method a View
             */
            @Override
            public void onClick(View v) {
                // display the DatePickerDialog with current date selected
                new DatePickerDialog(CreateList.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH) ).show();
            }
        });

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);
    }

    /**
     *  This method sets the Action Bar of the Main activity to what ever is defined in the main
     *  menu resource.
     * @param menu Menu object
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set the Action Bar of the Main Activity to whatever is define in the menu create list
        // menu resource
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    /**
     * This method gets called when an item in the overflow menu is selected.
     * @param item MenuItem object that contains information about the item selected ink the
     *             overflow; for example, its id
     * @return true if an item is selected, else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // get the id of the item selected
        switch (item.getItemId()){
            case R.id.action_home:
                // initialize an intent for the Main Activity, start intent,
                // return true if the id in the item selected is for the Main Activity
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list:
                // initialize an intent for the Create List Activity, start intent,
                // return true if the id in the item selected is for the Create List Activity
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method gets called when the add_list menu item gets pushed.
     * @param menuItem because the add_list item that calls this method is a menu item, we must
     *                 pass the method a menuItem.
     */
    public void createList(MenuItem menuItem){

        // get data input in EditTexts and store it inn Strings
        String name = nameEditText.getText().toString();
        String store = storeEditText.getText().toString();
        String date = dateEditText.getText().toString();

        // trim Strings and see if any are equal to an emty string
        if (name.trim().equals("") ||store.trim().equals("") || date.trim().equals("") ){
            // required data hasn't been input, so display toast
            Toast.makeText(this, "Please enter a name, Store, and date!", Toast.LENGTH_LONG).show();
        }else{
            // required data has been input, update the database and display a different Toast
            dbHandler.addShoppingList(name, store, date);
            Toast.makeText(this, "Shopping List added", Toast.LENGTH_LONG).show();
            // initialize the intent for the Main Activity
            intent = new Intent(this, MainActivity.class);
            // starts the intent that carries you to the Main Activity
            startActivity(intent);
        }
    }

    /**
     * this method gets called when a date is set in the DatePicker
     */
    public void updateDueDate(){

        // create a SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // apply the SimpleDateFormat to the dat set in the DatePickerDialog and set the ormatted
        // date in the EditText
        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
