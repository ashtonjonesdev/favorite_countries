package dev.ashtonjones.favorite_countries.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.textview.MaterialTextView
import dev.ashtonjones.favorite_countries.R
import dev.ashtonjones.favorite_countries.datamodels.Country
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder


class HomeFragmentItemBinder :
    ItemBinder<Country, HomeFragmentItemBinder.ViewHolder>() {


    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(
                parent,
                R.layout.home_fragment_recycler_view_item
            )
        )
    }


    override fun canBindData(item: Any): Boolean {
        return item is Country
    }

    // TODO: Issue with this class not being static?
    inner class ViewHolder(itemView: View) :
        ItemViewHolder<Country?>(itemView) {
        // Reference variables for the ViewHolder that represent the Views in the cards and the String for the message
        var titleTextView: MaterialTextView
        var notesTextView: MaterialTextView

        init {
            titleTextView = itemView.findViewById(R.id.countryTitleTextView)
            notesTextView = itemView.findViewById(R.id.countryNotesTextView)


//
//            // Select/Deselect the card on click
//            itemView.setOnClickListener { view: View? ->
//                itemAdapterPosition = adapterPosition
//                toggleItemSelection()
//            }
        }

        override fun getSwipeDirections(): Int {
            return ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }
    }

    companion object {
        // LOG TAG
        private val LOG_TAG = HomeFragmentItemBinder::class.java.simpleName
        var itemAdapterPosition = -1
    }

    override fun bindViewHolder(holder: ViewHolder, item: Country) {
        holder.titleTextView.text = item.title
        holder.notesTextView.text = item.notes
    }
}