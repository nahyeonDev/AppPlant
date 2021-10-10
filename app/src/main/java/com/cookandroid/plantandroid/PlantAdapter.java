package com.cookandroid.plantandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private ArrayList<ListItemObj> mData = null ;


    //아이템 뷰 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btn;
        ImageView plantImg;
        TextView plant_title;

        ViewHolder(View itemView) {
            super(itemView);

            //btn = itemView.findViewById(R.id.button);
            plant_title=itemView.findViewById(R.id.plant_title);
            plantImg=itemView.findViewById(R.id.plantImg);

        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음
    public PlantAdapter(ArrayList<ListItemObj> data) {
        mData = data;
    }

    @NonNull
    @Override
    //onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴. viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_recycler,parent,false);
        PlantAdapter.ViewHolder vh = new PlantAdapter.ViewHolder(view);

        return vh;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder holder, int position) {

        ListItemObj item = mData.get(position) ;

        holder.plant_title.setText(item.getPlantName());
        //holder.btn.setText(item.getPlantName());

    }

    //전체 아이템 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }


}
