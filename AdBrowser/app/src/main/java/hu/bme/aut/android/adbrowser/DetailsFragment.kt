package hu.bme.aut.android.adbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.adbrowser.databinding.FragmentDetailsBinding

class DetailsFragment : FragmentWithOptionsMenu() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var adDetailsViewModel: AdDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        val adId = DetailsFragmentArgs.fromBundle(requireArguments()).adId
        adDetailsViewModel = ViewModelProvider(this).get(AdDetailsViewModel::class.java)
        adDetailsViewModel.getById(adId).observe(
            viewLifecycleOwner,
            Observer { ad ->
                binding.tvTitle.text = ad?.title
                binding.tvLocation.text = ad?.location
                binding.tvDescription.text = ad?.description
                binding.tvPrice.text = "${ad?.price.toString()} Ft"
            }
        )
        return binding.root
    }
}
