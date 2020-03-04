package by.bsuir.podgornaya.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    public ArrayList<String> result;
    public ArrayList<String> example;
    public OnItemClickListener itemClickListener;

    //----------------------------------------------------------------------------------------------
    public MyAdapter(ArrayList<String> result,  ArrayList<String> example, OnItemClickListener itemClickListener)
    {
        this.result = result;
        this.example = example;
        this.itemClickListener = itemClickListener;
    }
    //----------------------------------------------------------------------------------------------
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,
                viewGroup,false);
        return new ViewHolder(v);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.itemResult.setText(result.get(i));
        viewHolder.itemExample.setText(result.get(i));
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public int getItemCount()
    {
        return result.size();
    }
    //----------------------------------------------------------------------------------------------
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView itemResult;
        private TextView itemExample;

        private ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemResult = itemView.findViewById(R.id.resultField);
            itemExample = itemView.findViewById(R.id.exampleField);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            itemClickListener.onItemClick(v, getLayoutPosition(), false, result, example);
        }
    }
    public interface OnItemClickListener
    {
        void onItemClick(View v, int position, boolean isLongClick, ArrayList<String> result, ArrayList<String> example);
    }
    //----------------------------------------------------------------------------------------------
}
