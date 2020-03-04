package by.bsuir.podgornaya.calculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataAdapter adapter;
    List<Phone> phones = new ArrayList<>();

    private void setInitialData(){
        phones.add(new Phone ("Huawei P10", "Huawei", R.drawable.mate8));
        phones.add(new Phone ("Elite z3", "HP", R.drawable.lumia950));
        phones.add(new Phone ("Galaxy S8", "Samsung", R.drawable.galaxys6));
        phones.add(new Phone ("LG G 5", "LG", R.drawable.nexus5x));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        setInitialData();
        // создаем адаптер
        adapter = new DataAdapter(this, phones);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
}

