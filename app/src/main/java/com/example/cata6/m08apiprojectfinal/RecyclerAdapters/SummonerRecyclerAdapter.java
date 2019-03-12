package com.example.cata6.m08apiprojectfinal.RecyclerAdapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cata6.m08apiprojectfinal.Activity.SummonerGamesActivity;
import com.example.cata6.m08apiprojectfinal.Converter.ConverterSummonerToJson;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.StatVars.StatVars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummonerRecyclerAdapter extends RecyclerView.Adapter<SummonerRecyclerAdapter.SummonerViewHolder> {

    public interface ClickListener {
        void onDeleteSummoner(Summoner summoner);
    }

    List<Summoner> listaSummoners;

    Map<String,Integer> imagenDivisiones = new HashMap<String, Integer>(){{
        put("unranked", R.drawable.unraked);
        put("iron", R.drawable.iron);
        put("bronze", R.drawable.bronze);
        put("silver", R.drawable.silver);
        put("gold", R.drawable.gold);
        put("platinum", R.drawable.platinum);
        put("diamond", R.drawable.diamond);
        put("master", R.drawable.master);
        put("grandmaster", R.drawable.grandmaster);
        put("challenger", R.drawable.challenger);
    }};

    ClickListener listener;
    public SummonerRecyclerAdapter(ClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public SummonerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSummoner = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summoner, parent, false);

        return new SummonerViewHolder(itemSummoner);
    }

    public void setListaSummoners(List<Summoner> listaSummoners) {
        this.listaSummoners = listaSummoners;
    }

    @Override
    public void onBindViewHolder(final SummonerViewHolder holder, final int position) {
        final Summoner summoner = listaSummoners.get(position);

        //Summoner Games.
        holder.cardViewSummonerBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), SummonerGamesActivity.class);
                intent.putExtra("summoner", ConverterSummonerToJson.someObjectListToString(summoner));
                v.getContext().startActivity(intent);
            }
        });

        //Summoner icon.
        Glide.with(holder.imageViewIcon.getContext())
                .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/profileicon/" + summoner.getProfileIconId() + ".png")
                .into(holder.imageViewIcon);

        //Summoner División.
        if(summoner.division != null) {
            Glide.with(holder.imageViewDiv.getContext())
                    .load(imagenDivisiones.get(summoner.division.substring(1)))
                    .into(holder.imageViewDiv);
        }else {
            Glide.with(holder.imageViewDiv.getContext())
                    .load(imagenDivisiones.get("unranked"))
                    .into(holder.imageViewDiv);
        }

        //Summoner nombre.
        holder.textViewName.setText(summoner.getName());

        //Summoner level.
        holder.textViewLevel.setText("Lv. " + String.valueOf(summoner.getSummonerLevel()));

        //Borrar Summoner.
        holder.imageButtonBorrarSummoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteSummoner(summoner);

            }
        });



        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("ID_ALUMNO", summoner.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return (listaSummoners != null ? listaSummoners.size() : 0);
    }

    class SummonerViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewSummonerBasicInfo;
        ImageView imageViewIcon;
        ImageView imageViewDiv;
        TextView textViewName;
        TextView textViewLevel;
        ImageButton imageButtonBorrarSummoner;



        public SummonerViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewSummonerBasicInfo = itemView.findViewById(R.id.summonerBasicInfo);
            imageViewIcon = itemView.findViewById(R.id.summonerIcon);
            imageViewDiv = itemView.findViewById(R.id.summonerDiv);
            textViewName = itemView.findViewById(R.id.summonerName);
            textViewLevel = itemView.findViewById(R.id.summonerLevel);
            imageButtonBorrarSummoner = itemView.findViewById(R.id.borrarSummoner);
        }
    }
}
