package com.udacity.shoestore.listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListingViewModel : ViewModel() {

  var shoes = MutableLiveData<MutableList<Shoe>>()

  init {
    shoes.value = mutableListOf(
      Shoe("Puma Sports 1", 10.0, "Puma", "Sports Shoes"),
      Shoe("Puma Sports 2", 10.0, "Puma", "Sports Shoes")
    )
  }

}