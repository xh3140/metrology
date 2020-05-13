package com.xh3140.metrology.main.fragment

import android.text.InputType
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.widget.dialog.CircleContentDialog
import com.xh3140.core.widget.dialog.CircleInputDialog
import com.xh3140.core.widget.dialog.CircleListDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseFragment
import com.xh3140.metrology.calculate.CalculateActivity
import com.xh3140.metrology.jjg.JJGActivity
import com.xh3140.metrology.jjg.MrHomogeneityActivity
import kotlinx.android.synthetic.main.fragment_main_home.*
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : BaseFragment() {

    override fun getLayoutResID(): Int = R.layout.fragment_main_home

    override fun initListener() {
        // 公式算法
        buttonFormula.setOnClickListener {
            startActivity<CalculateActivity>()
        }

        // 计量器具检校
        buttonCheck.setOnClickListener {
            startActivity<JJGActivity>()
        }
        // MR图像均匀性
        buttonMrHomogeneity.setOnClickListener {
            startActivity<MrHomogeneityActivity>()
        }
        // 测试圆角对话框
        testCircleDialog1.setOnClickListener {
            val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            val date = Date(System.currentTimeMillis())
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("测试 内容对话框")
                .setSummaryText("时间${simpleDateFormat.format(date)}")
                .setContentGravity(Gravity.NO_GRAVITY)
                .setContentText(
                    "一、移动认证简介\n" +
                            "\n" +
                            "1、什么是移动认证\n" +
                            "\n" +
                            "移动认证，基于运营商独有的网关能力+大数据能力，以手机号码作为开放的统一账号体系，为各类应用提供全面的用户账号使用和用户数据管理的一站式解决方案，实现身份认证、鉴权、管理的新型认证技术。目前已推出一键登录、本机号码校验两大产品，已为爱奇艺、支付宝、小米、同花顺等多款热门APP提供服务。\n" +
                            "\n" +
                            "1.jpg\n" +
                            "\n" +
                            "2、移动认证的特点\n" +
                            "\n" +
                            "● 以手机号码作为账号，通过确认手机号码，实现用户身份唯一性的认证。\n" +
                            "\n" +
                            "● 可使用户在应用注册登录环节，耗时仅1.5秒。\n" +
                            "\n" +
                            "● 适用于在移动手机客户端注册/登录/号码校验等多种场景。\n" +
                            "\n" +
                            "● 基于运营商网络认证结果，可避免短验或密码被拦截、攻击。\n" +
                            "\n" +
                            "3、移动认证能实现哪些能力\n" +
                            "\n" +
                            "目前已推出一键登录和本机号码校验两大产品。\n" +
                            "二、移动认证能做什么\n" +
                            "\n" +
                            "1、一键登录\n" +
                            "\n" +
                            "一键登录能力，即通过移动认证的网络认证能力，实现APP" +
                            "用户无需输入帐号密码，即可使用本机手机号码自动登录的能力。利用应用层无法截取的网络层号码认证能力验证号码的真实性，本机号码自动校验是现有短信验证方式的优化，能消除现有短信验证模式等待时间长、操作繁琐和容易泄露的痛点。\n" +
                            "\n" +
                            "一键登录的能力优势\n" +
                            "\n" +
                            "● 降低应用注册/登录门槛，减轻用户记忆负担，提高用户体验；\n" +
                            "\n" +
                            "● 降低对用户身份、通信行为等属性验证的繁琐步骤，助力企业完善风险管控系统\n" +
                            "\n" +
                            "● 取号成功率高达99.8%。\n" +
                            "\n" +
                            "● 两步完成注册登录，耗时仅需1.5秒。\n" +
                            "\n" +
                            "● 节省企业短验成本"
                )
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, view, i ->
                    toast("点击了按钮“${(view as AppCompatTextView).text}”，序号为：${i}")
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }

        testCircleDialog2.setOnClickListener {
            val builder = CircleListDialog.Builder(2)
                .setTitleText("测试 单选列表对话框")
                .setListItems(List(30) { "item ${it + 1}" })
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, _ ->
                    toast("选择了列表中索引为{${(dialog as CircleListDialog).getCheckedIndex()}}的项目")
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }

        testCircleDialog3.setOnClickListener {
            val builder = CircleListDialog.Builder(2)
                .setTitleText("测试 多选列表对话框")
                .setListItems(List(30) { "item ${it + 1}" })
                .setListItemIsMultiChoice(true)
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, _ ->
                    val list = (dialog as CircleListDialog).getCheckedIndexList()
                    var text = "{"
                    for (i in list.indices) {
                        text += when (i) {
                            0 -> "${list[i]}"
                            list.size - 1 -> ",${list[i]}"
                            else -> ",${list[i]}"
                        }
                    }
                    text += "}"
                    toast("选择了列表中索引为${text}的项目")
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }

        testCircleDialog4.setOnClickListener {
            val builder = CircleInputDialog.Builder(3)
                .setTitleText("批量添加数据")
                .setInputType(InputType.TYPE_CLASS_NUMBER)
                .setInputHintText("请输入数量")
                //.setInputPadding(20,20,20,20)
                .setInputMargins(intArrayOf(20, 50, 20, 50))
                .setButtonText(0, "取消")
                .setButtonText(1, "添加")
                .setButtonText(2, "添加到")
                .setButtonOnClickListener { dialog, view, i ->
                    toast("点击了按钮“${(view as AppCompatTextView).text}”，序号为：${i}")
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }
    }
}