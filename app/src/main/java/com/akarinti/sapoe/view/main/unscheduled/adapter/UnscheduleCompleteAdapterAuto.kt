package com.akarinti.sapoe.view.main.unscheduled.adapter

import android.view.View
import com.akarinti.sapoe.R
import com.akarinti.sapoe.model.Unschedule
import com.akarinti.sapoe.objects.Params
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_visit.view.*


class UnscheduleCompleteAdapterAuto(data: ArrayList<Unschedule>) : BaseQuickAdapter<Unschedule, BaseViewHolder>(
    R.layout.item_visit, data) {
    override fun convert(helper: BaseViewHolder?, item: Unschedule?) {
        helper?.let { h ->
            item?.let { i ->
                h.itemView.ivType.setImageResource(
                    when(i.type) {
                        Params.TYPE_AC -> R.drawable.label_ac
                        Params.TYPE_NEONBOX -> R.drawable.label_nb
                        Params.TYPE_CLEAN -> R.drawable.label_clean
                        Params.TYPE_QC -> R.drawable.label_qc
                        Params.TYPE_MCDS -> R.drawable.label_mcds
                        else -> R.drawable.label_qc
                    })

                h.itemView.tvProcess.visibility = View.GONE
                h.itemView.ivDone.visibility = View.VISIBLE

                h.itemView.tvPlace.text = i.location?.name?:"-"
                h.itemView.tvAddress.text = i.location?.address?:"-"
            }
        }
    }
}