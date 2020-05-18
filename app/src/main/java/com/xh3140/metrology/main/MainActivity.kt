package com.xh3140.metrology.main

import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.activity.BaseActivity
import io.github.kbiakov.codeview.classifier.CodeProcessor
import kotlinx.android.synthetic.main.activity_main.*
import org.scilab.forge.jlatexmath.core.AjLatexMath


class MainActivity : BaseActivity() {

    override fun getLayoutResID(): Int = R.layout.activity_main

    override fun initData() {
        // 数学公式
        AjLatexMath.init(this)
        CodeProcessor.init(this)
        // 底部导航
        val controller = Navigation.findNavController(this, R.id.navHostFragment)
        val configuration = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        NavigationUI.setupActionBarWithNavController(this, controller, configuration)
        NavigationUI.setupWithNavController(bottomNavigationView, controller)
    }
}