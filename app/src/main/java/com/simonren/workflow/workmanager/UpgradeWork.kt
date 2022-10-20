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
 * @time 17:57
 * @desc
 */

class UpgradeWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("WorkSample", "doWork WORK_UPGRADE")
        requestUpgrade()
        Log.d("WorkSample", "doWork WORK_UPGRADE success")
        return Result.success()
    }

    private fun requestUpgrade() {
        ColdStartWorkManagerUtil.saveWorkToShow(ColdStartWorkManagerUtil.WORK_UPGRADE,
            UpgradeUI(ColdStartWorkManagerUtil.WORK_UPGRADE))
    }
}

//UI任务
class UpgradeUI(name: String) : BaseWorkerUI(name) {

    override fun showWork() {
        Log.d("WorkSample", "showUI WORK_UPGRADE")
        AlertDialog.Builder(WorkApp.topActivity!!)
            .setTitle("是否升级？")
            .setPositiveButton("ok") { _, _ ->
                upgrade()
            }.setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
                showNextWork()
            }.show()
    }

    private fun upgrade() {
        Log.d("WorkSample", "upgrade ing")
    }
}