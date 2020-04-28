package app.web.drjackycv.presentation.main

import android.os.Bundle
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavControllerId(): Int = R.id.activityMainProductsListHostFragment

}