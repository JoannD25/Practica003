package inicio.fisei.practica_003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;

    private EditText $editTextAmount;
    private TextView $textViewPercent;
    private TextView $textViewTip;
    private TextView $textViewTotal;

    private SeekBar $seekBar;
    private Button $buttonClear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        $editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        $textViewPercent = (TextView) findViewById(R.id.textViewPercent);
        $textViewTip = (TextView) findViewById(R.id.textViewTip);
        $textViewTotal = (TextView) findViewById(R.id.textViewTotal);

        $seekBar = (SeekBar) findViewById(R.id.seekBar);
        $buttonClear = findViewById(R.id.buttonClear);

        $buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        $seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent = progress / 100.0;
                calculateBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        $editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    if (s.charAt(0) == ',' || s.charAt(0) == '.') {
                        showError("Error");
                        clear();
                    } else {
                        billAmount = Double.parseDouble(s.toString());
                        calculateBill();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showError(String aviso) {
        Toast.makeText(this, aviso, Toast.LENGTH_SHORT).show();
    }

    private void calculateBill() {
        $textViewPercent.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;

        $textViewTip.setText(currencyFormat.format(tip).toString());
        $textViewTotal.setText(currencyFormat.format(total).toString());
    }

    private void clear() {
        billAmount = 0;
        $editTextAmount.setText("");
        $textViewTip.setText("");
        $textViewTotal.setText("");
    }

}