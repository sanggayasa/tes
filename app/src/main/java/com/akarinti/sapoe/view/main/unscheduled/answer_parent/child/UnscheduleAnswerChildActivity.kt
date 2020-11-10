package com.akarinti.sapoe.view.main.unscheduled.answer_parent.child

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.akarinti.sapoe.R
import com.akarinti.sapoe.base.BaseMvpActivity
import com.akarinti.sapoe.model.questionnaire.ParentAcOffline
import com.akarinti.sapoe.model.questionnaire.ParentCleanOffline
import com.akarinti.sapoe.model.questionnaire.ParentNbOffline
import com.akarinti.sapoe.model.questionnaire.ParentOtherOffline
import com.akarinti.sapoe.objects.Params
import com.akarinti.sapoe.view.main.unscheduled.answer_parent.question.UAnswerActivity
import com.akarinti.sapoe.view.main.visit.answer_parent.adapter.AnswerAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.toolbar_navigation.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class UnscheduleAnswerChildActivity: BaseMvpActivity<UnscheduleAnswerChildPresenter>(), UnscheduleAnswerChildContract.View {

    companion object {
        fun newInstance(activity: Activity, parent: ParentAcOffline?, scheduleType: String?, selectedChildIndex: Int?) {
            activity.startActivity(activity.intentFor<UnscheduleAnswerChildActivity>(
                Params.BUNDLE_PARENT_AC to parent,
                Params.BUNDLE_UNSCHEDULE_TYPE to scheduleType,
                Params.BUNDLE_UNSCHEDULE_CHILD_IDX to selectedChildIndex
            ))
        }
        fun newInstance(activity: Activity, parent: ParentNbOffline?, scheduleType: String?, selectedChildIndex: Int?) {
            activity.startActivity(activity.intentFor<UnscheduleAnswerChildActivity>(
                Params.BUNDLE_PARENT_NB to parent,
                Params.BUNDLE_UNSCHEDULE_TYPE to scheduleType,
                Params.BUNDLE_UNSCHEDULE_CHILD_IDX to selectedChildIndex
            ))
        }
        fun newInstance(activity: Activity, parent: ParentCleanOffline?, scheduleType: String?, selectedChildIndex: Int?) {
            activity.startActivity(activity.intentFor<UnscheduleAnswerChildActivity>(
                Params.BUNDLE_PARENT_CLEAN to parent,
                Params.BUNDLE_UNSCHEDULE_TYPE to scheduleType,
                Params.BUNDLE_UNSCHEDULE_CHILD_IDX to selectedChildIndex
            ))
        }
        fun newInstance(activity: Activity, parent: ParentOtherOffline?, scheduleType: String?, selectedChildIndex: Int?) {
            activity.startActivity(activity.intentFor<UnscheduleAnswerChildActivity>(
                Params.BUNDLE_PARENT_OTHER to parent,
                Params.BUNDLE_UNSCHEDULE_TYPE to scheduleType,
                Params.BUNDLE_UNSCHEDULE_CHILD_IDX to selectedChildIndex
            ))
        }
    }

    private var scheduleType: String? = null
    private var childIndex: Int = -1

    private var dataAc: ParentAcOffline? = null
    private var dataNb: ParentNbOffline? = null
    private var dataCl: ParentCleanOffline? = null
    private var dataOther: ParentOtherOffline? = null
    private lateinit var adapterQuestion: AnswerAdapter

    @Inject
    override lateinit var presenter: UnscheduleAnswerChildPresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        initBundle()
        initView()
        initAction()
        initData()
    }

    override fun getLayout(): Int = R.layout.activity_info

    private fun initBundle() {
        intent?.extras?.let {
            dataAc = it.getParcelable(Params.BUNDLE_PARENT_AC)
            dataNb = it.getParcelable(Params.BUNDLE_PARENT_NB)
            dataCl = it.getParcelable(Params.BUNDLE_PARENT_CLEAN)
            dataOther = it.getParcelable(Params.BUNDLE_PARENT_OTHER)
            scheduleType = it.getString(Params.BUNDLE_UNSCHEDULE_TYPE)
            childIndex = it.getInt(Params.BUNDLE_UNSCHEDULE_CHILD_IDX)
        }
    }

    private fun initView() {
        tvTitle.text = getString(R.string.kunjungan_info)
        tvStatus.visibility = View.INVISIBLE
        ivFinish.visibility = View.VISIBLE
        tabButton.visibility = View.INVISIBLE
        baseContainer.visibility = View.GONE
    }

    private fun initAction() {
        ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initData() {
        if (null != scheduleType && childIndex != -1) {
            when (scheduleType) {
                Params.TYPE_AC -> {
                    dataAc?.let {
                        tvName.text = String.format(getString(R.string.name_ac_fmt), it.location?.name ?: "-", childIndex + 1)
                        tvInfo.text = String.format(getString(R.string.serial_no_fmt), it.itemList?.get(childIndex)?.indoorSerialNumber ?: "-")
                        adapterQuestion = AnswerAdapter(it.itemList?.get(childIndex)?.formList?: listOf())
                        adapterQuestion.setOnItemClickListener { _, _, position ->
                            UAnswerActivity.newInstance(this, it, scheduleType, position, childIndex)
                        }
                        initQuestionList()
                    }
                }
                Params.TYPE_NEONBOX -> {
                    dataNb?.let {
                        tvName.text = String.format(getString(R.string.name_nb_fmt), it.location?.name ?: "-", childIndex + 1)
                        tvInfo.text = String.format(getString(R.string.tipe_nb_fmt), it.itemList?.get(childIndex)?.type ?: "-")
                        adapterQuestion = AnswerAdapter(it.itemList?.get(childIndex)?.formList?: listOf())
                        adapterQuestion.setOnItemClickListener { _, _, position ->
                            UAnswerActivity.newInstance(this, it, scheduleType, position, childIndex)
                        }
                        initQuestionList()
                    }
                }
                Params.TYPE_CLEAN -> {
                    dataCl?.let {
                        tvName.text = String.format(getString(R.string.name_cl_fmt), it.location?.name ?: "-", childIndex + 1)
                        tvInfo.text = String.format(getString(R.string.wsid_fmt), it.itemList?.get(childIndex)?.wsid ?: "-")
                        adapterQuestion = AnswerAdapter(it.itemList?.get(childIndex)?.formList?: listOf())
                        adapterQuestion.setOnItemClickListener { _, _, position ->
                            UAnswerActivity.newInstance(this, it, scheduleType, position, childIndex)
                        }
                        initQuestionList()
                    }
                }
                Params.TYPE_MCDS -> {
                    dataOther?.let {
                        tvName.text = String.format(getString(R.string.name_cl_fmt), it.location?.name ?: "-", childIndex + 1)
                        tvInfo.text = String.format(getString(R.string.wsid_fmt), it.itemList?.get(childIndex)?.wsid ?: "-")
                        adapterQuestion = AnswerAdapter(it.itemList?.get(childIndex)?.formList?: listOf())
                        adapterQuestion.setOnItemClickListener { _, _, position ->
                            UAnswerActivity.newInstance(this, it, scheduleType, position, childIndex)
                        }
                        initQuestionList()
                    }
                }
                Params.TYPE_QC -> {
                    dataOther?.let {
                        tvName.text = String.format(getString(R.string.name_cl_fmt), it.location?.name ?: "-", childIndex + 1)
                        tvInfo.text = String.format(getString(R.string.wsid_fmt), it.itemList?.get(childIndex)?.wsid ?: "-")
                        adapterQuestion = AnswerAdapter(it.itemList?.get(childIndex)?.formList?: listOf())
                        adapterQuestion.setOnItemClickListener { _, _, position ->
                            UAnswerActivity.newInstance(this, it, scheduleType, position, childIndex)
                        }
                        initQuestionList()
                    }
                }
            }
        }
    }

    private fun initQuestionList() {
        rvQuestion.apply {
            layoutManager = LinearLayoutManager(this@UnscheduleAnswerChildActivity)
            adapter = adapterQuestion
            isNestedScrollingEnabled = false
        }
    }
}