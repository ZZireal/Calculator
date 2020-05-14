package by.bsuir.podgornaya.calculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<ResultExample> resultExampleList = new ArrayList<>();

    public List<ResultExample> getResultExampleList() {
        return resultExampleList;
    }

    public void setResultExampleList(List<ResultExample> resultExampleList1) {
        this.resultExampleList = resultExampleList1;
    }

    public void setInitialData(){
        resultExampleList.add(new ResultExample("Result 1","Example 1"));
        resultExampleList.add(new ResultExample("Result 2","Example 2"));
        resultExampleList.add(new ResultExample("Result 3","Example 3"));
        resultExampleList.add(new ResultExample("Result 4","Example 4"));
        resultExampleList.add(new ResultExample("Result 5","Example 5"));
    }

    public void addResultExampleList(String result, String example) {
        resultExampleList.add(new ResultExample (result, example));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        addInResultExampleList(intent.getStringArrayListExtra("resultKey"),intent.getStringArrayListExtra("exampleKey"));

        //setInitialData();

        // создаем адаптер
        myAdapter = new MyAdapter(this, resultExampleList);
        // устанавливаем для RecyclerView адаптер
        recyclerView.setAdapter(myAdapter);
    }

    public void addInResultExampleList (ArrayList<String> result, ArrayList<String> example) {
        for (int i = 0; i < result.size(); i++) {
            ResultExample resultExample = new ResultExample(result.get(i), example.get(i));
            if (resultExampleList != null) resultExampleList.add(resultExample);
        }
    }
}

