package com.simonren.workflow.workmanager

import com.simonren.workflow.WorkApp

/**
 * @author Simon
 * @date 2022/10/8
 * @time 17:46
 * @desc  负责把整个 UI 任务串联起来，其中workBean 记录了当前 UI 任务的名称和 BaseWorkerUI 对象。
 */
class UIWorkChain(
    workName: String,
    workerUI: BaseWorkerUI,
    val workBean: WorkBean = WorkBean(workName, workerUI),
) {
    var next: UIWorkChain? = null
}

data class WorkBean(
    val workName: String = "",
    val workerUI: BaseWorkerUI,
    var waitingNextUI: Boolean = false,
)

//UI 任务的执行与调度
abstract class BaseWorkerUI(private val workerName: String) {

    fun showNextWork() {
        var uiWorkChain = ColdStartWorkManagerUtil.getWorkChain()
        while (uiWorkChain != null) {
            if (uiWorkChain.workBean.workName == workerName) {
                if (uiWorkChain.next == null) {
                    uiWorkChain.workBean.waitingNextUI = true
                } else {
                    uiWorkChain.next!!.workBean.workerUI.showUIWork()
                }
                break
            }
            uiWorkChain = uiWorkChain.next
        }
    }

    fun showUIWork() {
        WorkApp.mainThreadExecute { showWork() }
    }

    abstract fun showWork()
}