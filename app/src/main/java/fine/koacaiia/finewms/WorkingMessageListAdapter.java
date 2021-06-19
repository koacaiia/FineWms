package fine.koacaiia.finewms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WorkingMessageListAdapter extends RecyclerView.Adapter<WorkingMessageListAdapter.ListView>{
    ArrayList<WorkingMessageList> list;

    public WorkingMessageListAdapter(ArrayList<WorkingMessageList> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ListView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.worklist_exercise,parent,false);
        return new ListView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListView holder, int position) {
        holder.message.setText(list.get(position).getMsg());
        holder.nickName.setText(list.get(position).getNickName());
        holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListView extends RecyclerView.ViewHolder {
        TextView message;
        TextView nickName;
        TextView time;
        TextView imageView;
        public ListView(@NonNull @NotNull View itemView) {
            super(itemView);
            this.message=itemView.findViewById(R.id.workMessage_message);
            this.nickName=itemView.findViewById(R.id.workMessage_nickName);
            this.time=itemView.findViewById(R.id.workMessage_time);
            this.imageView=itemView.findViewById(R.id.workMessage_image);
        }
    }
}
