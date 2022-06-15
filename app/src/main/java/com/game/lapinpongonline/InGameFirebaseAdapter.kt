package com.game.lapinpongonline

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.game.lapinpongonline.databinding.GameBindingInterfacesBinding
import com.game.lapinpongonline.databinding.RowGameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference


class InGameFirebaseAdapter(option: FirestoreRecyclerOptions<MatchBean>, idstr: String) :
    FirestoreRecyclerAdapter<MatchBean, InGameFirebaseAdapter.ViewHolder>(option) {

    var id = idstr


    class ViewHolder(val bind: GameBindingInterfacesBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GameBindingInterfacesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MatchBean) {
        var usrName = FirebaseAuth.getInstance().currentUser!!.displayName.toString()

        if (model.id == id) {
            holder.bind.tvT1.text = model.nameTeam1
            holder.bind.tvT2.text = model.nameTeam2
            holder.bind.tvScore1.text = model.scoreTeam1.toString()
            holder.bind.tvScore2.text = model.scoreTeam2.toString()
            holder.bind.timeRemain.text = ""

            if (model.scoreTeam1 < 1000 && model.scoreTeam2 < 1000) {
                if (usrName == model.player_one.toString() && model.bunny == false) {
                    holder.bind.image.setImageResource(R.drawable.hbs)
                } else if (usrName == model.player_one.toString()) {
                    holder.bind.image.setImageResource(R.drawable.madbunny)
                }


                if (usrName == model.player_two.toString() && model.bunny == true) {
                    holder.bind.image.setImageResource(R.drawable.hbs)
                } else if (usrName == model.player_two.toString()) {
                    holder.bind.image.setImageResource(R.drawable.madbunny)
                }
            }

            holder.bind.send.setOnClickListener {
                if (model.scoreTeam1 < 1000 && model.scoreTeam2 < 1000) {
                    if (usrName == model.player_one.toString() && !model.bunny)
                        model.bunny = !model.bunny

                    if (usrName == model.player_two.toString() && model.bunny)
                        model.bunny = !model.bunny
                    MatchFirebaseRepo.update(model, snapshots.getSnapshot(position).id)
                }
            }


            holder.bind.miner.setOnClickListener {
                if (model.scoreTeam1 < 1000 && model.scoreTeam2 < 1000) {
                    if (usrName == model.player_one.toString() && model.bunny) {
                        model.scoreTeam1 += 10
                    } else if (usrName == model.player_one.toString()) {
                        model.scoreTeam1 -= 20
                    }

                    if (usrName == model.player_two.toString() && !model.bunny) {
                        model.scoreTeam2 += 10
                    } else if (usrName == model.player_two.toString()) {
                        model.scoreTeam2 -= 20
                    }
                    MatchFirebaseRepo.update(model, snapshots.getSnapshot(position).id)
                } else {
                    if (model.scoreTeam1 > model.scoreTeam2)
                        holder.bind.timeRemain.text = "Victory for team 1"
                    else
                        holder.bind.timeRemain.text = "Victory for team 2"

                    if (usrName == model.player_two.toString() && model.scoreTeam1 > model.scoreTeam2) {
                        holder.bind.image.setImageResource(R.drawable.hbs)
                    } else {
                        holder.bind.image.setImageResource(R.drawable.madbunny)
                    }

                    if (usrName == model.player_one.toString() && model.scoreTeam1 < model.scoreTeam2) {
                        holder.bind.image.setImageResource(R.drawable.hbs)
                    } else {
                        holder.bind.image.setImageResource(R.drawable.madbunny)
                    }

                }
            }

        } else {
            holder.bind.cardGame.isVisible = false
        }
    }

}
