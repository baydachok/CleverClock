package com.narko.alarmclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

public class NewRecyclerAdapter extends RecyclerView.Adapter<NewRecyclerAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    NewRecyclerAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    // Нужно будет воспользоваться этой парашей
    public void setItems(Collection<String> dataItems) {
        mData.addAll(dataItems);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = mInflater.inflate(R.layout.alarm_clock_list_item, parent, false);
       return new ViewHolder(view);
    }


//Захуячь цикл, так как выводится только один элемент, а ты понимаешь, что это пиздец полный, ага)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String timeValue = mData.get(position);
        holder.timeValueTextView.setText(timeValue);
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView timeValueTextView;
        private Switch switchAlarmClock;

        ViewHolder(View itemView) {
            super(itemView);
            timeValueTextView = itemView.findViewById(R.id.name);
            //switchAlarmClock = itemView.findViewById(R.id.switcher);
            //switchAlarmClock.setChecked(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    String getItem(int id) {
        return mData.get(id);
    }

// ВОТ ЭТА ПОЕБЕНЬ ДОЛЖГНА ПРИГОДИТСЯ ПРИ РЕАЛИЗАЦИИ НАСТРОЙКИ КОНКРЕТНОГО БУДИЛЬНИКА, РАЗБЕРЁШЬСЯ ПОЗЖЕ

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}

