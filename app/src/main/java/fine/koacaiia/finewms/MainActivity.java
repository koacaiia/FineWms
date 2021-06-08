package fine.koacaiia.finewms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IncargoListAdapter.IncargoListAdapterOnClickListener, IncargoListAdapter.IncargoListAdapterOnLongClickListener {
    RecyclerView recyclerView;
    ArrayList<IncargoList> list;
    String[] consigneeList;
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
        adapter=new IncargoListAdapter(list,this,this);
        recyclerView.setAdapter(adapter);



    }

    private void getIncargoData() {
        list.clear();
        ArrayList<String> consigneeArrayList=new ArrayList<>();
        DatabaseReference databaseReference=database.getReference("IncargoData/Department/"+depotName);
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    IncargoList mList=data.getValue(IncargoList.class);
                    list.add(mList);
                    if(!consigneeArrayList.contains(mList.getConsignee())){
                        consigneeArrayList.add(mList.getConsignee());
                    }
                   }

                consigneeList=consigneeArrayList.toArray(new String[consigneeArrayList.size()]);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("duatjsrg","Error find"+error);
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }



    @Override
    public void incargoOnClick(IncargoListAdapter.ListViewHolder listViewHolder, View v, int position) {
        Toast.makeText(this,list.get(position).getBl()+"bl:SELECTED",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void incargoOnLongClick(IncargoListAdapter.ListViewHolder listViewHolder, View v, int position) {
        Toast.makeText(this,list.get(position).getConsignee()+"Consignee:SELECTED",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}