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

public class ViewList extends AppCompatActivity {

    // declare Bundle
    Bundle bundle;

    Intent intent;

    // declare long to store the id passed from the Main Activity
    long id;

    // declare a dbHandler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the Bundle that contaiins the id passed form the Main Activity
        bundle = this.getIntent().getExtras();

        // get the id in the Bundle
        id = bundle.getLong("_id");

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // call DBHandler method that gets shopping list name
        String shoppingListName = dbHandler.getShoppingListName((int) id);

        // set title of the View List activity to the shopping list name
        this.setTitle(shoppingListName);

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
        getMenuInflater().inflate(R.menu.menu_view_list, menu);
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
            case R.id.action_add_item:
                // initialize an intent for the Add Item Activity, start intent,
                // return true if the id in the item selected is for the Add Item Activity
                intent = new Intent(this, AddItem.class);
                intent.putExtra("_id", id);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method gets called when the add FloatingActionButton is clicked.  It starts the
     * AddItem activity.
     * @param view because the add FloatingActionButton that calls this method is technically
     *             considered a View, we must pass the method a View.
     */
    public void openAddItem(View view){

        intent = new Intent(this, AddItem.class);
        intent.putExtra("_id", id);
        startActivity(intent);
    }

    /**
     * This method gets called when the delete item button in the Action Bar get pushed.  It
     * deletes the shopping list from the database.
     * @param menuItem because the delete item that calls this method is technically considered a
     *                MenuItem, we must pass the method a MenuItem.
     */
    public void deleteShoppingList(MenuItem menuItem){

    }
}
