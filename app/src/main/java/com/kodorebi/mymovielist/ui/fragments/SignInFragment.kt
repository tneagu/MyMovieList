package com.kodorebi.mymovielist.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.kodorebi.mymovielist.R
import com.kodorebi.mymovielist.app.App
import com.kodorebi.mymovielist.core.errormessages.ErrorMessages
import com.kodorebi.mymovielist.core.ui.FragmentBase
import com.kodorebi.mymovielist.ui.viewmodels.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.kodein.di.generic.instance

/**
 * Created by TNE17909 on 4/28/2020.
 * Copyright © 2019 OpenGroupe. All rights reserved.
 */
class SignInFragment : FragmentBase(R.layout.fragment_sign_in) {
    private val errorMessages: ErrorMessages by App.kodein.instance<ErrorMessages>()

    private val viewModel : SignInViewModel by navGraphViewModels(R.id.sign_in_nav_graph)


    var isLoading : Boolean = false
        set(value) {
            emailEditText.isEnabled = !value
            passwordEditText.isEnabled = !value
            signInButton.isEnabled = !value
            signInButton.visibility = if (value) View.INVISIBLE else View.VISIBLE
            loadingView.visibility = if (value) View.VISIBLE else View.GONE
            field = value
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton.setOnClickListener{ onSignInButtonClick() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        emailEditText.setText(viewModel.email)
        passwordEditText.setText(viewModel.password)

        emailEditText.addTextChangedListener(afterTextChanged = viewModel.emailTextWatcher)
        passwordEditText.addTextChangedListener(afterTextChanged = viewModel.passwordTextWatcher)

        viewModel.emailError.observe(viewLifecycleOwner, Observer { emailLayout.error = it })
        viewModel.passwordError.observe(viewLifecycleOwner, Observer { passwordLayout.error = it })

        viewModel.state.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
    }





    private fun onSignInButtonClick() {
        if (viewModel.validate(requireContext())) {
            viewModel.signIn()
        }
    }

    private fun onStateChanged(state: SignInViewModel.State) {
        when (state) {
            is SignInViewModel.State.Initial -> {
                isLoading = false
            }
            is SignInViewModel.State.SigningIn -> {
                isLoading = true
            }
            is SignInViewModel.State.SignInFailed -> {
                handleError(state.throwable)
                viewModel.goToInitialState()
            }
            is SignInViewModel.State.SignedIn -> {
                println(state.userId)
//                goToHomePage()
                Toast.makeText(context, "Sign in succes", Toast.LENGTH_SHORT).show()
                viewModel.goToInitialState()
            }
        }
    }


    private fun handleError(throwable: Throwable) {
        val message = errorMessages.getMessage(throwable, requireContext())
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}