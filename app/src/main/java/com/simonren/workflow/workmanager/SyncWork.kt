package com.simonren.workflow.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author Simon
 * @date 2022/10/8
 * @time 18:22
 * @desc
 */

class SyncWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("WorkSample", "doWork WORK_SYNC")
        dataSync()
        Log.d("WorkSample", "doWork WORK_SYNC success")
        return Result.success()
    }

    private fun dataSync() {
        Log.d("WorkSample", "dataSync ing")
    }
}