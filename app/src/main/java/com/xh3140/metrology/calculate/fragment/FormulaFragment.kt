package com.xh3140.metrology.calculate.fragment

import com.xh3140.core.widgets.dialog.CircleListDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import com.xh3140.metrology.calculate.math.MathFormula
import com.xh3140.metrology.calculate.math.MathFormulaRMD
import com.xh3140.metrology.calculate.math.MathFormulaRSD
import kotlinx.android.synthetic.main.fragment_calculate_formula.*

class FormulaFragment : BaseFragment() {

    private var mMathFormula: MathFormula = MathFormulaRSD

    override fun getLayoutResID(): Int = R.layout.fragment_calculate_formula

    override fun initData() {
        setMathFormula(mMathFormula)
    }

    override fun initListener() {
        buttonSelect.setOnClickListener {
            val builder = CircleListDialog.Builder(2)
                .setTitleText("选择公式")
                .setListItems(1, listOf("相对平均偏差", "相对标准偏差"))
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, index ->
                    if (index == 1) {
                        when ((dialog as CircleListDialog).getCheckedIndex()) {
                            0 -> setMathFormula(MathFormulaRMD)
                            1 -> setMathFormula(MathFormulaRSD)
                        }
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }
    }

    internal fun getMathFormula(): MathFormula = mMathFormula

    private fun setMathFormula(formula: MathFormula) {
        mMathFormula = formula
        val chinese = formula.getChineseName()
        val english = formula.getEnglishName()
        val title = if (english.isEmpty()) chinese else "${chinese}\n${english}"
        textViewTitle.text = title
        flexibleRichTextView.setText(formula.getLatexString())
        textViewDescription.text = formula.getDescription()
    }
}
