package com.hudson.mindfill.lib;

import java.util.ArrayList;

/**
 * Created by hudsonhughes on 8/21/16.
 */
public class CustomTiles {
    public ArrayList<CustomTile> arrayList = new ArrayList<>();
    CustomTiles(ArrayList<CustomTile> arrayList){
        this.arrayList = arrayList;
    }
    CustomTiles(){}

    public void add(CustomTile customTile){
        arrayList.add(customTile);
    }
    public void removeObj(CustomTile customTile){
        arrayList.remove(customTile);
    }
    public void remove(int index){
        arrayList.remove(index);
    }
    public void set(int input, CustomTile fresh){
        arrayList.set(input, fresh);
    }
    public CustomTile get(int input){
        return arrayList.get(input);
    }
    public int size(){
        return arrayList.size();
    }

}
