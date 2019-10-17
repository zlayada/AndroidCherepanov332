package com.netology.androidcherepanov332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner mLanguageText;
    private Button mLanguageBtn;
    private String mLocalLanguage = ""; // Переменная для "флага" локализации

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    // Инициализируем элементы
    public void initViews() {
        mLanguageText = findViewById(R.id.languageText);
        mLanguageBtn = findViewById(R.id.languageBtn);
        initSpinnerLanguage();

        mLanguageBtn.setOnClickListener(new View.OnClickListener() {

            // Обрабатываем нажатие на кнопку
            @Override
            public void onClick(View view) {
                if (!mLocalLanguage.isEmpty()) {
                    Locale locale = new Locale(mLocalLanguage);

                    Configuration config = new Configuration();
                    config.setLocale(locale);

                    getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    recreate();
                }
            }
        });

    }

    // Обрабатываем значения спиннера и записываем флаг для локализации
    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.languageText, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageText.setAdapter(adapterCountries);

        mLanguageText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] languages = getResources().getStringArray(R.array.languageText);
                if (languages[i].equals(getString(R.string.eng))) {
                    mLocalLanguage = "en";
                }

                if (languages[i].equals(getString(R.string.rus))) {
                    mLocalLanguage = "ru";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}