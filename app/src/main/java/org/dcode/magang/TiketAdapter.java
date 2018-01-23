package org.dcode.magang;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 22/01/2018.
 */

public class TiketAdapter extends ArrayAdapter<Tiket> {
    List<Tiket> listTiket;
    Context context;
    int layout;

    public TiketAdapter(@NonNull Context context, int layout, List<Tiket> listTiket) {
        super(context, layout, listTiket);
        this.context=context;
        this.layout=layout;
        this.listTiket=listTiket;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        TicketHolder holder;
        if(v==null){
            LayoutInflater vi=((Activity)context).getLayoutInflater();
            v = vi.inflate(layout, parent,false);

            holder=new TicketHolder();
            holder.perbaikan_=(TextView) v.findViewById(R.id.perbaikan);
            holder.note_=(TextView) v.findViewById(R.id.note);

            v.setTag(holder);
        }
        else{
            holder=(TicketHolder) v.getTag();
        }
        Tiket tiket = listTiket.get(position);
        holder.perbaikan_.setText(tiket.getPerbaikan());
        holder.note_.setText(tiket.getNote());
        return v;
    }

    static class TicketHolder{
        TextView perbaikan_;
        TextView note_;
    }
}
