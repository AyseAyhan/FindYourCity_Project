package com.example.findyourcity;

import java.util.List;

public class Data {
    private List<CityModel> citylist;

    public List<CityModel> getCities() {
        return citylist;
    }

    public void setCitylist(List<CityModel> cityList) {
        this.citylist = cityList;
    }
}
