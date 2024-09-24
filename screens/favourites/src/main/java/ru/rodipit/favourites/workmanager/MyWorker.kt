package ru.rodipit.favourites.workmanager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters


internal class MyWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    override fun doWork(): Result {

        val handler = Handler(Looper.getMainLooper())
        try {
            handler.postDelayed({
                Toast.makeText(context, "Work done!", Toast.LENGTH_SHORT).show()
            }, 1000)
        } catch (ex: Exception) {
            handler.postDelayed({
                Toast.makeText(context, "Failed. Retry..", Toast.LENGTH_SHORT).show()
            }, 1000)
            return Result.retry()
        }
        return Result.success()
    }
}