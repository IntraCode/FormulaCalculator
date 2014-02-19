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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    View selectedView = null; //field to get the selected view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lvFormulas = (ListView) findViewById(R.id.lvFormulas); //initialize the listview
        final Button btnChooseFormula = (Button) findViewById(R.id.btnChooseFormula);
        final String[] formulas = new String[] { // string-array with the supported formulas
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
                    mView.setTag(getFormulaType(String.valueOf(textView.getText()))); // sets the formula type as a string to the tag
                }
                return mView;
            }
        };

        lvFormulas.setAdapter(lvFormulasAdapter); //set the adapter
        lvFormulas.setOnItemClickListener(new AdapterView.OnItemClickListener() { // is triggered when an item is clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedView = view; //assign the field
            }
        });


        btnChooseFormula.setOnClickListener(new View.OnClickListener() { //setting the click listener for the button
            @Override
            public void onClick(View v) {
                if (selectedView != null) {
                    String formula = (String)selectedView.getTag(); // get the formula type from the tag
                    Toast.makeText(getApplicationContext(), formula, Toast.LENGTH_SHORT).show(); // show the text which has been retrieved
                }
            }
        });
    }

    private String getFormulaType(String formulaTypeUnformatted) {
        char indexFind = (formulaTypeUnformatted.contains(" ")) ? ' ' : '-'; // determines what to look for

        if (indexFind == -1) // return null if there is no space / dash character
            return null;

        return formulaTypeUnformatted.substring(0, formulaTypeUnformatted.indexOf(indexFind)); // return everything in front of the space / dash
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // inflate the menu; this adds items to the action bar if it is present.
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
