package io.github.turskyi.daggerexample

import io.github.turskyi.daggerexample.di.AppComponent
import io.github.turskyi.daggerexample.di.DaggerTestAppComponent

/**
 * MyTestApplication will override MyApplication in android tests
 */
class MyTestApplication : MyApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fakes types
        return DaggerTestAppComponent.create()
    }
}
