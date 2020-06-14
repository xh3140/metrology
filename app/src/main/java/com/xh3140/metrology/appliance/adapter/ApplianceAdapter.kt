package com.xh3140.metrology.appliance.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.ApplianceActivity
import com.xh3140.metrology.appliance.document.StandardDocument
import kotlinx.android.synthetic.main.item_appliance_index.view.*


class ApplianceAdapter(val activity: ApplianceActivity) : ListAdapter<StandardDocument, ApplianceAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_appliance_index, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewNumber.text = getItem(position).number
        holder.itemView.textViewName.text = getItem(position).chineseName
        holder.itemView.textViewChineseNameValue.text = getItem(position).chineseName
        holder.itemView.textViewEnglishNameValue.text = getItem(position).englishName
        holder.itemView.textViewDocumentTypeValue.text = getItem(position).type.name
        holder.itemView.textViewDocumentStateValue.text = getItem(position).state.text
        holder.itemView.textViewPublishDateValue.text = getItem(position).publishDate
        holder.itemView.textViewExecuteDateValue.text = getItem(position).executeDate
        holder.itemView.textViewReplaceDocumentsValue.text = getItem(position).replaceDocuments.joinToString()
    }

    /**
     * 比较器
     */
    object DiffCallBack : DiffUtil.ItemCallback<StandardDocument>() {
        override fun areItemsTheSame(oldItem: StandardDocument, newItem: StandardDocument): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StandardDocument, newItem: StandardDocument): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * 回调接口
     */
    interface OnClickDocumentListener {
        /**
         * 在线阅读
         */
        fun onClickOnLineReading(document: StandardDocument)

        /**
         * 详细信息
         */
        fun onClickDetailedInformation(document: StandardDocument)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mExpandHeight: Int = 0
        private val mShowAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100)
        private val mHideAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100)

        init {
            configShowValueAnimator()
            configHideValueAnimator()
            // 展开与收起
            itemView.linearLayoutHeader.setOnClickListener {
                if (!mShowAnimator.isRunning && !mHideAnimator.isRunning) {
                    if (itemView.tabLayoutExpand.visibility == View.GONE) {
                        mShowAnimator.start()
                    } else {
                        mHideAnimator.start()
                    }
                }
            }
            // 在线阅读
            itemView.textViewOnlineRead.setOnClickListener {
                activity.onClickOnLineReading(getItem(adapterPosition))
            }
            // 详细信息
            itemView.textViewDetailedInformation.setOnClickListener {
                activity.onClickDetailedInformation(getItem(adapterPosition))
            }
        }


        private fun View.setLayoutHeight(height: Int) {
            val newLayoutParams = layoutParams
            newLayoutParams.height = height
            layoutParams = newLayoutParams
        }

        /**
         * 配置展开动画
         */
        private fun configShowValueAnimator() {
            mShowAnimator.apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 200L
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        itemView.imageViewArrow.rotation = 0f
                        itemView.imageViewArrow.setImageResource(R.drawable.ic_arrow_down)
                        itemView.tabLayoutExpand.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        if (mExpandHeight == 0) {
                            mExpandHeight = itemView.tabLayoutExpand.height
                        }
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        itemView.imageViewArrow.rotation = 0f
                        itemView.tabLayoutExpand.setLayoutHeight(0)
                        itemView.tabLayoutExpand.visibility = View.VISIBLE
                    }
                })
                addUpdateListener {
                    val value = it.animatedValue as Int
                    itemView.imageViewArrow.rotation = 90f * value / 100
                    itemView.tabLayoutExpand.setLayoutHeight(value * mExpandHeight / 100)
                }
            }
        }

        /**
         * 配置收起动画
         */
        private fun configHideValueAnimator() {
            mHideAnimator.apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 200L
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        itemView.imageViewArrow.rotation = 0f
                        itemView.imageViewArrow.setImageResource(R.drawable.ic_arrow_right)
                        itemView.tabLayoutExpand.setLayoutHeight(0)
                        itemView.tabLayoutExpand.visibility = View.GONE
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        itemView.imageViewArrow.rotation = 0f
                        itemView.tabLayoutExpand.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        if (mExpandHeight == 0) {
                            mExpandHeight = itemView.tabLayoutExpand.height
                        }
                    }
                })
                addUpdateListener {
                    val value = it.animatedValue as Int
                    itemView.imageViewArrow.rotation = -90f * value / 100
                    itemView.tabLayoutExpand.setLayoutHeight((100 - value) * mExpandHeight / 100)
                }
            }
        }
    }
}