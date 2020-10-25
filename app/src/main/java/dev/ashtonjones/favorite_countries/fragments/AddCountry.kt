package dev.ashtonjones.favorite_countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.ashtonjones.favorite_countries.databinding.FragmentAddCountryBinding
import dev.ashtonjones.favorite_countries.databinding.FragmentHomeBinding
import dev.ashtonjones.favorite_countries.datalayer.viewmodel.FavoriteCountriesViewModel
import dev.ashtonjones.favorite_countries.datamodels.Country


/**
 * A simple [Fragment] subclass.
 * Use the [AddCountry.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCountry : Fragment() {

    private var _binding: FragmentAddCountryBinding? = null

    private val binding get() = _binding!!

    private lateinit var favoriteCountriesViewModel: FavoriteCountriesViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCountryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setClickListeners()

        // SETUP VIEWMODEL
        setUpViewModel()
    }

    private fun setUpViewModel() {
        favoriteCountriesViewModel =
            ViewModelProvider(requireActivity())[FavoriteCountriesViewModel::class.java]
    }

    private fun setClickListeners() {

        binding.saveNewCountryFAB.setOnClickListener(View.OnClickListener {

            if(binding.textInputEditTextTitleAddNewMessage.text == null || binding.textInputEditTextTitleAddNewMessage.length() == 0 || binding.textInputEditTextMessageAddNewMessage.text == null || binding.textInputEditTextMessageAddNewMessage.length() == 0) {

                Toast.makeText(context, "Please enter a title and a message", Toast.LENGTH_SHORT).show()

            }

            else {

                // Create the new Country using the entered information

                var newTitle = binding.textInputEditTextTitleAddNewMessage.text.toString()
                var newNotes = binding.textInputEditTextMessageAddNewMessage.text.toString()

                var newCountry = Country(newTitle, newNotes)

                // Save the country to the database
                favoriteCountriesViewModel.insert(newCountry)

                // Pop the destination from the backstack
                findNavController().popBackStack()

            }

        })


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddCountry()
    }
}