package fine.koacaiia.finewms;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IncargoListAdapter extends RecyclerView.Adapter<IncargoListAdapter.ListViewHolder>{
    ArrayList<IncargoList> list;


    public IncargoListAdapter(ArrayList<IncargoList> list) {
        this.list=list;
    }

    @NonNull
    @NotNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView working;
        TextView date;
        TextView container;
        TextView consignee;
        TextView cargotype;
        TextView remark;
        TextView bl;
        TextView des;
        TextView incargo;
        LinearLayout cardView;
        public ListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

        }
    }
}
