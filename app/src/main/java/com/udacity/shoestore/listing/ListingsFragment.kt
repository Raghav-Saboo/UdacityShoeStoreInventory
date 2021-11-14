package com.udacity.shoestore.listing

import android.graphics.Color.BLACK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingsBinding
import com.udacity.shoestore.models.Shoe

class ListingsFragment : Fragment() {

  private lateinit var binding: FragmentListingsBinding

  private val viewModel: ListingViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listings, container, false)
    setHasOptionsMenu(true)
    binding.lifecycleOwner = this

    viewModel.shoes.observe(viewLifecycleOwner, {
      for (shoe in it) {
        val layout = buildListItem(shoe)
        val params = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        params.bottomMargin = 32
        binding.shoeList.addView(layout, params)
      }
    })

    binding.addShoe.setOnClickListener { view ->
      view.findNavController().navigate(R.id.action_listingsFragment_to_detailsFragment)
    }

    return binding.root
  }

  private fun buildListItem(shoe: Shoe): LinearLayout {
    val name = TextView(this.context)
    name.setProps("Name: " + shoe.name)
    val size = TextView(this.context)
    size.setProps("Size: " + shoe.size.toString())
    val company = TextView(this.context)
    company.setProps("Company: " + shoe.company)
    val description = TextView(this.context)
    description.setProps("Description: " + shoe.description)
    val layout = LinearLayout(this.context)
    layout.orientation = VERTICAL
    layout.setPadding(32)
    layout.addView(name)
    layout.addView(size)
    layout.addView(company)
    layout.addView(description)
    return layout
  }

  private fun TextView.setProps(
    text: String,
    padding: Int = 0,
    color: Int = BLACK,
  ) {
    this.text = text
    this.setPadding(padding)
    this.textSize = 18f
    this.setTextColor(color)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.logout_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
      || super.onOptionsItemSelected(item)
  }

}