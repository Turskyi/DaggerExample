package io.github.turskyi.daggerexample.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.github.turskyi.daggerexample.login.LoginComponent
import io.github.turskyi.daggerexample.registration.RegistrationComponent
import io.github.turskyi.daggerexample.user.UserManager
import javax.inject.Singleton

// Definition of a Dagger component that adds info from the StorageModule to the graph
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Types that can be retrieved from the graph
    // Expose RegistrationComponent factory from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    //  Expose UserManager so that MainActivity and SettingsActivity
    // can access a particular instance of UserComponent
    fun userManager(): UserManager
}