package com.example.asus.sampleblki.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.sampleblki.Adapter.BeritaAdapter;
import com.example.asus.sampleblki.Model.Berita;
import com.example.asus.sampleblki.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentOne extends Fragment {

    public FragmentOne(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.recycler_view_item, container, false);

        ArrayList<Berita> berita = new ArrayList<>();

        berita.add(new Berita(R.drawable.rm, "Ceritanya Judul Berita", "Tanggal Beritanya"));
        berita.add(new Berita(R.drawable.rm, "Ceritanya Judul Berita", "Tanggal Beritanya"));
        berita.add(new Berita(R.drawable.rm, "Ceritanya Judul Berita", "Tanggal Beritanya"));
        berita.add(new Berita(R.drawable.rm, "Ceritanya Judul Berita", "Tanggal Beritanya"));

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rvItem);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        BeritaAdapter adapter = new BeritaAdapter(getActivity(), berita);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
