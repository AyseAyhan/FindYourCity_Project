package com.example.findyourcity;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends ArrayAdapter<CityModel> {
    private List<CityModel> originalList;
    private List<CityModel> filteredList;

    public CityListAdapter(Context context, List<CityModel> cityList) {
        super(context, android.R.layout.simple_list_item_1, cityList);
        this.originalList = new ArrayList<>(cityList);
        this.filteredList = new ArrayList<>(cityList);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public CityModel getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public Filter getFilter() {
        return cityFilter;
    }

    private Filter cityFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = originalList;
                results.count = originalList.size();
            } else {
                List<CityModel> filteredCities = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CityModel city : originalList) {
                    if (city.getName().toLowerCase().contains(filterPattern)) {
                        filteredCities.add(city);   //city.getName() olmalı  //package şeklinde auto texte yazıyor
                    }
                }
                results.values = filteredCities;
                results.count = filteredCities.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList.clear();
            filteredList.addAll((List<CityModel>) results.values);
            notifyDataSetChanged();
        }
    };
}
