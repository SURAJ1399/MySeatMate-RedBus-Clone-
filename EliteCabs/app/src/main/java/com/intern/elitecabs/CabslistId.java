package com.intern.elitecabs;

import com.google.firebase.firestore.Exclude;

import androidx.annotation.NonNull;


public class CabslistId {


@Exclude
    public String Cablistid;
    public <T extends  CabslistId> T withId(@NonNull final String id){
        this.Cablistid=id;
        return (T)this;
    }
}
