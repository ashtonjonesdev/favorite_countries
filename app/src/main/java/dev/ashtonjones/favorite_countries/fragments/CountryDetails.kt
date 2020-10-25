package dev.ashtonjones.favorite_countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.ashtonjones.favorite_countries.R
import dev.ashtonjones.favorite_countries.databinding.FragmentCountryDetailsBinding
import dev.ashtonjones.favorite_countries.databinding.FragmentHomeBinding
import dev.ashtonjones.favorite_countries.datalayer.viewmodel.FavoriteCountriesViewModel
import dev.ashtonjones.favorite_countries.datamodels.Country

class CountryDetails : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var favoriteCountriesViewModel: FavoriteCountriesViewModel

    val args: CountryDetailsArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        // SETUP VIEWMODEL
        setUpViewModel()

        setTextViewData()
    }

    private fun setTextViewData() {
        // Set the text view and hint of edit text view using the value passed from the Country Search Fragment
        binding.countryTextView.text = args.countryTitleArg
        binding.textInputEditTextCountryDetails.hint = "My favorite things about ${args.countryTitleArg}"
    }

    private fun setUpViewModel() {
        favoriteCountriesViewModel =
            ViewModelProvider(requireActivity())[FavoriteCountriesViewModel::class.java]
    }

    private fun setClickListeners() {
        binding.addCountryFAB.setOnClickListener(View.OnClickListener {

            if(binding.textInputEditTextCountryDetails.text == null || binding.textInputEditTextCountryDetails.length() == 0) {

                Toast.makeText(context, "Please enter a note about this country", Toast.LENGTH_SHORT).show()

            }

            else {

                // Create the new Country using the entered information

                var newTitle = binding.countryTextView.text.toString()
                var newNotes = binding.textInputEditTextCountryDetails.text.toString()

                var newCountry = Country(newTitle, newNotes)

                // Save the country to the database
                favoriteCountriesViewModel.insert(newCountry)

                // Go all the way back to Home Fragment
                findNavController().popBackStack(R.id.home_fragment_dest, false)

            }

        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CountryDetails()
    }
}