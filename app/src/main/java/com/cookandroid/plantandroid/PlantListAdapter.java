package com.cookandroid.plantandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.PlantListViewHolder>{

    private ArrayList<plantItemList> arrayList;
    private Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public PlantListAdapter(ArrayList<plantItemList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public PlantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_list_item, parent, false);
        PlantListViewHolder holder = new PlantListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantListViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(arrayList.get(position).getMainImg())
                .into(holder.mainImg);
        holder.plantName.setText(arrayList.get(position).getPlantName());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class PlantListViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImg;
        TextView plantName;

        public PlantListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mainImg = itemView.findViewById(R.id.item_image);
            this.plantName = itemView.findViewById(R.id.item_title);
        }
    }
}
