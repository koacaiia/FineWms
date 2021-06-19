package fine.koacaiia.finewms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements IncargoListAdapter.IncargoListAdapterOnClickListener, IncargoListAdapter.IncargoListAdapterOnLongClickListener {
    RecyclerView recyclerView;
    ArrayList<IncargoList> list;
    String[] consigneeList;
    IncargoListAdapter adapter;
    FirebaseDatabase database;
    String departmentName;
    String staffName;

    String dateToday;
    String dateStartDay;
    String dateEndDay;

    static private final String SHARE_NAME="SHARE_DEPOT";
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
//        Intent intent=new Intent(MainActivity.this,CameraActivity.class);
//        startActivity(intent);



        dateToday=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        sharedPreferences=getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        if(sharedPreferences.getString("departmentName",null)==null){
            alertUserRegistDialog();
            return;
        }else{
            departmentName=sharedPreferences.getString("departmentName","Fine");
            staffName=sharedPreferences.getString("staffName","Fine");
        }


        recyclerView=findViewById(R.id.main_recyclerview);
        LinearLayoutManager manager=new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(manager);
        database=FirebaseDatabase.getInstance();


        getIncargoData();
        adapter=new IncargoListAdapter(list,this,this);
        recyclerView.setAdapter(adapter);

        getConsigneeListDialog();


        Button btn_location=findViewById(R.id.main_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initMainActivity();
//                Spinner spinner=new Spinner(getApplicationContext());

            }
        });

        Button btn_init=findViewById(R.id.main_init);
        btn_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMainActivity();
            }
        });



    }

    private void initMainActivity() {
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
    }


    private void getIncargoData() {
        list=new ArrayList<>();


        ArrayList<IncargoList> allList=new ArrayList<>();
        ArrayList<String> consigneeArrayList=new ArrayList<>();
        DatabaseReference databaseReference=database.getReference("IncargoData/Department/"+departmentName);
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    IncargoList mList=data.getValue(IncargoList.class);
                    allList.add(mList);
                    if(!consigneeArrayList.contains(mList.getConsignee())){
                        consigneeArrayList.add(mList.getConsignee());
                    }
                    if(mList.getDate().equals(dateToday)){
                        list.add(mList);
                    }

                   }

                consigneeList=consigneeArrayList.toArray(new String[consigneeArrayList.size()]);
                sortInitDataDialog(list,allList,consigneeList);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("duatjsrg","Error find"+error);
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        
    }

    private void sortInitDataDialog(ArrayList<IncargoList> list, ArrayList<IncargoList> allList, String[] consigneeList) {
        View view=getLayoutInflater().inflate(R.layout.dialog_sortinit,null);
        RecyclerView recyclerView=view.findViewById(R.id.sortinit_title_recyclerview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        SortInitDialogAdapter adapter=new SortInitDialogAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(view)
                .show();

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_account:
                alertUserRegistDialog();
                break;
            case R.id.action_putData:
                putRegData();
            case R.id.action_search:
                searchIncargoData();

        }
        return true;
    }
    private void alertUserRegistDialog() {
        editor=sharedPreferences.edit();
        ArrayList<String> departmentNameArrayList=new ArrayList<>();
        departmentNameArrayList.add("1물류_02010810");
        departmentNameArrayList.add("2물류_02010027");
        departmentNameArrayList.add("창고사업부");
        String[] departmentNameList=departmentNameArrayList.toArray(new String[departmentNameArrayList.size()]);
        View view=getLayoutInflater().inflate(R.layout.dialog_user_reg,null);
        EditText reg_edit=view.findViewById(R.id.user_reg_edit);

        Button reg_button=view.findViewById(R.id.user_reg_button);
        TextView reg_textview=view.findViewById(R.id.user_reg_text);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog dialog=builder.create();
        builder.setTitle("사용자 등록")
                .setSingleChoiceItems(departmentNameList,0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        departmentName=departmentNameList[which];
                        reg_textview.setText("부서명:"+departmentName+" 로 확인");
                    }
                })
                .setView(view)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("departmentName",departmentName);
                        editor.putString("staffName",staffName);
                        editor.apply();
                        if(departmentName==null||staffName==null){
                            Toast.makeText(getApplicationContext(),"등록 내용을 재확인 바랍니다.",Toast.LENGTH_SHORT).show();
                           alertUserRegistDialog();
                        }else{
                            Toast.makeText(getApplicationContext(),departmentName+"_"+staffName+" 로 사용자 등록 성공 하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                            initMainActivity();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"사용자 등록을 취소 합니다",Toast.LENGTH_SHORT).show();
                        initMainActivity();
                    }
                })
                .show();
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffName=reg_edit.getText().toString();
                if(departmentName==null ||departmentName.equals("")){
                    Toast.makeText(getApplicationContext(),"부서명 확인 바랍니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                reg_textview.setText(departmentName+"_"+staffName+"으로 사용자 등록을"+"\n"+"진행할려면 하단 confirm 버튼을 클릭 바랍니다." );

            }
        });

    }

    private void searchIncargoData() {
    }

    private void putRegData() {
    }

    private void getConsigneeListDialog(){
      
        Spinner spinner=new Spinner(this);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(spinner);
        builder.setTitle("getConsigneeList");
        builder.show();
    }
}