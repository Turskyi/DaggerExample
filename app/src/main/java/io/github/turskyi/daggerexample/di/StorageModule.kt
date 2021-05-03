package io.github.turskyi.daggerexample.di

import dagger.Binds
import dagger.Module
import io.github.turskyi.daggerexample.storage.SharedPreferencesStorage
import io.github.turskyi.daggerexample.storage.Storage

// Tells Dagger this is a Dagger module
// Because of @Binds, StorageModule needs to be an abstract class
@Module
abstract class StorageModule {

    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}