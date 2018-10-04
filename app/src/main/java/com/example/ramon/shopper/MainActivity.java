package com.example.ramon.shopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // declare and Intent - used to start activities
    Intent intent;

    // declare a DBHandler - used to communicate with the database
    DBHandler dbHandler;

    // declare a ShoppingLists CursorAdapter - used to link the data in the Cursor to the ListView
    ShoppingList shoppingListAdapter;

    // declare a ListView - used to reference the ListView in the resource file
    ListView shopperListView;

    /**
     * This method initializes the Main Activity.
     * @param savedInstanceState Bundle object that may contain data about the previous state of
     *                           the Activity.  If it does contain data about the previous state,
     *                           the Activity will be restored to that state.  If it doesn't contain
     *                           data about the previous state, the Activity will behave as if its's
     *                           being viewed fro the first time.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // restore Main Activity to previous state, if there is data about the previous state in
        // the savedInstanceState Bundle
        super.onCreate(savedInstanceState);

        // set the View of the Main Activity to whatever is defined in the activity main layout
        // resource
        setContentView(R.layout.activity_main);

        // set the Action Bar of the Main Activity to whatever is defined by yhe Basic Activity
        // Action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // initialize ListView
        shopperListView = (ListView)findViewById(R.id.shopperListView);

        // initialize ShoppingLists CursorAdapter with the shopping list dat in the database
        shoppingListAdapter = new ShoppingList(this, dbHandler.getShoppingLists(), 0);

        shopperListView.setAdapter(shoppingListAdapter);

        // register OnItemClickListener on the ListView
        shopperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // initialize an intent for the View List Activity, start intent
                intent = new Intent(MainActivity.this, ViewList.class);

                // put the shopping list id of the clicked row in the intent
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });
    }

    /**
     *  This method sets the Action Bar of the Main activity to what ever is defined in the main
     *  menu resource.
     * @param menu Menu object
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set the Action Bar of the Main Activity to whatever is define in the menu main menu
        // resource
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
     * This method gets called when the fabCreateList floating action button gets clicked.
     * it starts the Create List Activity.
     * @param view because the fabCreateList floating action button is considered a view, we must
     *             pass the method a View object
     */
    public void openCreateList(View view){
        // initialize an intent for the Create List Activity, start intent
        intent = new Intent(this, CreateList.class);
        startActivity(intent);

    }
}
