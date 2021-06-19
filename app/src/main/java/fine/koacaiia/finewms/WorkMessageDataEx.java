package fine.koacaiia.finewms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WorkMessageDataEx extends AppCompatActivity {
    FirebaseDatabase database;
    ArrayList<WorkingMessageList> list;
    RecyclerView recyclerview;
    WorkingMessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_message_data_ex);

        database=FirebaseDatabase.getInstance();
        recyclerview=findViewById(R.id.recyclerView_workingMessageDate);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        getData();
        adapter=new WorkingMessageListAdapter(list);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void getData() {
        list=new ArrayList<>();
        DatabaseReference databaseReference=database.getReference("WorkingMessage");
        ValueEventListener listener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    WorkingMessageList mList=data.getValue(WorkingMessageList.class);
                    list.add(mList);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(listener);
    }
}