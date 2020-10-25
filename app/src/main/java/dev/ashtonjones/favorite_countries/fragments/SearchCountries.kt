package dev.ashtonjones.favorite_countries.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ashtonjones.favorite_countries.adapters.SearchFragmentSelectableItemBinder
import dev.ashtonjones.favorite_countries.databinding.FragmentSearchCountriesBinding
import dev.ashtonjones.favorite_countries.datamodels.Country
import dev.ashtonjones.favorite_countries.datamodels.CountryResult
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode
import mva2.adapter.util.OnSelectionChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchCountries : Fragment() {


    private var _binding: FragmentSearchCountriesBinding? = null

    private val binding get() = _binding!!

    private lateinit var multiViewAdapter: MultiViewAdapter

    private lateinit var listSection: ListSection<Country>

//    private val worldBankAPI = WorldBankAPI()

    lateinit var filteredList: ArrayList<Country>


    private val callback = object : Callback<CountryResult> {
        override fun onFailure(call: Call<CountryResult>?, t:Throwable?) {
            Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
        }

        override fun onResponse(call: Call<CountryResult>?, response: Response<CountryResult>?) {
            response?.isSuccessful.let {
                val resultList = CountryResult(response?.body()?.items ?: emptyList())
//                repoList.adapter = RepoListAdapter(resultList)

                val countriesList = resultList.items;

                Toast.makeText(context, "Found countries: $countriesList", Toast.LENGTH_SHORT).show()


                listSection.set(countriesList)

            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchCountriesBinding.inflate(inflater, container, false)
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

        // SETUP SECTION SELECTION BEHAVIOR
        setUpSectionSelection()

    }

    private fun setUpSectionSelection() {
        listSection.setOnSelectionChangedListener(OnSelectionChangedListener { item: Country?, isSelected: Boolean, selectedItems: List<Country?>? ->

            /// Navigate to the Country Details destination, passing the Country title as an argument
            findNavController().navigate(
                SearchCountriesDirections.actionSearchCountriesFragmentDestToCountryDetailsFragmentDest(
                    item?.title
                )
            )
        })
    }

    private fun setClickListeners() {
        // Set a listener for action button on search to initiate a search for countries
        binding.textInputEditTextSearchCountry.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

//                    Toast.makeText(context, "Searching for ${v.text}", Toast.LENGTH_SHORT).show()

//                    worldBankAPI.getAllCountries(callback)

                    filteredList = ArrayList()

                    /// If the sample data contains the entered country, add it to the filtered list to only show 1 country
                    if(countriesSampleData.contains(Country(binding.textInputEditTextSearchCountry.text.toString(), ""))) {

                        filteredList.add(Country(binding.textInputEditTextSearchCountry.text.toString(), ""))

                        listSection.set(filteredList)

                    }

                    else {
                        Toast.makeText(context, "Could not find a country!", Toast.LENGTH_SHORT).show()
                    }


                    true
                }
                else -> false
            }
        }
    }

    private fun setUpRecyclerView() {
        // Create a LinearLayout manager for the RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)

        // Set the LayoutManager orientation
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        // Set the layout manager for the RecyclerView
        binding.recyclerView.layoutManager = linearLayoutManager

        // Connect the MultiViewAdapter to the RecyclerView
        binding.recyclerView.setAdapter(multiViewAdapter)

        // Attach ItemToucHelper to RecyclerView to enable drag and drop functionality
        multiViewAdapter.getItemTouchHelper()
            .attachToRecyclerView(binding.recyclerView)
    }

    private fun setUpMultiViewAdapter() {
        // Initialize the MultiViewAdapter
        multiViewAdapter = MultiViewAdapter()

        // Initialize the Sections
        listSection = ListSection<Country>()

        // Initialize the Binders
        val selectableItemBinderMessageCard = SearchFragmentSelectableItemBinder()

        // Register the Binders with the MultiViewAdapter
        multiViewAdapter.registerItemBinders(selectableItemBinderMessageCard)

        // Add Sections to the MultiViewAdapter
        multiViewAdapter.addSection(listSection)
        listSection.setSelectionMode(Mode.SINGLE)

        // Connect the MultiViewAdapter to the RecyclerView
        binding.recyclerView.adapter = multiViewAdapter

        // TODO: REPLACE WITH DATA FROM API CALL
        listSection.set(countriesSampleData)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchCountries()
    }







    // TODO: REPLACE WITH DATA FROM API CALL
    // Example Countries Data
    private val countriesSampleData = listOf(
        Country("Aruba", ""),
        Country("Afghanistan", ""),
        Country("Africa", ""),
        Country("Angola", ""),
        Country("Albania", ""),

        Country("Andorra", ""),
        Country("Argentina", ""),
        Country("Antigua and Barbuda", ""),
        Country("Australia", ""),
        Country("Azerbaijan", ""),

        Country("Austria", ""),
        Country("Azerbaijan", ""),
        Country("Belgium", ""),
        Country("Bangladesh", ""),
        Country("Bulgaria", ""),

        Country("Bahamas", ""),
        Country("Belarus", ""),
        Country("Belize", ""),
        Country("Bolivia", ""),
        Country("Brazil", ""),

        Country("Barbados", ""),
        Country("Bhutan", ""),
        Country("Botswana", ""),
        Country("Brunei", ""),
        Country("Bulgaria", ""),

        Country("Burkina Faso", ""),
        Country("Burundi", ""),
        Country("Cabo Verde", ""),
        Country("Cambodia", ""),
        Country("Cameroon", ""),

        Country("Canada", ""),
        Country("Central Afican Republic", ""),
        Country("Chad", ""),
        Country("Chile", ""),
        Country("China", ""),

        Country("Columbia", ""),
        Country("Congo", ""),
        Country("Costa Rica", ""),
        Country("Croatia", ""),
        Country("Cuba", ""),

        Country("Cyprus", ""),
        Country("Czech Republic", ""),
        Country("Denmark", ""),
        Country("Dijibouti", ""),
        Country("Dominican Republic", ""),

        Country("Ecuador", ""),
        Country("Egypt", ""),
        Country("El Salvador", ""),
        Country("Estonia", ""),
        Country("Eswatini", ""),

        Country("Ethiopia", ""),
        Country("Fiji", ""),
        Country("Finland", ""),
        Country("France", ""),
        Country("Gabon", ""),

        Country("Gambia", ""),
        Country("Georgia", ""),
        Country("Germany", ""),
        Country("Ghana", ""),
        Country("Greece", ""),

        Country("Grenada", ""),
        Country("Guatemala", ""),
        Country("Guinea", ""),
        Country("Guyana", ""),
        Country("Haiti", ""),

        Country("Honduras", ""),
        Country("Hungary", ""),
        Country("Iceland", ""),
        Country("India", ""),
        Country("Indonesia", ""),

        Country("Iran", ""),
        Country("Iraq", ""),
        Country("Ireland", ""),
        Country("Israel", ""),
        Country("Italy", ""),

        Country("Jamaica", ""),
        Country("Japan", ""),
        Country("Jordan", ""),
        Country("Kazakhstan", ""),
        Country("Kenya", ""),

        Country("Kiribati", ""),
        Country("North Korea", ""),
        Country("South Korea", ""),
        Country("Kosovo", ""),
        Country("Kuwait", ""),

        Country("Kyrgyzstan", ""),
        Country("Laos", ""),
        Country("Latvia", ""),
        Country("Lebanon", ""),
        Country("Lesotho", ""),

        Country("Liberia", ""),
        Country("Libya", ""),
        Country("Lithuania", ""),
        Country("Luxembourg", ""),
        Country("Haiti", ""),

        Country("Madagascar", ""),
        Country("Malawi", ""),
        Country("Malaysia", ""),
        Country("Maldives", ""),
        Country("Mali", ""),

        Country("Malta", ""),
        Country("Marshall Islands", ""),
        Country("Mauritania", ""),
        Country("Mauritius", ""),
        Country("Mexico", ""),

        Country("Moldova", ""),
        Country("Monaco", ""),
        Country("Mongolia", ""),
        Country("Montenegro", ""),
        Country("Morocco", ""),

        Country("Mozambique", ""),
        Country("Myanmar", ""),
        Country("Namibia", ""),
        Country("Nauru", ""),
        Country("Nepal", ""),

        Country("Netherlands", ""),
        Country("New Zealand", ""),
        Country("Nicaragua", ""),
        Country("Niger", ""),
        Country("Nigeria", ""),

        Country("Norway", ""),
        Country("Oman", ""),
        Country("Pakistan", ""),
        Country("Palau", ""),
        Country("Panama", ""),

        Country("Papua New Guinea", ""),
        Country("Paraguay", ""),
        Country("Peru", ""),
        Country("Philippines", ""),
        Country("Poland", ""),

        Country("Portugal", ""),
        Country("Qatar", ""),
        Country("Romania", ""),
        Country("Russia", ""),
        Country("Rwanda", ""),

        Country("Saudi Arabia", ""),
        Country("Serbia", ""),
        Country("Singapore", ""),
        Country("Slovakia", ""),
        Country("Slovenia", ""),

        Country("Somalia", ""),
        Country("South Africa", ""),
        Country("Spain", ""),
        Country("Sri Lanka", ""),
        Country("Sudan", ""),

        Country("Suriname", ""),
        Country("Sweden", ""),
        Country("Switzerland", ""),
        Country("Syria", ""),
        Country("Taiwan", ""),

        Country("Tanzania", ""),
        Country("Thailand", ""),
        Country("Trinidad and Tobago", ""),
        Country("Tunisia", ""),
        Country("Turkey", ""),

        Country("Turkmenistan", ""),
        Country("Uganda", ""),
        Country("Ukraine", ""),
        Country("United Arab Emirates", ""),
        Country("United Kingdom", ""),

        Country("United States", ""),
        Country("Uruguay", ""),
        Country("Venezuela", ""),
        Country("Vietnam", ""),
        Country("Zimbabwe", ""),


        )
}