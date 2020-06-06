package com.xh3140.metrology.appliance.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.core.extensions.startActivity
import com.xh3140.core.extensions.toast
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.document.JJG9612017Document
import com.xh3140.metrology.appliance.document.StandardDocument
import com.xh3140.metrology.jjg.jjg9612017.JJG9612017Activity
import kotlinx.android.synthetic.main.item_appliance_index.view.*


class IndexAdapter(val activity: FragmentActivity) : ListAdapter<StandardDocument, IndexAdapter.ViewHolder>(DiffCallBack) {

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
        holder.itemView.textViewReferenceDocumentsValue.text = getItem(position).referenceDocuments.joinToString()
        holder.itemView.textViewSupersededDocumentsValue.text = getItem(position).supersededDocuments.joinToString()
        holder.itemView.textViewAdoptDocumentsValue.text = getItem(position).adoptDocuments.joinToString()
    }

    object DiffCallBack : DiffUtil.ItemCallback<StandardDocument>() {
        override fun areItemsTheSame(oldItem: StandardDocument, newItem: StandardDocument): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StandardDocument, newItem: StandardDocument): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mExpandHeight: Int = 0
        private val mHeaderView: View = itemView.linearLayoutHeader
        private val mImageView: ImageView = itemView.imageViewArrow
        private val mExpandView: View = itemView.tabLayoutExpand
        private val mShowAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100)
        private val mHideAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100)

        init {
            mExpandView.post {
                mExpandHeight = mExpandView.height
                mExpandView.visibility = View.GONE
            }
            configShowValueAnimator()
            configHideValueAnimator()
            mHeaderView.setOnClickListener {
                if (!mShowAnimator.isRunning && !mHideAnimator.isRunning) {
                    if (mExpandView.visibility == View.GONE) {
                        mShowAnimator.start()
                    } else {
                        mHideAnimator.start()
                    }
                }
            }
            itemView.textViewOnlineRead.setOnClickListener {
                val document = getItem(adapterPosition)
                val number = document.number.substring(4)
                if (number.isEmpty()) {
                    return@setOnClickListener
                }
                val builder = CircleContentDialog.Builder(2)
                    .setTitleText("在线阅读")
                    .setSummaryText(document.chineseName)
                    .setContentText("请问是否打开浏览器在线阅读文件？")
                    .setButtonText(0, "取消")
                    .setButtonText(1, "确定")
                    .setButtonOnClickListener { dialog, _, i ->
                        if (i == 1) {
                            val url = "http://jjg.spc.org.cn/resmea/standard/JJG%2520${number}/?"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            itemView.context.startActivity(intent)
                        }
                        dialog.dismiss()
                    }
                builder.create().show(activity.supportFragmentManager, null)
            }

            itemView.textViewDetailedInformation.setOnClickListener {
                when (getItem(adapterPosition)) {
                    is JJG9612017Document -> activity.startActivity<JJG9612017Activity>()
                    else -> activity.toast("暂无启用")
                }
            }
        }


        private fun View.setLayoutHeight(height: Int) {
            val newLayoutParams = layoutParams
            newLayoutParams.height = height
            layoutParams = newLayoutParams
        }

        private fun configShowValueAnimator() {
            mShowAnimator.apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 200L
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        mImageView.rotation = 0f
                        mImageView.setImageResource(R.drawable.ic_arrow_down)
                        mExpandView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        mImageView.rotation = 0f
                        mExpandView.setLayoutHeight(0)
                        mExpandView.visibility = View.VISIBLE
                    }
                })
                addUpdateListener {
                    val value = it.animatedValue as Int
                    mImageView.rotation = 90f * value / 100
                    mExpandView.setLayoutHeight(value * mExpandHeight / 100)
                }
            }
        }

        private fun configHideValueAnimator() {
            mHideAnimator.apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 200L
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        mImageView.rotation = 0f
                        mImageView.setImageResource(R.drawable.ic_arrow_right)
                        mExpandView.setLayoutHeight(0)
                        mExpandView.visibility = View.GONE
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        mImageView.rotation = 0f
                        mExpandView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                    }
                })
                addUpdateListener {
                    val value = it.animatedValue as Int
                    mImageView.rotation = -90f * value / 100
                    mExpandView.setLayoutHeight((100 - value) * mExpandHeight / 100)
                }
            }
        }
    }
}