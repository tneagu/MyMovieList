package com.kodorebi.mymovielist.ui.viewmodels

import android.content.Context
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodorebi.mymovielist.R
import com.kodorebi.mymovielist.app.App
import com.kodorebi.mymovielist.ws.firebase.FirebaseAuthentification
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.kodein.di.generic.instance

/**
 * Created by TNE17909 on 4/28/2020.
 * Copyright © 2019 OpenGroupe. All rights reserved.
 */
class SignInViewModel : ViewModel() {

    sealed class State {
        object Initial : State()
        object SigningIn : State()
        class SignInFailed(val throwable: Throwable) : State()
        class SignedIn(val userId: Int) : State()
    }

    private val firebaseAuthentication : FirebaseAuthentification by App.kodein.instance<FirebaseAuthentification>()

    private val disposables = CompositeDisposable()
    private val _emailError = MutableLiveData<String?>()
    private val _passwordError = MutableLiveData<String?>()

    private val _state : MutableLiveData<State> = MutableLiveData(
        State.Initial
    )

    var email : String = ""
    var password : String = ""

    val emailTextWatcher = { e : Editable? ->
        email = e.toString()
        if (_emailError.value != null) {
            _emailError.postValue(null)
        }
    }

    val passwordTextWatcher = { e : Editable? ->
        password = e.toString()
        if (_passwordError.value != null) {
            _passwordError.postValue(null)
        }
    }


    val emailError : LiveData<String?>
        get() {
            return _emailError
        }

    val passwordError : LiveData<String?>
        get() {
            return _passwordError
        }

    val state : LiveData<State>
        get() {
            return _state
        }

    fun goToInitialState() {
        _state.value = State.Initial
    }



    fun validate(context: Context) : Boolean {
        var isValid = true

        when {
            email.isBlank() -> {
                _emailError.value = context.getString(R.string.error_email_required)
                isValid = false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _emailError.value = context.getString(R.string.error_invalid_email)
                isValid = false
            }
            else -> _emailError.value = null
        }

        when {
            password.isEmpty() -> {
                _passwordError.value = context.getString(R.string.error_password_required)
                isValid = false
            }
            else -> _passwordError.value = null
        }

        return isValid
    }

    fun signIn(){
        _state.value = State.SigningIn

        val disposable = firebaseAuthentication.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _state.postValue(
                    State.SignedIn(1)
                )
            }, {
                _state.postValue(
                    State.SignInFailed(Throwable("SignInError"))
                )
            })

        disposables.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}