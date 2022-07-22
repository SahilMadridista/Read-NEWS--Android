package com.example.readnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

   String API_KEY = "639d710d5be1491489aa746f1b22251b";
   ArrayList<ModelClass> modelClassArrayList;
   Adapter adapter;
   String country = "in";
   private RecyclerView recyclerViewHome;

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {

      View v = inflater.inflate(R.layout.homefragment,null);

      recyclerViewHome = v.findViewById(R.id.recyclerviewhome);
      modelClassArrayList = new ArrayList<>();
      recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter = new Adapter(getContext(),modelClassArrayList);
      recyclerViewHome.setAdapter(adapter);

      findNews();

      return v;
   }

   private void findNews() {

      ApiUtilities.getApiInterface().getNews(country,100,API_KEY).enqueue(new Callback<MainNews>() {
         @Override
         public void onResponse(Call<MainNews> call, Response<MainNews> response) {

            if(response.isSuccessful()){
               modelClassArrayList.addAll(response.body().getArticles());
               adapter.notifyDataSetChanged();
            }

         }

         @Override
         public void onFailure(Call<MainNews> call, Throwable t) {

         }
      });

   }
}
