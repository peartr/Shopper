package com.example.ramon.shopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddItem extends AppCompatActivity {

    Intent intent;

    long id;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the Bundle that contaiins the id passed form the Main Activity
        bundle = this.getIntent().getExtras();

        // get the id in the Bundle
        id = bundle.getLong("_id");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set the Action Bar of the Add Item Activity to whatever is define in the menu main menu
        // resource
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

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

    public void addItem(MenuItem menuItem){

    }


}
