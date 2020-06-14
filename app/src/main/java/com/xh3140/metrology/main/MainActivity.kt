package com.xh3140.metrology.main

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.activity.BaseActivity
import io.github.kbiakov.codeview.classifier.CodeProcessor
import kotlinx.android.synthetic.main.activity_main.*
import org.scilab.forge.jlatexmath.core.AjLatexMath
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity() {

    override fun getLayoutResID(): Int = R.layout.activity_main

    override fun initData() {
        // 过期保护
        if (checkAuthorization()) {
            setContentView(View(this))
            return
        }
        // 数学公式
        AjLatexMath.init(this)
        CodeProcessor.init(this)
        // 底部导航
        val controller = Navigation.findNavController(this, R.id.navHostFragment)
        val configuration = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        NavigationUI.setupActionBarWithNavController(this, controller, configuration)
        NavigationUI.setupWithNavController(bottomNavigationView, controller)
    }

    private fun checkAuthorization(): Boolean {
        val currentDate = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        if (currentDate > dateFormat.parse("2020-10-08")) {
            val builder = CircleContentDialog.Builder(2)
                .setDialogCanceledOnTouchOutside(false)
                .setTitleText("通知")
                .setContentText("软件的授权已经过期，请与开发者联系！")
                .setButtonText(0, "确定")
                .setButtonOnClickListener { dialog, _, _ ->
                    dialog.dismiss()
                    finish()
                }
            builder.create().show(supportFragmentManager, null)
            return true
        }
        return false
    }
}