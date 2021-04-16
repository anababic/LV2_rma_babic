package com.example.lv2_zad1_babic

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PersonHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(person: InspiringPerson, interactionListener: InteractionListener){
        itemView.tvName.text = person.firstName+" "+person.lastName;
        when(person.dateOfDeath){
            null -> itemView.tvDateBornDied.text= person.dateOfBirth+" - ";
            else -> itemView.tvDateBornDied.text= person.dateOfBirth+" - "+person.dateOfDeath;
        }
        itemView.tvDescription.text = person.description;
        itemView.ivPersons.setImageBitmap(person.image);
        itemView.ivPersons.setOnClickListener { interactionListener.onShowQuote(person.id) }
    }
}