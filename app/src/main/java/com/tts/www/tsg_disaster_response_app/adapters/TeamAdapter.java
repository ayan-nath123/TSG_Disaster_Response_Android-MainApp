package com.tts.www.tsg_disaster_response_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.www.tsg_disaster_response_app.Model.TeamModel;
import com.tts.www.tsg_disaster_response_app.R;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    Context context;
    ArrayList<TeamModel> teamModelArrayList;

    public TeamAdapter(Context context, ArrayList<TeamModel> teamModelArrayList) {
        this.context = context;
        this.teamModelArrayList = teamModelArrayList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_team, viewGroup,false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int i) {
        TeamModel teamModel =teamModelArrayList.get(i);
        holder.teamMemberName.setText(teamModel.getTeamMemberName());
        holder.teamMemberCompany.setText(teamModel.getTeamMembercompany());
        holder.teamMemberDesignation.setText(teamModel.getTeamMemberDesignation());
        holder.teamMemberPhone.setText(teamModel.getTeamMemberPhoneNo());
        holder.teamMemberEmail.setText(teamModel.getTeamMemberEmail());

    }

    @Override
    public int getItemCount() {
        return teamModelArrayList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView teamMemberName,teamMemberCompany,teamMemberDesignation,teamMemberPhone,teamMemberEmail;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            teamMemberName = itemView.findViewById(R.id.tv_team_name);
            teamMemberCompany = itemView.findViewById(R.id.tv_team_company);
            teamMemberDesignation = itemView.findViewById(R.id.tv_team_designation);
            teamMemberPhone = itemView.findViewById(R.id.tv_team_phone);
            teamMemberEmail = itemView.findViewById(R.id.tv_team_email);
        }
    }
}
