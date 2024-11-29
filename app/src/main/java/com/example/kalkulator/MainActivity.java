package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Deklaracje widoków
    private EditText cyfra1, cyfra2, wynik;
    private Button buttonDodawanie, buttonOdejmowanie, buttonMnozenie, buttonDzielenie;
    private Button buttonPierwiastek, buttonPotega, buttonSilnia;
    private Button buttonRozszerzenie, buttonPowrot;
    private LinearLayout rozszerzoneFunkcjeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja widoków
        cyfra1 = findViewById(R.id.cyfra1);
        cyfra2 = findViewById(R.id.drugaCyfra);
        wynik = findViewById(R.id.wynik);

        buttonDodawanie = findViewById(R.id.buttonDodawanie);
        buttonOdejmowanie = findViewById(R.id.buttonOdejmowanie);
        buttonMnozenie = findViewById(R.id.buttonMnozenie);
        buttonDzielenie = findViewById(R.id.buttonDzielenie);

        buttonPierwiastek = findViewById(R.id.buttonPierwiastek);
        buttonPotega = findViewById(R.id.buttonPotega);
        buttonSilnia = findViewById(R.id.buttonSilnia);

        buttonRozszerzenie = findViewById(R.id.Rozszerzenie);
        buttonPowrot = findViewById(R.id.buttonPowrot);

        rozszerzoneFunkcjeLayout = findViewById(R.id.rozszerzoneFunkcjeLayout);

        // Ukryj rozszerzone funkcje na starcie
        rozszerzoneFunkcjeLayout.setVisibility(View.GONE);

        // Obsługa podstawowych operacji
        buttonDodawanie.setOnClickListener(v -> wykonajDzialanie("+"));
        buttonOdejmowanie.setOnClickListener(v -> wykonajDzialanie("-"));
        buttonMnozenie.setOnClickListener(v -> wykonajDzialanie("*"));
        buttonDzielenie.setOnClickListener(v -> wykonajDzialanie("/"));

        // Obsługa rozszerzonych funkcji
        buttonPierwiastek.setOnClickListener(v -> wykonajPierwiastek());
        buttonPotega.setOnClickListener(v -> wykonajPotegowanie());
        buttonSilnia.setOnClickListener(v -> wykonajSilnie());

        // Przełączanie między podstawowymi i rozszerzonymi funkcjami
        buttonRozszerzenie.setOnClickListener(v -> {
            rozszerzoneFunkcjeLayout.setVisibility(View.VISIBLE);
            buttonRozszerzenie.setVisibility(View.GONE);
        });

        buttonPowrot.setOnClickListener(v -> {
            rozszerzoneFunkcjeLayout.setVisibility(View.GONE);
            buttonRozszerzenie.setVisibility(View.VISIBLE);
        });
    }

    // Funkcja do wykonania podstawowych działań
    private void wykonajDzialanie(String dzialanie) {
        try {
            double liczba1 = Double.parseDouble(cyfra1.getText().toString());
            double liczba2 = Double.parseDouble(cyfra2.getText().toString());
            double result;

            switch (dzialanie) {
                case "+":
                    result = liczba1 + liczba2;
                    break;
                case "-":
                    result = liczba1 - liczba2;
                    break;
                case "*":
                    result = liczba1 * liczba2;
                    break;
                case "/":
                    if (liczba2 == 0) {
                        wynik.setText("Nie dziel przez zero!");
                        return;
                    }
                    result = liczba1 / liczba2;
                    break;
                default:
                    wynik.setText("Błąd działania!");
                    return;
            }
            wynik.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            wynik.setText("Podaj poprawne liczby!");
        }
    }

    // Funkcja do obliczania pierwiastka
    private void wykonajPierwiastek() {
        try {
            double liczba = Double.parseDouble(cyfra1.getText().toString());
            if (liczba < 0) {
                wynik.setText("Nie można pierwiastkować liczby ujemnej!");
                return;
            }
            double result = Math.sqrt(liczba);
            wynik.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            wynik.setText("Podaj poprawną liczbę!");
        }
    }

    // Funkcja do obliczania potęgi
    private void wykonajPotegowanie() {
        try {
            double liczba1 = Double.parseDouble(cyfra1.getText().toString());
            double liczba2 = Double.parseDouble(cyfra2.getText().toString());
            double result = Math.pow(liczba1, liczba2);
            wynik.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            wynik.setText("Podaj poprawne liczby!");
        }
    }

    // Funkcja do obliczania silni
    private void wykonajSilnie() {
        try {
            int liczba = Integer.parseInt(cyfra1.getText().toString());
            if (liczba < 0) {
                wynik.setText("Silnia z liczby ujemnej nie istnieje!");
                return;
            }
            int result = 1;
            for (int i = 1; i <= liczba; i++) {
                result *= i;
            }
            wynik.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            wynik.setText("Podaj poprawną liczbę!");
        }
    }
}