package by.bsuir.podgornaya.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLog";

    TextView resultField;
    EditText exampleField;

    Button openBracket, closeBracket, percent, delete, number_1, number_2, number_3, number_4, number_5, number_6,
            number_7, number_8, number_9, number_0, division, multiplication, sum, difference, point, result,
            tan, sin, cos, ln, lg, factorial, pi, exponentE, power, sqrt;


    View.OnClickListener buttonWork;
    View.OnLongClickListener buttonWorkLong;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.menu_history:
                Intent intentHistory = new Intent(this, HistoryActivity.class);
                startActivity(intentHistory);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Связываем переменные с кнопочками");

        //Создаём объект тулбар и находим его по ID
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Устанавливаем его на экран
        setSupportActionBar(toolbar);

        number_1 = findViewById(R.id.number_1);
        number_2 = findViewById(R.id.number_2);
        number_3 = findViewById(R.id.number_3);
        number_4 = findViewById(R.id.number_4);
        number_5 = findViewById(R.id.number_5);
        number_6 = findViewById(R.id.number_6);
        number_7 = findViewById(R.id.number_7);
        number_8 = findViewById(R.id.number_8);
        number_9 = findViewById(R.id.number_9);
        number_0 = findViewById(R.id.number_0);

        resultField = findViewById(R.id.resultField);
        exampleField = findViewById(R.id.exampleField);

        exampleField.setMovementMethod(new ScrollingMovementMethod());

        division = findViewById(R.id.division);
        multiplication = findViewById(R.id.multiplication);
        sum = findViewById(R.id.sum);
        difference = findViewById(R.id.difference);

        point = findViewById(R.id.point);
        result = findViewById(R.id.result);
        openBracket = findViewById(R.id.openSkob);
        closeBracket = findViewById(R.id.closeSkob);
        percent = findViewById(R.id.persent);
        delete = findViewById(R.id.delete);
        tan = findViewById(R.id.tan);
        cos = findViewById(R.id.cos);
        sin = findViewById(R.id.sin);
        ln = findViewById(R.id.ln);
        lg = findViewById(R.id.lg);
        factorial = findViewById(R.id.factorial);
        exponentE = findViewById(R.id.exponentE);
        power = findViewById(R.id.power);
        sqrt = findViewById(R.id.sqrt);
        pi = findViewById(R.id.pi);

        final String[] exampleNow = {""};

        Log.d(TAG, "Связали!");

        buttonWork = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.openSkob:
                        exampleField.setText(exampleNow[0] + "(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.closeSkob:
                        exampleField.setText(exampleNow[0] + ")");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.persent:
                        exampleField.setText(exampleNow[0] + "%");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.delete:
                        if (exampleNow[0] == "") {
                        } else
                            try {
                                exampleField.setText(exampleNow[0].substring(0, exampleNow[0].length() - 1));
                                exampleNow[0] = exampleField.getText().toString();
                                break;
                            } catch (Exception ex) {
                                resultField.setText("Неверное выражение");
                            }
                        break;
                    case R.id.number_1:
                        exampleField.setText(exampleNow[0] + "1");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_2:
                        exampleField.setText(exampleNow[0] + "2");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_3:
                        exampleField.setText(exampleNow[0] + "3");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_4:
                        exampleField.setText(exampleNow[0] + "4");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_5:
                        exampleField.setText(exampleNow[0] + "5");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_6:
                        exampleField.setText(exampleNow[0] + "6");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_7:
                        exampleField.setText(exampleNow[0] + "7");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_8:
                        exampleField.setText(exampleNow[0] + "8");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_9:
                        exampleField.setText(exampleNow[0] + "9");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.number_0:
                        exampleField.setText(exampleNow[0] + "0");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.point:
                        exampleField.setText(exampleNow[0] + ".");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.sum:
                        exampleField.setText(exampleNow[0] + "+");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.difference:
                        exampleField.setText(exampleNow[0] + "-");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.division:
                        exampleField.setText(exampleNow[0] + "/");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.multiplication:
                        exampleField.setText(exampleNow[0] + "*");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.sin:
                        exampleField.setText(exampleNow[0] + "sin(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.cos:
                        exampleField.setText(exampleNow[0] + "cos(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.tan:
                        exampleField.setText(exampleNow[0] + "tan(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.factorial:
                        exampleField.setText(exampleNow[0] + "fact(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.ln:
                        exampleField.setText(exampleNow[0] + "log(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.lg:
                        exampleField.setText(exampleNow[0] + "log10(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.exponentE:
                        exampleField.setText(exampleNow[0] + "e");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.power:
                        exampleField.setText(exampleNow[0] + "^");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.sqrt:
                        exampleField.setText(exampleNow[0] + "sqrt(");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.pi:
                        exampleField.setText(exampleNow[0] + "pi");
                        exampleNow[0] = exampleField.getText().toString();
                        break;
                    case R.id.result:
                        if (exampleNow[0] == "") {
                        } else
                            try {
                                BigDecimal calc;
                                calc = new Expression(exampleNow[0]).eval();
                                resultField.setText(calc.toString());
                                //добавление в RecyclerView
                                break;
                            } catch (Exception ex) {
                                resultField.setText("Неверное выражение");
                            }
                }
            }
        };

        buttonWorkLong = new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.delete: {
                        if (exampleNow[0] == "") {
                            resultField.setText("");
                        } else {
                            exampleNow[0] = "";
                            exampleField.setText(exampleNow[0]);
                            resultField.setText(exampleNow[0]);
                        }
                    }
                    case R.id.result: {
                        if (exampleNow[0] == "") {
                            resultField.setText("");
                        } else {
                            try {
                                BigDecimal calc;
                                calc = new Expression(exampleNow[0]).eval();
                                resultField.setText(calc.toString());
                                exampleNow[0] = calc.toString();
                                exampleField.setText(exampleNow[0]);
                                break;
                            } catch (Exception ex) {
                                resultField.setText("Неверное выражение");
                            }
                        }
                    }
                }
                return true;
            }
        };

        Log.d(TAG, "Присваиваем кнопочкам обработчики");

        number_1.setOnClickListener(buttonWork);
        number_2.setOnClickListener(buttonWork);
        number_3.setOnClickListener(buttonWork);
        number_4.setOnClickListener(buttonWork);
        number_5.setOnClickListener(buttonWork);
        number_6.setOnClickListener(buttonWork);
        number_7.setOnClickListener(buttonWork);
        number_8.setOnClickListener(buttonWork);
        number_9.setOnClickListener(buttonWork);
        number_0.setOnClickListener(buttonWork);
        resultField.setOnClickListener(buttonWork);
        exampleField.setOnClickListener(buttonWork);
        division.setOnClickListener(buttonWork);
        multiplication.setOnClickListener(buttonWork);
        sum.setOnClickListener(buttonWork);
        difference.setOnClickListener(buttonWork);
        point.setOnClickListener(buttonWork);
        result.setOnClickListener(buttonWork);
        delete.setOnClickListener(buttonWork);
        openBracket.setOnClickListener(buttonWork);
        closeBracket.setOnClickListener(buttonWork);
        percent.setOnClickListener(buttonWork);
        delete.setOnClickListener(buttonWork);
        delete.setOnLongClickListener(buttonWorkLong);
        result.setOnLongClickListener(buttonWorkLong);

        Log.d(TAG, "Присвоили!");

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            sin.setOnClickListener(buttonWork);
            cos.setOnClickListener(buttonWork);
            tan.setOnClickListener(buttonWork);
            ln.setOnClickListener(buttonWork);
            lg.setOnClickListener(buttonWork);
            factorial.setOnClickListener(buttonWork);
            exponentE.setOnClickListener(buttonWork);
            sqrt.setOnClickListener(buttonWork);
            power.setOnClickListener(buttonWork);
            pi.setOnClickListener(buttonWork);
        }
    }
}