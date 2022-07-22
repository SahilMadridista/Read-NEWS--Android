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

public class ScienceFragment extends Fragment {

   String API_KEY = "639d710d5be1491489aa746f1b22251b";
   ArrayList<ModelClass> modelClassArrayList;
   Adapter adapter;
   String country = "in";
   private RecyclerView recyclerViewScience;
   private String category = "science";

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {

      View v = inflater.inflate(R.layout.sciencefragment,null);
      recyclerViewScience = v.findViewById(R.id.recyclerviewscience);
      modelClassArrayList = new ArrayList<>();
      recyclerViewScience.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter = new Adapter(getContext(),modelClassArrayList);
      recyclerViewScience.setAdapter(adapter);

      findNews();

      return v;
   }

   private void findNews() {

      ApiUtilities.getApiInterface().getCategoryNews(country,category,100,API_KEY)
              .enqueue(new Callback<MainNews>() {
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
