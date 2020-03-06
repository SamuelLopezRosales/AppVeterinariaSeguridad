package com.example.samuel.appveterinariaseguridad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuel.appveterinariaseguridad.R;
import com.example.samuel.appveterinariaseguridad.ui.activity.OptionsActivity;
import com.example.samuel.appveterinariaseguridad.ui.model.Species;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.ViewHolder> {
    private ArrayList<Species> mDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivSpecies;

        public ViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.tvSpeciesName);
            ivSpecies = (ImageView) v.findViewById(R.id.ivSpecies);
        }
    }

    public SpeciesAdapter(Context context, ArrayList<Species> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

   /* public void setDataSet(ArrayList<Species> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }*/

    @Override
    public SpeciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.species_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        final Species species = mDataSet.get(i);

        holder.tvName.setText(species.getName());
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

        holder.ivSpecies.setImageResource(species.getImagen());



        // bind click event
        holder.ivSpecies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OptionsActivity.class);

                intent.putExtra("species_id", species.getId());
                intent.putExtra("species_name", species.getName());

                // pass image via intent :O
                holder.ivSpecies.buildDrawingCache();
                final Bitmap species_image = holder.ivSpecies.getDrawingCache();
                intent.putExtra("species_image", species_image);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

