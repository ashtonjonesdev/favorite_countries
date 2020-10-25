package dev.ashtonjones.favorite_countries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ashtonjones.favorite_countries.R
import dev.ashtonjones.favorite_countries.adapters.HomeFragmentItemBinder
import dev.ashtonjones.favorite_countries.databinding.FragmentHomeBinding
import dev.ashtonjones.favorite_countries.datalayer.viewmodel.FavoriteCountriesViewModel
import dev.ashtonjones.favorite_countries.datamodels.Country
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    /// References for member variables.
    private lateinit var multiViewAdapter: MultiViewAdapter

    private lateinit var listSection: ListSection<Country>

    private lateinit var favoriteCountriesViewModel: FavoriteCountriesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        // SETUP MULTIVIEWADAPTER
        setUpMultiViewAdapter()

        // SETUP RECYCLERVIEW
        setUpRecyclerView()

        // SETUP VIEWMODEL
        setUpViewModel()

        setUpSwipeToDismiss()

    }

    private fun setUpSwipeToDismiss() {
        listSection.setSwipeToDismissListener { position, item ->

            Toast.makeText(context, "Deleted ${item.title}", Toast.LENGTH_SHORT).show()

            // Delete the Country from the database
            favoriteCountriesViewModel.deleteCountry(item)

        }

    }

    private fun setUpViewModel() {

        favoriteCountriesViewModel =
            ViewModelProvider(requireActivity())[FavoriteCountriesViewModel::class.java]

    }

    fun setUpRecyclerView() {

        // Create a GridLayoutManager for the RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)

        // Set the LayoutManager span count
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        // Set the layout manager for the RecyclerView
        binding.recyclerView.layoutManager = linearLayoutManager

        // Connect the MultiViewAdapter to the RecyclerView
        binding.recyclerView.setAdapter(multiViewAdapter)

        // Attach ItemToucHelper to RecyclerView to enable drag and drop functionality
        multiViewAdapter.getItemTouchHelper()
            .attachToRecyclerView(binding.recyclerView)
    }

    fun setUpMultiViewAdapter() {

        // Initialize the MultiViewAdapter
        multiViewAdapter = MultiViewAdapter()

        // Initialize the Sections
        listSection = ListSection<Country>()

        // Initialize the Binders
        val itemBinder = HomeFragmentItemBinder()

        // Register the Binders with the MultiViewAdapter
        multiViewAdapter.registerItemBinders(itemBinder)

        // Add Sections to the MultiViewAdapter
        multiViewAdapter.addSection(listSection)
        listSection.setSelectionMode(Mode.SINGLE)

        // Connect the MultiViewAdapter to the RecyclerView
        binding.recyclerView.adapter = multiViewAdapter


    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    private fun setClickListeners() {

        binding.addCountryFAB.setOnClickListener {

            findNavController().navigate(R.id.action_home_fragment_dest_to_addCountry)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        favoriteCountriesViewModel.allWords.observe(this, Observer { countries ->
            listSection.set(
                countries
            )
        })


    }


}