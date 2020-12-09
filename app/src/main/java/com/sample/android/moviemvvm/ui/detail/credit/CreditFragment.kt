package com.sample.android.moviemvvm.ui.detail.credit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.domain.Credit
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.ui.person.PERSON_WRAPPER
import com.sample.android.moviemvvm.ui.person.PersonActivity
import com.sample.android.moviemvvm.ui.person.PersonWrapper
import kotlinx.android.synthetic.main.fragment_credit.view.*

class CreditFragment<T : Credit> : Fragment() {

    companion object {
        private const val TMDB_ITEM = "tmdb_item"
        private const val CREDITS = "credits"

        fun <T : Credit> newInstance(item: TmdbItem, credits: List<T>) =
                CreditFragment<T>().apply {
                    arguments = Bundle(2).apply {
                        putParcelable(TMDB_ITEM, item)
                        putParcelableArrayList(CREDITS, credits as ArrayList)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_credit, container, false)
        root.credit_list.apply {
            setHasFixedSize(true)
            adapter = arguments?.getParcelableArrayList<T>(CREDITS)?.let {
                CreditAdapter(it, object : CreditClickCallback<T> {
                    override fun onClick(credit: T) {
                        val intent = Intent(activity, PersonActivity::class.java).apply {
                            putExtras(Bundle().apply {
                                putParcelable(PERSON_WRAPPER, PersonWrapper(credit,
                                        arguments?.getParcelable<TmdbItem>(TMDB_ITEM)?.backdropPath))
                            })
                        }
                        startActivity(intent)
                    }
                })
            }
        }
        return root
    }
}