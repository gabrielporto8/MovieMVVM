package com.sample.android.moviemvvm.ui.detail.credit

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.databinding.CreditItemBinding
import com.sample.android.moviemvvm.domain.Credit
import com.sample.android.moviemvvm.util.layoutInflater

class CreditAdapter<T : Credit>(
        credits: ArrayList<T>,
        private val creditClickCallback: CreditClickCallback<T>)
    : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    private var credits: List<T> = credits
        set(credit) {
            field = credit
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val binding: CreditItemBinding = DataBindingUtil
                .inflate(parent.context.layoutInflater,
                        R.layout.credit_item,
                        parent, false)
        binding.callback = creditClickCallback
        return CreditViewHolder(binding)
    }

    override fun getItemCount() = credits.size

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        with(holder.binding) {
            creditItem = credits[position]
            executePendingBindings()
        }
    }

    class CreditViewHolder(internal val binding: CreditItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}