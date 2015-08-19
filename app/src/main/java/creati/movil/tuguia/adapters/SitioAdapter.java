package creati.movil.tuguia.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import creati.movil.tuguia.R;
import creati.movil.tuguia.models.Sitio;

/**
 * Created by Dario Chamorro on 18/08/2015.
 */
public class SitioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public interface OnItemClick{
        void onItemClick(int position);
    }

    Context context;
    List<Sitio> data;

    OnItemClick onItemClick;
    RecyclerView recyclerView;

    public SitioAdapter(Context context, List<Sitio> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == Sitio.LUGAR){
            View v = View.inflate(context, R.layout.template_lugar, null);
            v.setOnClickListener(this);
            holder = new LugarViewHolder(v);
        }else{
            View v = View.inflate(context, R.layout.template_restaurante, null);
            v.setOnClickListener(this);
            holder = new RestauranteViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Sitio sitio = data.get(position);
        if(holder instanceof LugarViewHolder){

            LugarViewHolder lugar = (LugarViewHolder) holder;
            lugar.title.setText(sitio.getNombre());
            lugar.content.setText(sitio.getDescripcion());
            lugar.address.setText(sitio.getDireccion());

            Uri uri = Uri.parse(sitio.getUrl());
            Picasso.with(context).load(uri).into(lugar.img);

        }else{
            RestauranteViewHolder restaurante = (RestauranteViewHolder) holder;

            restaurante.title.setText(sitio.getNombre());
            restaurante.content.setText(sitio.getCategoria());
            restaurante.address.setText(sitio.getDireccion());
            restaurante.number.setText(sitio.getTelefono());

            Uri uri = Uri.parse(sitio.getUrl());
            Picasso.with(context).load(uri).into(restaurante.img);


        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getTipo();
    }

    public void setOnItemClick(RecyclerView recyclerView, OnItemClick onItemClick){
        this.recyclerView = recyclerView;
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        onItemClick.onItemClick(position);
    }

    //region ViewHolders
    class LugarViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;
        TextView content;
        TextView address;

        public LugarViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            address = (TextView) itemView.findViewById(R.id.address);
        }
    }

    class RestauranteViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;
        TextView content;
        TextView address;
        TextView number;

        public RestauranteViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            address = (TextView) itemView.findViewById(R.id.address);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }
    //endregion

}
