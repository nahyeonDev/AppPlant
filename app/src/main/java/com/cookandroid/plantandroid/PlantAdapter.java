package com.cookandroid.plantandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private ArrayList<ListItemObj> pData = null;

    public PlantAdapter(ArrayList<ListItemObj> data) {
        pData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_recycler,parent,false);
        PlantAdapter.ViewHolder vh = new PlantAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder holder, int position) {
        ListItemObj item = pData.get(position);

        holder.btn.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    //아이템 뷰 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btn;

        ViewHolder(View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.button);

        }
    }
}
