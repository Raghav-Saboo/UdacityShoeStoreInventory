package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

  private lateinit var binding: FragmentLoginBinding

  data class Login(var email: String = "", var password: String = "")

  private val login: Login = Login()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
    binding.login = login
    binding.signUp.setOnClickListener { view ->
      handleLogin(view)
    }
    binding.signIn.setOnClickListener { view ->
      handleLogin(view)
    }
    return binding.root
  }

  private fun handleLogin(view: View) {
    binding.login?.let {
      val s: String = "Email: " + it.email + " Password: " + it.password
      Timber.i(s)
      if (it.email.isEmpty() || it.password.isEmpty()) {
        Toast.makeText(context, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show()
      } else {
        view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
      }
    }
  }

}