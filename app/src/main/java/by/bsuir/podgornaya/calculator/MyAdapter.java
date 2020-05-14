package by.bsuir.podgornaya.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ResultExample> resultExampleList;
    private Context context;

    public List<ResultExample> getResultExampleList() {
        return resultExampleList;
    }

    public void setResultExampleList(List<ResultExample> resultExampleList) {
        this.resultExampleList = resultExampleList;
    }

    MyAdapter(Context context, List<ResultExample> resultExampleList) {
        this.resultExampleList = resultExampleList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        ResultExample resultExample = resultExampleList.get(position);
        holder.resultView.setText(resultExample.getResult());
        holder.exampleView.setText(resultExample.getExample());
    }

    @Override
    public int getItemCount() {
        return resultExampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView resultView, exampleView;
        //LinearLayout linearLayout;
        ViewHolder(View view){
            super(view);
            //linearLayout = view.findViewById(R.id.recycler_item_linearlayout);
            resultView = (TextView) view.findViewById(R.id.resultRecycle);
            exampleView = (TextView) view.findViewById(R.id.exampleRecycle);
            //linearLayout.setOnClickListener(this);
        }

       /* @Override
        public void onClick(View v) {
            Intent intentHistory = new Intent(context, MainActivity.class);
            // Bundle bundle = new Bundle();
            //bundle.putStringArrayList("resultKey", resultList);
            // bundle.putStringArrayList("exampleKey", exampleList);
            // intentHistory.putExtras(bundle);
            context.startActivity(intentHistory);
        }*/
    }

}