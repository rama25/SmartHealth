package com.example.rama.smarthealth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Rama on 24-11-2017.
 */

public class DietPlan extends AppCompatActivity implements View.OnClickListener {

    private EditText etHeight;
    private EditText etWeight;
    private Button bDiet;
    private TextView etOutput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        etHeight = (EditText)findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        bDiet = (Button) findViewById(R.id.bDiet);
        etOutput = (TextView) findViewById(R.id.etOutput);

        bDiet.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bDiet:
                getDietPlan();
                break;
        }
    }

    private void getDietPlan() {
        double h = Double.parseDouble(etHeight.getText().toString());
        double w = Double.parseDouble(etWeight.getText().toString());
        double bmi = w / (h * h);
        String res = "";
        if (bmi < 19) {
            // UnderWeight
            res = "You are Underweight\n\n" +
                    "Breakfast - Fruit yogurt and fruit juice\n" +
                    "Lunch - Grilled chicken and chocolate milkshake\n" +
                    "Snacks - Wheat bread with turkey breast and full calorie dairy products.\n" +
                    "Dinner - Add vegetables like French beans, broccoli, Chinese cabbage, carrots, lettuce, spinach, asparagus, pumpkins, and eggplants to your diet.\n" +
                    "You can even add olive oil in generous quantities to your salads.\n" +
                    "\n";
        }
        else if (bmi < 25) {
            // Normal
            res = "You are healthy\n\n" +
                    "Breakfast - Fill up your breakfast meal with protein like 2-3 scrambled egg whites with a whole grain toast and a fruit of your choice or a bowl of fruit oats porridge with sprouts salad. Protein kick-starts your metabolism and keeps you feeling fuller for longer during the day.\n" +
                    "\n" +
                    "Lunch - Choose carb-rich foods that will supply energy and protein like a bowl of dal/chicken/fish curry with brown rice or roti and a veg salad. You could also include beans or chicken sandwich with plenty of veggies.\n" +
                    "\n" +
                    "Snacks - Having a snack will keep your energy levels up and you can choose from a variety of nourishing options like Apple cinnamon granola bar or Nature Valley’s granola bar or you can have a fistful of nuts.\n" +
                    "\n" +
                    "Dinner - The supper of the day should be a combination of protein and carbs. You can include oily fish which are rich in omega 3 fatty acids like mackerel, salmon which is beneficial for healthy hair and skin. Fill up your dinner bowl with a variety of colorful veggies. Chicken/fish/paneer with roti and quinoa preparations and soup or salad with veggies.\n" +
                    "\n";
        }
        else if (bmi < 30) {
            // Over
            res = "You are Overweight\n\n" +
                    "Breakfast - Omelette made with three egg whites and filled with 75g chopped mixed peppers and a handful of spinach\n" +
                    "\n" +
                    "Lunch - One grilled chicken breast, mixed salad leaves, red peppers, green beans and ¼ tbsp olive oil\n" +
                    "\n" +
                    "Snacks - 100g turkey breast with ¼ cucumber, sliced\n" +
                    "\n" +
                    "Dinner - 100g grilled chicken breast with steamed broccoli\n" +
                    "\n";
        }
        else {
            // Obese
            // Need to fill
            res = "You are Obese";
        }
        etOutput.setMovementMethod(new ScrollingMovementMethod());
        etOutput.setText(res);
    }
}
