package com.davidups.roomlivedata

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlin.coroutines.CoroutineContext

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView = itemView.tvWord
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordListAdapter.WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(p0: WordListAdapter.WordViewHolder, p1: Int) {
        val current = words[p1]
        p0.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>){
        this.words = words
        notifyDataSetChanged()
    }
}