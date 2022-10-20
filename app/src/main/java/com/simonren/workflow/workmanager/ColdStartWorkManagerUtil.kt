package com.simonren.workflow.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * @author Simon
 * @date 2022/10/8
 * @time 17:44
 * @desc
 */
object ColdStartWorkManagerUtil {

    const val WORK_UPGRADE = "upgrade"
    const val WORK_ACTIVITY = "activity"

    private var mWorkChain: UIWorkChain? = null

    fun saveWorkToShow(work: String, workerUI: BaseWorkerUI) {
        val newUIWorkChain = UIWorkChain(work, workerUI)
        if (mWorkChain == null) {
            mWorkChain = newUIWorkChain
            mWorkChain!!.workBean.workerUI.showUIWork()
            return
        }
        var temp = mWorkChain
        while (temp!!.next != null) {
            temp = temp.next
        }
        temp.next = newUIWorkChain
        if (temp.workBean.waitingNextUI) {
            temp.next!!.workBean.workerUI.showUIWork()
        }
    }

    fun startWorkFlow(context: Context) {
        val upgradeWorker = OneTimeWorkRequestBuilder<UpgradeWorker>().build()
        val syncWork = OneTimeWorkRequestBuilder<SyncWorker>().build()
        val activityWork = OneTimeWorkRequestBuilder<ActivityWorker>().build()
        WorkManager.getInstance(context)
            .beginWith(upgradeWorker)
            .then(activityWork)
            .then(syncWork)
            .enqueue()
    }

    fun getWorkChain(): UIWorkChain? {
        return mWorkChain
    }

}