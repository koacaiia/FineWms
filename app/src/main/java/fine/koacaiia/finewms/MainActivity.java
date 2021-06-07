package fine.koacaiia.finewms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<IncargoList> list;
    IncargoListAdapter adapter;
    FirebaseDatabase database;
    String depotName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.main_recyclerview);
        LinearLayoutManager manager=new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(manager);
        database=FirebaseDatabase.getInstance();
        depotName="Incargo2";
        list=new ArrayList<>();
        getIncargoData();
    }

    private void getIncargoData() {
        Log.i("duatjsrb","Firebase Data get"+depotName);
//        list.clear();
        DatabaseReference databaseReference=database.getReference("IncargoData/Department/"+depotName);

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    IncargoList mList=data.getValue(IncargoList.class);

                    Log.i("duatjsrb","consignee Value"+mList.getConsignee()+list.size());
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("duatjsrg","Error find"+error);
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }
}