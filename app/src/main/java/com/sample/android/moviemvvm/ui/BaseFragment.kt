package com.sample.android.moviemvvm.ui

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.paging.NetworkState
import com.sample.android.moviemvvm.ui.detail.DetailActivity
import com.sample.android.moviemvvm.ui.detail.EXTRA_NAV_TYPE
import com.sample.android.moviemvvm.ui.detail.EXTRA_TMDB_ITEM
import com.sample.android.moviemvvm.util.NavType
import com.sample.android.moviemvvm.widget.MarginDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

abstract class BaseFragment<T : TmdbItem> : DaggerFragment(), TmdbClickCallback<T> {

    protected abstract val viewModel: TmdbViewModel<T>

    protected abstract val navType: NavType?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val itemAdapter = TmdbAdapter({ viewModel.retry() }, this)

        with(root) {

            swipe_refresh.apply {
                setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorPrimary),
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                viewModel.refreshState.observe(viewLifecycleOwner, Observer {
                    isRefreshing = it == NetworkState.LOADING
                    itemAdapter.setRefreshState(it)
                })

                setOnRefreshListener { viewModel.refresh() }
            }

            list.apply {
                addItemDecoration(MarginDecoration(context))
                setHasFixedSize(true)
                adapter = itemAdapter
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (itemAdapter.getItemViewType(position) != R.layout.network_state_item)
                            1 else manager.spanCount
                    }
                }
            }
        }

        viewModel.items.observe(this, Observer<PagedList<T>> {
            itemAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            itemAdapter.setNetworkState(it)
        })

        return root
    }

    override fun onClick(t: T, poster: ImageView, name: TextView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_TMDB_ITEM, t)
                putParcelable(EXTRA_NAV_TYPE, navType)
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(poster, ViewCompat.getTransitionName(poster)),
                Pair<View, String>(name, ViewCompat.getTransitionName(name)))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}