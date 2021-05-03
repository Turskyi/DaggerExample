package io.github.turskyi.daggerexample.di

import dagger.Component
import javax.inject.Singleton

// Replacement for AppComponent in android tests
@Singleton
// Includes TestStorageModule that overrides objects provided in StorageModule
@Component(modules = [TestStorageModule::class, AppSubcomponents::class])
interface TestAppComponent : AppComponent
