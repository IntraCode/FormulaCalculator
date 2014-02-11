package org.intracode.formulacalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvFormulas = (ListView) findViewById(R.id.lvFormulas); //initialize the listview
        Button btnChooseFormula = (Button) findViewById(R.id.btnChooseFormula);
        String[] formulas = new String[] { // string-array with the supported formulas
                "PQ-Formula",
                "ABC-Formula",
                "URI-Formulas",
                "Pythagorean Theorem",
                "Linear Functions",
                "Exponential Functions"
        };
        //creating an adapter for the listview
        ArrayAdapter<String> lvFormulasAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, formulas) {

            //using method to change the text color (white > black)
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                View mView = super.getView(position, convertView, parent);
                if (mView != null) {
                TextView textView = (TextView) mView.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                }
                return mView;
            }
        };

        lvFormulas.setAdapter(lvFormulasAdapter); //set the adapter

        btnChooseFormula.setOnClickListener(new View.OnClickListener() { //setting the click listener for the button
            @Override
            public void onClick(View v) {
                //TODO: pass through parameters to the next activity
                //TODO: create an enum with all possibilities
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    AlertDialog alert; //alert dialog for later reference; placed within the class to avoid final declaration
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) { //check which button is clicked

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this); //creating the alert builder
            alertBuilder.setIcon(android.R.drawable.ic_menu_info_details) //setting the icon
            .setMessage("Developed By:\nJohnny Manson & Ben Nathan") //setting the message
            .setTitle("App Information") //Setting the Message
            .setNeutralButton("Got it!", new DialogInterface.OnClickListener() { //setting the neutral button's click listener
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (alert != null) //check if alert has been assigned
                        alert.dismiss(); //get rid of the dialog popup
                }
            });
            alert = alertBuilder.create(); //assign the alert by using the create method
            alert.show(); //display the alert
        }
        return super.onOptionsItemSelected(item);
    }
}
