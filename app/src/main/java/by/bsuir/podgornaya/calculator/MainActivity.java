package by.bsuir.podgornaya.calculator;

import android.content.Intent; //подключаю, т.к. передаю данные в другую активити (из основной странички калькулятора в страничку с историей)
import android.content.res.Configuration;
import android.os.Bundle; //подключаю, т.к. сохраняю свои данные в объекте типа Bundle
import android.util.Log; //пыталась проверить на каком моменте ломается код
import android.view.Menu; //для меню
import android.view.MenuInflater; //для того, чтобы "надуть" меню пунктами
import android.view.MenuItem; // для того, чтобы написать обработчик нажатий на пункты меню
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.udojava.evalex.Expression; //для математических расчетов
import java.math.BigDecimal; //для математических расчетов
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLog"; //попытка отследить где ломается приложение

    //объявление переменных
    String[] exampleNow = {""}; //String[], потому что у меня что-то не работало и предложило автоматически заменить String на String[]

    EditText resultField; //EditText почему - тоже не помню, давно писала приложение
    EditText exampleField; //возможно, для того, чтобы сделать свайп, когда строка не влазит в границы экрана

    Button openBracket, closeBracket, percent, delete, number_1, number_2, number_3, number_4, number_5, number_6,
            number_7, number_8, number_9, number_0, division, multiplication, sum, difference, point, result,
            tan, sin, cos, ln, lg, factorial, pi, exponentE, power, sqrt;

    View.OnClickListener buttonWork; //краткий клик для всех кнопок
    View.OnLongClickListener buttonWorkLong; //долгий клик для кнопок результата и очищения

    ArrayList<String> resultList = new ArrayList<>(); //для последующей передачи данных в историю
    ArrayList<String> exampleList = new ArrayList<>(); //для последующей передачи данных в историю

    //сохраняет состояние активити (для перехода к другой ориентации экрана)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("keyForDataAfterOrient", exampleNow[0]);
        super.onSaveInstanceState(outState);
    }

    //восстаналивает состояние активити (после перехода к другой ориентации экрана)
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        exampleNow[0] = savedInstanceState.getString("keyForDataAfterOrient", exampleNow[0]);
    }

    //создание меню опций
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu); //"надуваем" меню данными из разметки
        return true;
    }

    //обработчик нажатий на пункты меню опций
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                //создадим намерение intent и укажем из какого какое активити открыть (явный вызов intent)
                Intent intentAbout = new Intent(this, AboutActivity.class);
                //запуск intent-a
                startActivity(intentAbout);
                break;
            case R.id.menu_history:
                Intent intentHistory = new Intent(this, HistoryActivity.class);
                Bundle bundle = new Bundle(); //создать объект типа Bundle для того, чтобы запихнуть сюда данные для передачи на другой активити
                bundle.putStringArrayList("resultKey", resultList);
                bundle.putStringArrayList("exampleKey", exampleList);
                intentHistory.putExtras(bundle); //передаем в intent данные, сохраненные в Bundle
                startActivity(intentHistory, bundle); //запускаем другую активити и передаем туда данные в Bundle
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //задать макет в кач-ве пользовательского интерфейса операции, передается в него идентификатор ресурса макета

        Log.d(TAG, "Связываем переменные с кнопочками");

        //Создаём объект тулбар и находим его по ID
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Устанавливаем его на экран
        setSupportActionBar(toolbar);

        //сейчас будем привязывать к переменным соответствующие id, чтобы работать с объектами разметки через java-код
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

        Log.d(TAG, "Связали!");

        //обработчик коротких нажатий на кнопки разметки
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
                                //для RecycleView
                                resultList.add(resultField.getText().toString());
                                exampleList.add(exampleNow[0]);
                                break;
                            } catch (Exception ex) {
                                resultField.setText("Неверное выражение");
                                System.out.println(ex.getMessage());
                            }
                }
            }
        };

        //обработчик долгих нажатий на кнопки разметки
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

        //присвоить соответствущим кнопкам обработчики нажатий
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
