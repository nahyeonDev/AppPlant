package com.cookandroid.plantandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StarBookmarkAdapter extends RecyclerView.Adapter<StarBookmarkAdapter.ViewHolder> {
    private ArrayList<String> question_title=null;

    //아이템 뷰 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView star_question;

        ViewHolder(View itemView) {
            super(itemView);

            star_question=itemView.findViewById(R.id.star_question);
        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음
    public StarBookmarkAdapter(ArrayList<String> list) {
        question_title = list;
    }

    @NonNull
    @Override
    //onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴. viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.question_item_recycler,parent,false);
        StarBookmarkAdapter.ViewHolder vh = new StarBookmarkAdapter.ViewHolder(view);

        return vh;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull StarBookmarkAdapter.ViewHolder holder, int position) {

        String text= question_title.get(position);
        Log.d("hoooooooo",text);

        holder.star_question.setText(text);
        //Log.d("hoooooooo", (String) holder.heart_plantName.getText());

//        ListItemObj item = mData.get(position) ;
//
//        //이름가져오기
//        holder.plant_title.setText(item.getplantName());
//        //holder.btn.setText(item.getPlantName());
//
//        //상세페이지 연결(item_recycler의 리니어레이아웃의 id를 연결해 이미지가 아닌 리스트 어느 곳을 눌러도 넘어갈 수 있음)
//        holder.list_container.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                intent= new Intent(v.getContext(), PlantDetail.class);
//                //식물 이름을 디테일 화면으로 전해서 식물 이름으로 세부화면 내용 받아옴
//                intent.putExtra("title", mData.get(position).getplantName());
//                v.getContext().startActivity(intent);
//                //Toast.makeText(v.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    //전체 아이템 갯수 리턴.
    @Override
    public int getItemCount() {
        return question_title.size();
    }

}
