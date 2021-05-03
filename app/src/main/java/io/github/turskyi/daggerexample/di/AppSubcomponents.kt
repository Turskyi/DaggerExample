package io.github.turskyi.daggerexample.di

import dagger.Module
import io.github.turskyi.daggerexample.login.LoginComponent
import io.github.turskyi.daggerexample.registration.RegistrationComponent
import io.github.turskyi.daggerexample.user.UserComponent

// This module tells AppComponent which are its subcomponents
@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class, UserComponent::class])
class AppSubcomponents