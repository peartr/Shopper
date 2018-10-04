package com.example.ramon.shopper;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ShoppingList extends CursorAdapter {

    /**
     * Initializes ShoppingList CursorAdapter.
     * @param context reference to the activity that initialized the CursorAdapter
     * @param cursor reference to the Cursor that contains the data from the database
     * @param flags determines special behaviour of the CursorAdapter.  will always be 0 which
     *              means the CursorAdapter shouldn't ha any special behavior.
     */
    public ShoppingList(Context context, Cursor cursor, int flags){
        super(context, cursor, flags);
    }

    /**
     * Makes a new Vew to hol the data in the Cursor.
     * @param context reference to activity that initialized the CursorAdapter
     * @param cursor reference to the cursor that contains the dat from the database
     * @param viewGroup reference to the shopperListView in the Main Activity
     * @return reference to the new View that will be displayed in teh main Activity, e.g. the one
     * that contains the data
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.li_shopping_list, viewGroup, false);
    }

    /**
     * Binds the new View to teh ata in the Cursor.
     * @param view new View just created
     * @param context reference to activity that initialized the CursorActivity
     * @param cursor reference to the c=Cursor that contains the data from the database
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.nameTextView)).setText(cursor.getString(cursor.getColumnIndex("name")));
        ((TextView) view.findViewById(R.id.storeTextView)).setText(cursor.getString(cursor.getColumnIndex("store")));
        ((TextView) view.findViewById(R.id.dateTextView)).setText(cursor.getString(cursor.getColumnIndex("date")));

    }


}
