package com.example.foreignerloginreg;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOForeigner {

    private DatabaseReference databaseReference;
    public DAOForeigner(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Foreigner.class.getSimpleName());
    }

    public Task<Void> add(Foreigner fog)
    {
        return databaseReference.push().setValue(fog);
    }
}
