package io.github.turskyi.daggerexample.registration

import io.github.turskyi.daggerexample.di.ActivityScope
import io.github.turskyi.daggerexample.user.UserManager
import javax.inject.Inject

//TODO: replace with real viewModel
/**
 * RegistrationViewModel is the ViewModel that the Registration flow ([RegistrationActivity]
 * and fragments) uses to keep user's input data.
 */
// @Inject tells Dagger how to provide instances of this type
// Dagger also knows that UserManager is a dependency
// Scopes this ViewModel to components that use @ActivityScope
@ActivityScope
class RegistrationViewModel @Inject constructor(val userManager: UserManager) {

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun acceptTCs() {
        acceptedTCs = true
    }

    fun registerUser() {
        if (io.github.turskyi.daggerexample.BuildConfig.DEBUG && username == null) {
            error("Assertion failed")
        }
        if (io.github.turskyi.daggerexample.BuildConfig.DEBUG && password == null) {
            error("Assertion failed")
        }
        if (io.github.turskyi.daggerexample.BuildConfig.DEBUG && acceptedTCs != true) {
            error("Assertion failed")
        }

        userManager.registerUser(username!!, password!!)
    }
}
