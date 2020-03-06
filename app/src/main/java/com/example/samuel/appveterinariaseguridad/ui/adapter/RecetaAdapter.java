package com.example.samuel.appveterinariaseguridad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.appveterinariaseguridad.R;
import com.example.samuel.appveterinariaseguridad.ui.model.Receta;

import java.util.ArrayList;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {
    private ArrayList<Receta> mDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDueno;
        public ImageView ivReceta;

        public ViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.tvRecetaName);
            tvDueno = v.findViewById(R.id.tvRecetaDueno);
            ivReceta = (ImageView) v.findViewById(R.id.ivRecetas);
        }
    }

    public RecetaAdapter(Context context, ArrayList<Receta> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

   /* public void setDataSet(ArrayList<Species> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }*/

    @Override
    public RecetaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recetas_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        final Receta receta = mDataSet.get(i);

        holder.tvName.setText(receta.getPaciente());
        holder.tvDueno.setText(receta.getDueno());
        /*Picasso.with(context)
                .load(species.getImageUrl())
                // .placeholder()
                .fit()
                .into(holder.ivSpecies);*/


       /* Picasso.get()
                .load(species.getImageUrl())
                .resize(100,100)
                .centerCrop()
                .into(holder.ivSpecies);*/

        holder.ivReceta.setImageResource(receta.getImagen());



        // bind click event
        holder.ivReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, receta.getDueno(), Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(context, OptionsActivity.class);

               // intent.putExtra("species_id", species.getId());
               // intent.putExtra("species_name", species.getName());

                // pass image via intent :O
                //holder.ivSpecies.buildDrawingCache();
                //final Bitmap species_image = holder.ivSpecies.getDrawingCache();
                //intent.putExtra("species_image", species_image);

                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

