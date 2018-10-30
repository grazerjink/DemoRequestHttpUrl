package com.kj.demorequesthttpurl.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxGroupModel implements IBaseModel {

    private int id;
    private int type;
    private String name;
    private int value;

    public TaxGroupModel(JSONObject object) {
        try {
            id = object.getJSONObject("data").getInt("id");
            type = object.getJSONObject("data").getInt("type");
            name = object.getJSONObject("data").getString("name");
            value = object.getJSONObject("data").getInt("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TaxGroupModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
