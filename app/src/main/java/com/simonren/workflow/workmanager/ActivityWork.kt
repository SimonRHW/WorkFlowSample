package com.simonren.workflow.workmanager

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.simonren.workflow.WorkApp

/**
 * @author Simon
 * @date 2022/10/8
 * @time 18:35
 * @desc
 */

//后台任务
class ActivityWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("WorkSample", "doWork WORK_ACTIVITY")
        getActivity()
        Log.d("WorkSample", "doWork WORK_ACTIVITY success")
        ColdStartWorkManagerUtil.saveWorkToShow(
            ColdStartWorkManagerUtil.WORK_ACTIVITY,
            ActivityUI(ColdStartWorkManagerUtil.WORK_ACTIVITY)
        )
        return Result.success()
    }

    private fun getActivity() {
        Log.d("WorkSample", "getActivity ing")
    }
}


//UI任务
class ActivityUI(name: String) : BaseWorkerUI(name) {

    override fun showWork() {
        Log.d("WorkSample", "showUI WORK_ACTIVITY")
        AlertDialog.Builder(WorkApp.topActivity!!)
            .setTitle("这是一个活动，点击查看？")
            .setPositiveButton("ok") { dialog, which ->
                gotoActivity()
            }
            .setOnDismissListener {
                showNextWork()
            }
            .show()
    }

    private fun gotoActivity() {
        Log.d("WorkSample", "gotoActivity ing")
    }
}