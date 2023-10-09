package com.work.jobs

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.Scheduler
import org.quartz.spi.TriggerFiredBundle
import kotlin.reflect.jvm.jvmName

class ProcessStateJob(
) : Job {

    override fun execute(context: JobExecutionContext?) {
        if (context == null) {
            return
        }

        val dataMap = context.jobDetail.jobDataMap
/*
        val name: String? = try {
            dataMap.getString(JOB_MAP_NAME_ID_KEY)
        } catch (e: ClassCastException) {
            null
        }

        if (name != null) {

            println(greetingMessage)
        }*/


    }

    companion object {
        const val JOB_MAP_NAME_ID_KEY = "name"
        const val WATCH_JOB_GROUP = "WatchJob"

    }
}

 fun createNewProcessStateJob(bundle: TriggerFiredBundle?, scheduler: Scheduler?): Job {
    if (bundle != null) {
        val jobClass = bundle.jobDetail.jobClass
        if (jobClass.name == ProcessStateJob::class.jvmName) {
            return ProcessStateJob()
        }
    }
    throw NotImplementedError("Job Factory error")
}
