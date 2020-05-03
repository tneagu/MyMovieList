package com.kodorebi.mymovielist.ui.fragments

import androidx.navigation.navGraphViewModels
import com.kodorebi.mymovielist.R
import com.kodorebi.mymovielist.app.App
import com.kodorebi.mymovielist.core.errormessages.ErrorMessages
import com.kodorebi.mymovielist.core.ui.FragmentBase
import com.kodorebi.mymovielist.ui.viewmodels.SignInViewModel
import org.kodein.di.generic.instance

/**
 * Created by TNE17909 on 4/28/2020.
 * Copyright © 2019 OpenGroupe. All rights reserved.
 */
class SignInFragment : FragmentBase(R.layout.fragment_sign_in) {
    private val errorMessages: ErrorMessages by App.kodein.instance()

    private val viewModel : SignInViewModel by navGraphViewModels(R.id.sign_in_nav_graph)
}