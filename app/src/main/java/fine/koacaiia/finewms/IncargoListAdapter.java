package fine.koacaiia.finewms;

import android.view.LayoutInflater;
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

    public interface IncargoListAdapterOnClickListener{
        void incargoOnClick(IncargoListAdapter.ListViewHolder listViewHolder,View v,int position);

    }
    public interface IncargoListAdapterOnLongClickListener{
        void incargoOnLongClick(IncargoListAdapter.ListViewHolder listViewHolder,View v,int position);
    }

    IncargoListAdapterOnClickListener clickListener;
    IncargoListAdapterOnLongClickListener longClickListener;

    public IncargoListAdapter(ArrayList<IncargoList> list,IncargoListAdapterOnClickListener clickListener,
                              IncargoListAdapterOnLongClickListener longClickListener) {
        this.list=list;
        this.clickListener=clickListener;
        this.longClickListener=longClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_incargo,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListViewHolder holder, int position) {
        String str_container20=list.get(position).getContainer20();
        String str_container40=list.get(position).getContainer40();
        String str_lclcargo=list.get(position).getLclcargo();
        String cargoType;
        if(str_container20.equals("1")){
            cargoType="20FT";
        }else if(str_container40.equals("1")){
            cargoType="40FT";
        }else if(str_lclcargo.equals("1")){
            cargoType="Cargo";
        }else{
            cargoType="미정";
        }

        holder.working.setText(list.get(position).getWorking());
        holder.date.setText(list.get(position).getDate());
        holder.consignee.setText(list.get(position).getConsignee());
        holder.container.setText(list.get(position).getContainer());
        holder.cargotype.setText(cargoType);
        holder.remark.setText(list.get(position).getRemark());
        holder.bl.setText(list.get(position).getBl());
        holder.des.setText(list.get(position).getDescription());
        holder.incargo.setText(list.get(position).getIncargo());

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
            this.working=itemView.findViewById(R.id.incargo_working);
            this.date=itemView.findViewById(R.id.incargo_date);
            this.container=itemView.findViewById(R.id.incargo_container);
            this.consignee=itemView.findViewById(R.id.incargo_consignee);
            this.cargotype=itemView.findViewById(R.id.incargo_cargotype);
            this.remark=itemView.findViewById(R.id.incargo_remark);
            this.bl=itemView.findViewById(R.id.incargo_bl);
            this.des=itemView.findViewById(R.id.incargo_des);
            this.incargo=itemView.findViewById(R.id.incargo_incargo);
            this.cardView=itemView.findViewById(R.id.incargo_back);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.incargoOnClick(ListViewHolder.this,v,getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.incargoOnLongClick(ListViewHolder.this,v,getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
