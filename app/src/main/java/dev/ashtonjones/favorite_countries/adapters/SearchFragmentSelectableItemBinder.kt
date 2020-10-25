package dev.ashtonjones.favorite_countries.adapters

import android.view.View
import android.view.ViewGroup
import com.google.android.material.textview.MaterialTextView
import dev.ashtonjones.favorite_countries.R
import dev.ashtonjones.favorite_countries.datamodels.Country
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class SearchFragmentSelectableItemBinder :
    ItemBinder<Country, SearchFragmentSelectableItemBinder.ViewHolder>() {


    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(
                parent,
                R.layout.search_fragment_recycler_view_item
            )
        )
    }



    override fun canBindData(item: Any): Boolean {
        return item is Country
    }

    // TODO: Issue with this class not being static?
    inner class ViewHolder(itemView: View) :
        ItemViewHolder<Country?>(itemView) {

        var titleTextView: MaterialTextView

        init {
            titleTextView = itemView.findViewById(R.id.searchCountryTitleTextView)



            // Need to add this method to enable selection of item
            itemView.setOnClickListener { view: View? ->
                itemAdapterPosition = adapterPosition
                toggleItemSelection()
            }
        }
    }

    companion object {
        // LOG TAG
        private val LOG_TAG = HomeFragmentItemBinder::class.java.simpleName
        var itemAdapterPosition = -1
    }

    override fun bindViewHolder(holder: ViewHolder, item: Country) {
        holder.titleTextView.text = item.title
    }
}