package io.github.turskyi.daggerexample.registration

import dagger.Subcomponent
import io.github.turskyi.daggerexample.di.ActivityScope
import io.github.turskyi.daggerexample.registration.enterdetails.EnterDetailsFragment
import io.github.turskyi.daggerexample.registration.termsandconditions.TermsAndConditionsFragment

// Definition of a Dagger subcomponent
// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
@Subcomponent
interface RegistrationComponent {
    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}