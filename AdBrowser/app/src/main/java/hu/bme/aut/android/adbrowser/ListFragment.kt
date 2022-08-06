package hu.bme.aut.android.adbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.adbrowser.adapter.AdRecyclerViewAdapter
import hu.bme.aut.android.adbrowser.databinding.FragmentListBinding
import hu.bme.aut.android.adbrowser.model.Ad
import hu.bme.aut.android.adbrowser.notification.NotificationHelper
import hu.bme.aut.android.adbrowser.viewmodel.AdListViewModel

class ListFragment : FragmentWithOptionsMenu(), AdRecyclerViewAdapter.TodoItemClickListener {
    private lateinit var binding: FragmentListBinding;
    private lateinit var adListViewModel: AdListViewModel
    private lateinit var adRecyclerViewAdapter: AdRecyclerViewAdapter

    private fun setupRecyclerView(view: View) {
        adRecyclerViewAdapter = AdRecyclerViewAdapter()
        adRecyclerViewAdapter.itemClickListener = this
        val rvAdList = view.findViewById<RecyclerView>(R.id.rvAdList)
        rvAdList.adapter = adRecyclerViewAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        adListViewModel = ViewModelProvider(this).get(AdListViewModel::class.java)
        adListViewModel.adList.observe(
            viewLifecycleOwner,
            Observer { adRecyclerViewAdapter.addAll(it) })
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView(binding.root)
        binding.tvWelcome.text =
            "Üdvözlünk, ${ListFragmentArgs.fromBundle(requireArguments()).nameToShow}"
        return binding.root
    }

    override fun onItemClick(ad: Ad) {
        val action = ListFragmentDirections.actionShowDetails(ad.id!!)
        findNavController().navigate(action)
    }

    override fun onItemLongClick(ad: Ad) {
        NotificationHelper.createAdDetailsNotification(requireContext(), ad)
    }

}
