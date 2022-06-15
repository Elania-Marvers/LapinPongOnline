package com.game.lapinpongonline

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.game.lapinpongonline.databinding.RowGameBinding
import com.google.firebase.auth.FirebaseAuth

class GameFirebaseAdapter(option: FirestoreRecyclerOptions<MatchBean>) :
    FirestoreRecyclerAdapter<MatchBean, GameFirebaseAdapter.ViewHolder>(option) {
    class ViewHolder(val bind: RowGameBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowGameBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MatchBean) {
        if (model.isPlay) {
            holder.bind.cardGame.isVisible = false
        }


        holder.bind.tvT1.text = model.nameTeam1
        holder.bind.tvT2.text = model.nameTeam2
        holder.bind.tvScore1.text = model.scoreTeam1.toString()
        holder.bind.tvScore2.text = model.scoreTeam2.toString()
        holder.bind.timeRemain.text = model.timeRemaining.toString()

        holder.bind.join.setOnClickListener { v ->

            if (model.player_one == null) {
                model.player_one = FirebaseAuth.getInstance().currentUser!!.displayName.toString()
            } else if (model.player_two == null) {
                model.player_two = FirebaseAuth.getInstance().currentUser!!.displayName.toString()
                model.isPlay = true
            }
            MatchFirebaseRepo.update(model, snapshots.getSnapshot(position).id)


            val context = v.context
            var intent = Intent(v.context, GameActivity::class.java)
                .putExtra("id", model.id)
            context.startActivity(intent)


        }
    }
}