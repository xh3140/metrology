package com.xh3140.metrology.main

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.activity.BaseActivity
import io.github.kbiakov.codeview.classifier.CodeProcessor
import kotlinx.android.synthetic.main.layout_main_navigation_home.*
import kotlinx.android.synthetic.main.layout_main_navigation_settings.*
import kotlinx.android.synthetic.main.layout_main_navigation_tools.*
import org.scilab.forge.jlatexmath.core.AjLatexMath
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {

    override fun getLayoutResID(): Int = R.layout.activity_main

    override fun initData() {
        // 数学公式
        AjLatexMath.init(this)
        CodeProcessor.init(this)
        // 获取当前时间
        getCurrentTimeMillis()
        // 底部导航
        configBottomNavigation()
    }

    private fun getCurrentTimeMillis() {
        try {
            //请求网络资源是耗时操作。放到子线程中进行
            Thread(Runnable {
                val url = URL("http://www.baidu.com")
                val connection = url.openConnection()
                connection.readTimeout = 5000
                connection.connectTimeout = 5000
                connection.connect()
                checkAuthorization(connection.date)
            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
            checkAuthorization(System.currentTimeMillis())
        }
    }

    private fun checkAuthorization(timeMillis: Long) {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        format.timeZone = TimeZone.getTimeZone("Asia/Shanghai")
        val expiryDate = format.parse("2020-11-11 00:00:00")
        if (timeMillis > expiryDate?.time ?: 0) {
            val builder = CircleContentDialog.Builder(1)
                .setDialogCanceledOnTouchOutside(false)
                .setTitleText("通知")
                .setContentText("软件的授权已经过期，请与开发者联系！")
                .setButtonText(0, "确定")
                .setButtonOnClickListener { dialog, _, _ ->
                    dialog.dismiss()
                    finish()
                }
            builder.create().show(supportFragmentManager, null)
        }
    }

    private fun configBottomNavigation() {
        val destinationMap = mapOf(
            R.id.fragmentHome to motionLayoutHome,
            R.id.fragmentTools to motionLayoutTools,
            R.id.fragmentSettings to motionLayoutSettings
        )
        val navController = findNavController(R.id.fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(destinationMap.keys))
        destinationMap.forEach { map ->
            map.value.setOnClickListener { navController.navigate(map.key) }
        }
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0.00001f }
            destinationMap[destination.id]?.transitionToEnd()
        }
    }
}