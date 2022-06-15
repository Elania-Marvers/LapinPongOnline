package com.game.lapinpongonline

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TABLE_NAME = "matches"

class MatchFirebaseRepo {
    companion object {
        private fun getCollection() = Firebase.firestore.collection(TABLE_NAME)
        fun create(data: MatchBean) = getCollection().add(data)

        fun getAllMatches() = getCollection().orderBy("time", Query.Direction.DESCENDING)
        fun getTheMatch(id : String) = Firebase.firestore.collection(TABLE_NAME).document(id)
        fun update(data: MatchBean, id: String) = getCollection().document(id).set(data)
    }
}