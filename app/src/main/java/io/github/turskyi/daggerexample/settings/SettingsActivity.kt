package io.github.turskyi.daggerexample.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.github.turskyi.daggerexample.MyApplication
import io.github.turskyi.daggerexample.R
import io.github.turskyi.daggerexample.login.LoginActivity
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {

    // SettingsViewModel is provided by Dagger
    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Gets the userManager from the application graph to obtain the instance
        // of UserComponent and gets this Activity injected
        val userManager = (application as MyApplication).appComponent.userManager()
        userManager.userComponent!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setupViews()
    }

    private fun setupViews() {
        findViewById<Button>(R.id.refresh).setOnClickListener {
            settingsViewModel.refreshNotifications()
        }
        findViewById<Button>(R.id.logout).setOnClickListener {
            settingsViewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}
