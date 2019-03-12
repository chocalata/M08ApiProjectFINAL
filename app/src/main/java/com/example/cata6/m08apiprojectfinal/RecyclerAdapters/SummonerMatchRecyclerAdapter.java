package com.example.cata6.m08apiprojectfinal.RecyclerAdapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.Champion;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.Spell;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.SummonerSpellsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Participant;
import com.example.cata6.m08apiprojectfinal.Model.Games.ParticipantIdentity;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.StatVars.StatVars;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SummonerMatchRecyclerAdapter extends RecyclerView.Adapter<SummonerMatchRecyclerAdapter.SummonerMatchViewHolder>  {

    public List<Participant> participantList;
    public List<ParticipantIdentity> participantIdentityList;
    ChampionsResponse championsResponse;
    SummonerSpellsResponse summonerSpellsResponse;

    public SummonerMatchRecyclerAdapter(List<Participant> participantList,
                                        List<ParticipantIdentity> participantIdentityList,
                                        ChampionsResponse championsResponse,
                                        SummonerSpellsResponse summonerSpellsResponse) {
        this.participantList = participantList;
        this.participantIdentityList = participantIdentityList;
        this.championsResponse = championsResponse;
        this.summonerSpellsResponse = summonerSpellsResponse;
    }

    Participant participant;

    @NonNull
    @Override
    public SummonerMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSummoner = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summoner_result, parent, false);

        return new SummonerMatchRecyclerAdapter.SummonerMatchViewHolder(itemSummoner);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SummonerMatchViewHolder holder, int position) {
        participant = participantList.get(position);
        Log.e("ABC", String.valueOf(participant.teamId));
        for (ParticipantIdentity participantIdentity:participantIdentityList) {

            if(participantIdentity.participantId == participant.participantId){
                //HACER LA CONSULTA A LA API DE DDRAGON
                String championName = null;
                String spellName1 = null;
                String spellName2 = null;
                if(participant.teamId == StatVars.RED_TEAM){
                    holder.itemView.setBackgroundColor(0xFFFF9696);
                }else {
                    holder.itemView.setBackgroundColor(0xFF9696FF);
                }

                for (Champion champ:championsResponse.data.values()) {
                    if(champ.key.equals(String.valueOf(participant.championId))){
                        championName = champ.id;
                    }
                }
                for (Spell spell:summonerSpellsResponse.data.values()) {
                    if(spell.key.equals(String.valueOf(participant.spell1Id))){
                        spellName1 = spell.id;
                    }else if(spell.key.equals(String.valueOf(participant.spell2Id))){
                        spellName2 = spell.id;
                    }
                }

                //Imagen campeón
                Glide.with(holder.civ_resultChampionImage.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/champion/" + championName + ".png")
                        .into(holder.civ_resultChampionImage);

                //Nombre Summoner.
                holder.tv_resultSummonerName.setText(participantIdentity.player.summonerName);

                //Score Summoner.
                holder.tv_resultSummonerScore.setText(participant.stats.kills + "/" + participant.stats.deaths + "/" + participant.stats.assists);

                //Hechizos de invocador.
                Glide.with(holder.iv_summonerSpell1.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/spell/" + spellName1 + ".png")
                        .into(holder.iv_summonerSpell1);
                Glide.with(holder.iv_summonerSpell2.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/spell/" + spellName2 + ".png")
                        .into(holder.iv_summonerSpell2);

                //Items Summoner.
                Glide.with(holder.iv_summonerItem0.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item0 + ".png")
                        .into(holder.iv_summonerItem0);
                Glide.with(holder.iv_summonerItem1.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item1 + ".png")
                        .into(holder.iv_summonerItem1);
                Glide.with(holder.iv_summonerItem2.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item2 + ".png")
                        .into(holder.iv_summonerItem2);
                Glide.with(holder.iv_summonerItem3.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item3 + ".png")
                        .into(holder.iv_summonerItem3);
                Glide.with(holder.iv_summonerItem4.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item4 + ".png")
                        .into(holder.iv_summonerItem4);
                Glide.with(holder.iv_summonerItem5.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item5 + ".png")
                        .into(holder.iv_summonerItem5);
                Glide.with(holder.iv_summonerItem6.getContext())
                        .load("http://ddragon.leagueoflegends.com/cdn/" + StatVars.VERSION + "/img/item/" + participant.stats.item6 + ".png")
                        .into(holder.iv_summonerItem6);

                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return participantList.size() ;
    }

    class SummonerMatchViewHolder extends RecyclerView.ViewHolder{
        CircleImageView civ_resultChampionImage;
        TextView tv_resultSummonerName;
        TextView tv_resultSummonerScore;
        ImageView iv_summonerSpell1;
        ImageView iv_summonerSpell2;
        ImageView iv_summonerItem0;
        ImageView iv_summonerItem1;
        ImageView iv_summonerItem2;
        ImageView iv_summonerItem3;
        ImageView iv_summonerItem4;
        ImageView iv_summonerItem5;
        ImageView iv_summonerItem6;

        public SummonerMatchViewHolder(@NonNull View itemView) {
            super(itemView);
            civ_resultChampionImage = itemView.findViewById(R.id.resultChampionImage);
            tv_resultSummonerName = itemView.findViewById(R.id.resultSummonerName);
            tv_resultSummonerScore = itemView.findViewById(R.id.resultSummonerScore);
            iv_summonerSpell1 = itemView.findViewById(R.id.summonerSpell1);
            iv_summonerSpell2 = itemView.findViewById(R.id.summonerSpell2);
            iv_summonerItem0 = itemView.findViewById(R.id.summonerItem0);
            iv_summonerItem1 = itemView.findViewById(R.id.summonerItem1);
            iv_summonerItem2 = itemView.findViewById(R.id.summonerItem2);
            iv_summonerItem3 = itemView.findViewById(R.id.summonerItem3);
            iv_summonerItem4 = itemView.findViewById(R.id.summonerItem4);
            iv_summonerItem5 = itemView.findViewById(R.id.summonerItem5);
            iv_summonerItem6 = itemView.findViewById(R.id.summonerItem6);
        }
    }
}
