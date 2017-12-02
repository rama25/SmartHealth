package com.example.rama.smarthealth;

/**
 * Created by Rama on 21-11-2017.
 */

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DbDetails extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editAge, editBloodgroup ,editSleep, editCardio, editAlcohol, editTobacco, editDisease;
    Button btnSubmit, btnView;
    TextView OP;
    TextView disease;
    //int age, ageScore, sleep, sleepScore, cardio, cardioScore, alcohol, alcoholScore, tobacco, tobaccoScore, disease, diseaseScore, totalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText5);
        editAge = (EditText)findViewById(R.id.editText8);
        editBloodgroup = (EditText)findViewById(R.id.editText9);
        editSleep = (EditText)findViewById(R.id.editText10);
        editCardio = (EditText)findViewById(R.id.editText11);
        editAlcohol = (EditText)findViewById(R.id.editText12);
        editTobacco = (EditText)findViewById(R.id.editText13);
        editDisease = (EditText)findViewById(R.id.editText14);
        btnSubmit = (Button)findViewById(R.id.button2);
        btnView = (Button)findViewById(R.id.button3);
        disease = (TextView) findViewById(R.id.textView14);

        disease.setMovementMethod(new ScrollingMovementMethod());

        onSubmit();
        viewAll();

    }

    public void onSubmit(){
        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted =  myDb.insertData(editName.getText().toString(), editAge.getText().toString(),
                                editBloodgroup.getText().toString(), editSleep.getText().toString(),
                                editCardio.getText().toString(), editAlcohol.getText().toString(),
                                editTobacco.getText().toString(), editDisease.getText().toString(), calculateScore(Integer.parseInt(editAge.getText().toString()), Integer.parseInt(editSleep.getText().toString()),
                                        Integer.parseInt(editCardio.getText().toString()), Integer.parseInt(editAlcohol.getText().toString()), Integer.parseInt(editTobacco.getText().toString()),
                                        Integer.parseInt(editDisease.getText().toString())));

                        if(isInserted == true)
                            Toast.makeText(DbDetails.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DbDetails.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public int calculateScore(int age, int sleep, int cardio, int alcohol, int tobacco, int disease){
        int ageScore, sleepScore, cardioScore, alcoholScore, tobaccoScore, diseaseScore, totalScore;

        if(age<30)
            ageScore = 20;
        else if(age>=30 && age<=50)
            ageScore = 14;
        else
            ageScore = 8;

        if(sleep==4)
            sleepScore = 6;
        else if(sleep==5)
            sleepScore = 8;
        else if(sleep==6)
            sleepScore = 18;
        else if(sleep==7 || sleep==8)
            sleepScore = 20;
        else if(sleep==9)
            sleepScore = 16;
        else
            sleepScore = 12;

        if(cardio<20)
            cardioScore = 5;
        else if(cardio>20 && cardio<=40)
            cardioScore = 16;
        else if(cardio<40 && cardio>=100)
            cardioScore = 20;
        else
            cardioScore = 18;

        if(alcohol==0)
            alcoholScore = 20;
        else if(alcohol==1)
            alcoholScore = 14;
        else if(alcohol==2)
            alcoholScore = 8;
        else
            alcoholScore = 0;

        if(tobacco==0)
            tobaccoScore = 20;
        else if(tobacco==1)
            tobaccoScore = 14;
        else if(tobacco==2)
            tobaccoScore = 8;
        else
            tobaccoScore = 0;

        if(disease==0)
            diseaseScore = 20;
        else if(disease==1)
            diseaseScore = 18;
        else if(disease==2)
            diseaseScore = 14;
        else if(disease==3)
            diseaseScore = 10;
        else if(disease==4)
            diseaseScore=5;
        else
            diseaseScore = 2;

        totalScore = ageScore+sleepScore+cardioScore+alcoholScore+tobaccoScore+diseaseScore;
        return totalScore;

    }

    /*public String generateReport(int score){
        String res;
        if(score>=90 && score<=120){
            res = "Reduce tobacco even if you are a level 1 smoker\n"+
                    "Alcohol does have benefits, keep it to twice a week at max\n"+
                    "Exercise level is great, keep it up\n"+
                    "Have more protein to help build muscle\n"+
                    "Sleeping for 7 to 8 hours is good ";
        }
        else if(score>=70 && score<=89){
            res = "Reduce tobacco level, use e-cigarettes\n"+
                    "Alcohol does have benefits, keep it to twice a week at max\n"+
                    "Need to exercise for about 30 - 60 mins a day \n"+
                    "Watch food intake, have small meals 5-6 times a day\n"+
                    "Sleeping for 7 to 8 hours is important";
        }
        else{
            res = "Need to reduce tobacco level\n"+
                    "Alcohol does have benefits, keep it to twice a week at max\n"+
                    "Need to exercise for about 30 - 60 mins a day\n"+
                    "Watch food intake, have small meals 5-6 times a day\n"+
                    "Reduce sugar and oil intake\n"+
                    "Sleeping for 7 to 8 hours is important\n";
        }

        return res;
    }*/

    public String generateReport(int alcohol, int tobacco, int disease){

        String res = "";

        if (alcohol == 0) {
            if (tobacco == 0) {
                if (disease == 0)
                    res = "Living a healthy lifestyle. Continue exercising and eating healthy.";
                else if (disease == 1)
                    res = "Always carry the inhaler and a mask.";
                else if (disease == 2)
                    res = "Weight loss is crutial and exercise everyday for a minimum of 1 hour. Drink green tea. Avoid oily and sugar based foods. Reduce salt in take also.";
                else if (disease == 3)
                    res = "Gastrointestinal problems are difficult to handle. Drink camomile tea to relax the muscles. Soft, less oil, no deep fried is recommended. Yoga is a good form of exercise to keep the gut healthy";
                else if (disease == 4)
                    res = "Diabetes is a serious disease if not treated properly. Always check 3 months average and keep it below 7. Brisk walking is recommended for atleast an hour daily. Replace sugar with jaggery to handle sweet cravings";
                else
                    res = "Exercise everyday. Check ECG every 3-6 months.";

            }
            else if (tobacco == 1 || tobacco == 2 || tobacco == 3) {
                if (disease == 0)
                    res = "Living a healthy lifestyle yet you need to stop smoking. Continue exercising and eating healthy.";
                else if (disease == 1)
                    res = "Always carry the inhaler and a mask. Smoking kills the lungs.";
                else if (disease == 2)
                    res = "Smoking restricts a person from losing weight. Stop smoking. Weight loss is crutial and exercise everyday for a minimum of 1 hour. Drink green tea. Avoid oily and sugar based foods. Reduce salt in take also.";
                else if (disease == 3)
                    res = "Gastrointestinal problems are difficult to handle. Drink camomile tea to relax the muscles. Soft, less oil, no deep fried is recommended. Yoga is a good form of exercise to keep the gut healthy. Smoking needs to slowly cut down and completely stopped soon.";
                else if (disease == 4)
                    res = "Diabetes is a serious disease if not treated properly. Always check 3 months average and keep it below 7. Brisk walking is recommended for atleast an hour daily. Replace sugar with jaggery to handle sweet cravings.Smoking needs to slowly cut down and completely stopped soon.";
                else
                    res = "Exercise everyday. Check ECG every 3-6 months. Smoking needs to slowly cut down and completely stopped soon.";
            }
        }
        else if (alcohol == 1 || alcohol == 2 || alcohol == 3) {
            if (tobacco == 0) {
                if (disease == 0)
                    res = "Living a healthy lifestyle. Continue exercising and eating healthy. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max.";
                else if (disease == 1)
                    res = "Always carry the inhaler and a mask. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max.";
                else if (disease == 2)
                    res = "Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Drinking a lot of alcohol causes a person to have a fat belly which makes it harder to lose weight. Weight loss is crutial and exercise everyday for a minimum of 1 hour. Drink green tea. Avoid oily and sugar based foods. Reduce salt in take also.";
                else if (disease == 3)
                    res = "Gastrointestinal problems are difficult to handle. Drink camomile tea to relax the muscles. Soft, less oil, no deep fried is recommended. Yoga is a good form of exercise to keep the gut healthy. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Beer is good for digestion issues.";
                else if (disease == 4)
                    res = "Diabetes is a serious disease if not treated properly. Always check 3 months average and keep it below 7. Brisk walking is recommended for atleast an hour daily. Replace sugar with jaggery to handle sweet cravings. Drinking Alcohol has many health benefits, but not for a diabetic as it raises the sugar levels.";
                else
                    res = "Exercise everyday. Check ECG every 3-6 months. Smoking needs to slowly cut down and completely stopped soon. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Drinking 2 glasses of red wine a week is healthy";
            }
            else if (tobacco == 1 || tobacco == 2 || tobacco == 3) {
                if (disease == 0)
                    res = "Living a healthy lifestyle. Continue exercising and eating healthy. Drinking Alcohol has many health benefits, but anything is excess is bad. Smoking kills hence the problem may have not set in yet but dont let it to either. Limit to 2 drinks a week at max.";
                else if (disease == 1)
                    res = "Always carry the inhaler and a mask. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max.Smoking kills the lungs.";
                else if (disease == 2)
                    res = "Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Drinking a lot of alcohol causes a person to have a fat belly which makes it harder to lose weight. Weight loss is crutial and exercise everyday for a minimum of 1 hour. Drink green tea. Avoid oily and sugar based foods. Reduce salt in take also.Smoking restricts a person from losing weight. Stop smoking";
                else if (disease == 3)
                    res = "Gastrointestinal problems are difficult to handle. Drink camomile tea to relax the muscles. Soft, less oil, no deep fried is recommended. Yoga is a good form of exercise to keep the gut healthy. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Beer is good for digestion issues.Smoking needs to slowly cut down and completely stopped soon.";
                else if (disease == 4)
                    res = "Diabetes is a serious disease if not treated properly. Always check 3 months average and keep it below 7.  Brisk walking is recommended for atleast an hour daily. Replace sugar with jaggery to handle sweet cravings.Smoking needs to slowly cut down and completely stopped soon. Drinking Alcohol has many health benefits, but not for a diabetic as it raises the sugar levels.Smoking needs to slowly cut down and completely stopped soon.";
                else
                    res = "Exercise everyday. Check ECG every 3-6 months. Smoking needs to slowly cut down and completely stopped soon. Drinking Alcohol has many health benefits, but anything is excess is bad. Limit to 2 drinks a week at max. Drinking 2 glasses of red wine a week is healthy. Smoking needs to slowly cut down and completely stopped soon.";
            }
        }
        return res;
    }

    public void viewAll() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData(editName.getText().toString());
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        //int finalscore;
                        //finalscore = Integer.parseInt(res.getString(9));

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Email ID :"+ res.getString(1)+"\n");
                            buffer.append("Blood Group:"+res.getString(3)+"\n");
                            buffer.append("Score:"+res.getString(9)+"\n");
                            //buffer.append("Report :"+ generateReport(res.getInt(9))+"\n");
                            buffer.append("Report:"+ generateReport(res.getInt(6), res.getInt(7), res.getInt(8)));

                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
}
