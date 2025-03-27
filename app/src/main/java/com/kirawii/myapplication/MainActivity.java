package com.kirawii.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText weightEditText, heightEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightText = weightEditText.getText().toString().trim();
        String heightText = heightEditText.getText().toString().trim();

        if (weightText.isEmpty() || heightText.isEmpty()) {
            resultTextView.setText("请输入体重和身高");
            return;
        }

        try {
            double weight = Double.parseDouble(weightText);
            double height = Double.parseDouble(heightText);


            double bmi = weight / (height * height);
            String healthAdvice = getHealthAdvice(bmi);

            resultTextView.setText(String.format("您的 BMI: %.2f\n%s", bmi, healthAdvice));
        } catch (NumberFormatException e) {
            resultTextView.setText("输入格式错误，请输入有效数字");
        }
    }
    private String getHealthAdvice(double bmi) {
        if (bmi < 18.5) {
            return "您属于 偏瘦，建议加强营养，多吃高蛋白食物，并适量锻炼。";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "您的体重 正常，请保持良好的饮食和运动习惯。";
        } else if (bmi >= 24.9 && bmi < 29.9) {
            return "您属于 超重，建议控制饮食，减少高热量摄入，并加强锻炼。";
        } else {
            return "您属于 肥胖，建议调整饮食结构，增加运动量，并关注健康指标。";
        }
    }
}
