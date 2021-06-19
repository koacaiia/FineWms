package fine.koacaiia.finewms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SortInitDialogAdapter extends RecyclerView.Adapter<SortInitDialogAdapter.ListViewHolder>{
    ArrayList<IncargoList> list=new ArrayList<>();
    public SortInitDialogAdapter(ArrayList<IncargoList> list){
        this.list=list;
    }
    @NonNull
    @NotNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sortinitdialog,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListViewHolder holder, int position) {
        holder.txtConsignee.setText(list.get(position).getConsignee());
        holder.txtContainer40.setText(list.get(position).getContainer40());
        holder.txtContainer20.setText(list.get(position).getContainer20());
        holder.txtCargo.setText(list.get(position).getLclcargo());
        holder.txtQty.setText(list.get(position).getIncargo());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtConsignee;
        TextView txtContainer40;
        TextView txtContainer20;
        TextView txtCargo;
        TextView txtQty;
        public ListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtConsignee=itemView.findViewById(R.id.sortinit_recycler_consignee);
            txtContainer40=itemView.findViewById(R.id.sortinit_recycler_container40);
            txtContainer20=itemView.findViewById(R.id.sortinit_recycler_container20);
            txtCargo=itemView.findViewById(R.id.sortinit_recycler_cargo);
            txtQty=itemView.findViewById(R.id.sortinit_recycler_qty);

        }
    }
}
