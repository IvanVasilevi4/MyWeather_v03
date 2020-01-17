package com.ivanvasilevich.myweather_v03;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class WeatherSettingsFragment extends Fragment {
    boolean isExistCity;
    int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_settings, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isExistCity = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }
        if (isExistCity) {
            showList();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void showList() {
        if (isExistCity) {
            ListOfCitiesFragment detail = (ListOfCitiesFragment)
                    getFragmentManager().findFragmentById(R.id.list);
            if (detail == null || detail.getIndex() != currentPosition) {
                detail = ListOfCitiesFragment.create(currentPosition);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.list, detail); // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent(getActivity(), ListOfCitiesActivity.class);
            intent.putExtra("index", currentPosition);
            startActivity(intent);
        }
    }

}
