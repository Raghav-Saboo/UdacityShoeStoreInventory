package com.udacity.shoestore.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseMethod
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.listing.ListingViewModel
import com.udacity.shoestore.models.Shoe

class DetailsFragment : Fragment() {

  private lateinit var binding: FragmentDetailsBinding

  val shoe: Shoe = Shoe("", 0.0, "", "")

  private val viewModel: ListingViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(
      inflater, R.layout.fragment_details, container, false)
    binding.shoe = shoe
    binding.save.setOnClickListener { view ->
      viewModel.shoes.value?.add(shoe)
      view.findNavController().navigate(R.id.action_detailsFragment_to_listingsFragment)
    }
    binding.cancel.setOnClickListener { view ->
      view.findNavController().navigate(R.id.action_detailsFragment_to_listingsFragment)
    }
    return binding.root
  }

}