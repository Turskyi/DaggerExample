package io.github.turskyi.daggerexample.di
import dagger.Binds
import dagger.Module
import io.github.turskyi.daggerexample.storage.FakeStorage
import io.github.turskyi.daggerexample.storage.Storage

// Overrides StorageModule in android tests
@Module
abstract class TestStorageModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: FakeStorage): Storage
}
