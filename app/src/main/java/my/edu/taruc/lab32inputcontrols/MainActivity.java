package my.edu.taruc.lab32inputcontrols;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxSmoker;
    private RadioButton radioButtonMale, radioButtonFemale;
    private TextView textViewPremium;

    //create the spinner things drop down
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Linking UI to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //Create an adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this, "Position:" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view)
    {
        double premium = 0;
        int pos;

        pos = spinnerAge.getSelectedItemPosition();

        checkGender(view,premium,pos);
        checkSmoker(view,pos,premium);

        if(pos ==0){
            premium += 50;
        }
        else if(pos == 1){
            premium += 55;
        }
        else if(pos == 2){
            premium += 60;
        }
        else if(pos == 3){
            premium += 70;
        }
        else if(pos == 4){
            premium += 120;
        }
        else if(pos == 5){
            premium += 160;
        }
        else if(pos == 6){
            premium += 200;
        }
        else if(pos == 7){
            premium += 250;
        }
        else{
            premium = 0;
        }

        //Display premium
        textViewPremium.setText(getString(R.string.premium) + premium);
    }

    public void checkGender(View view, double addPremium, int position){
        //Determine the gender of user
        int indexGender;
        indexGender = radioGroupGender.getCheckedRadioButtonId();
        if(indexGender == R.id.radioButtonMale &&  position == 2 && position == 5){
            //TODO: calculate premium for male
            addPremium += 50;
        }
        else if(indexGender == R.id.radioButtonMale &&  position == 3 && position == 4){
            //TODO: calculate premium for female
            addPremium += 100;
        }

    }

    public void checkSmoker(View view,int position, double addPremium){
        boolean isSmoker = false;
        //Check smoker
        if(checkBoxSmoker.isChecked() && position == 3){
            isSmoker = true;
            addPremium +=100;
        }
        else if(checkBoxSmoker.isChecked() && position == 4 && position ==5){
            isSmoker = true;
            addPremium +=150;
        }
        else if(checkBoxSmoker.isChecked() && position == 6 && position == 7){
            isSmoker = true;
            addPremium +=200;
        }


    }
}
