package com.example.droidcafe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();

        String message = "Order: " +
                intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.OrderTV);
        try {
            if (!intent.getStringExtra(MainActivity.EXTRA_MESSAGE).isEmpty()) {

            }
            textView.setText(message);
        } catch (Exception e){
            textView.setText("Order: You didn't order anything yet");
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.lables,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner spinner = findViewById(R.id.label_spiner);
        spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String lable = parent.getItemAtPosition(position).toString();
                    displayToast(lable);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        EditText editText = findViewById(R.id.phone_text);
        if(editText != null) editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled= false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    dialNumber();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void dialNumber() {
        EditText editText = findViewById(R.id.phone_text);
        String phoneNum = null;
        if(editText != null) phoneNum = "tel: " + editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    private void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onRedioButtonClicked(View view) {
        RadioButton selected = (RadioButton) view;
        boolean checked = selected.isChecked();
        if(selected.getId() == R.id.sameday){
            if(checked){
                displayToast("Same day service is selected");
            }
        } else if(selected.getId() == R.id.nextday){
            if(checked){
                displayToast("Next day service is selected");
            }
        } else if(selected.getId() == R.id.picup){
            if(checked){
                displayToast("Pick up service is selected");
            }
        }
    }
}
