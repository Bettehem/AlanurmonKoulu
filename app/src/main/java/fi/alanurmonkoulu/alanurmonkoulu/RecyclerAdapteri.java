package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by alanurmonkoulu on 16.2.2015.
 */
public class RecyclerAdapteri extends RecyclerView.Adapter<RecyclerAdapteri.RecyclerViewHolderi>{

    LayoutInflater inflater;
    List<ListanTietoja> listanTietoja = Collections.emptyList();
    Context context;
    ClickListener clickListener;

    public RecyclerAdapteri (Context context, List<ListanTietoja> listanTiedot){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listanTietoja = listanTiedot;
    }

    public void update(int position){
        notifyItemChanged(position);
    }

    public void updateAll(){
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolderi onCreateViewHolder(ViewGroup parent, int viewType) {

        //reference to the custom list item layout
        View itemView = inflater.inflate(R.layout.listan_kohteet, parent, false);

        //jumps to the ViewHolder class below
        return new RecyclerViewHolderi(itemView);
    }

    //used to set what is actually displayed
    @Override
    public void onBindViewHolder(RecyclerViewHolderi holderi, int position) {
        //collect the information from ListanTietoja class
        ListanTietoja nykyinenListanOsa = listanTietoja.get(position);

        holderi.title.setText(nykyinenListanOsa.recyclerListItemTextTitle);
        //holderi.icon.setImageResource(nykyinenListanOsa.recyclerIconId);
    }

    @Override
    public int getItemCount() {
        return listanTietoja.size();
    }

    class RecyclerViewHolderi extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;

        ImageView icon;

        public RecyclerViewHolderi(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listan_teksti);

        }

        @Override
        public void onClick(View v){
            if (clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{

        public void itemClicked(View view, int position);

    }

}
