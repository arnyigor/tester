package com.arny.tester.tests

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class SingleTestsRunnable : Testable {
    private val activeClientObservable = BehaviorSubject.create<Any>()
    override fun runTest(args: Array<String>?) {
        args?.getOrNull(0)?.let {
            if (it == "all") {
                mergeTest()
                zipTest()
            } else {
                mergeTest()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun zipTest() {
        println("zipTest ${printThread()} started")
        firstSingle()
            .zipWith(secondSingle())
            .zipWith(errorSingle("error from zip"))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                println("zipTest complete, result:$result, ${printThread()}")
            }, {
                println("zipTest error:${it.message}")
            })
    }

    @SuppressLint("CheckResult")
    private fun mergeTest() {
        println("mergeTest started ${printThread()} ")
        Single.merge(firstSingle(), secondSingle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                println("mergeTest, complete result:$result, ${printThread()}")
            }, {
                it.printStackTrace()
                println("mergeTest error:${it.message}")
            })
    }

    private fun longTimeEventWithMaybeError(
        task: String,
        time: Long,
        error: String? = null
    ): String {
        println("longTimeEventWithMaybeError task:'$task' ${printThread()}")
        Thread.sleep(time)
        error.takeIf { it.isNullOrBlank().not() }?.let { error(it) }
        return task
    }

    private fun printThread() = "current thread:${Thread.currentThread().name}"

    private fun secondSingle() =
        Single.fromCallable { longTimeEventWithMaybeError("second task", 4000) }
            .observeOn(Schedulers.io())

    private fun firstSingle() = Single.just(longTimeEventWithMaybeError("first task", 2000))
        .subscribeOn(Schedulers.io())

    private fun errorSingle(err: String? = null) =
        Single.fromCallable { longTimeEventWithMaybeError("error task", 6000, err) }
            .subscribeOn(Schedulers.io())

    private fun returnNullable(): String? {
        val random = (0..1).random()
        if (random == 1) {
            return "random:$random, Not nullable"
        }
        return null
    }

    @SuppressLint("CheckResult")
    private fun maybeTest() {
        Maybe.fromCallable {
            val returnNullable = returnNullable()
            println("returnNullable:$returnNullable")
            returnNullable
        }
            .subscribe({
                println("subscribe result:$it")
            }, {
                println("error result:" + it.message)
                it.printStackTrace()
            }, {
                println("complete result:")
            })
    }

    private fun filterTest(): Single<String> {
        return Single.just(listOf("11", "23", "344"))
            .flatMapObservable { Observable.fromIterable(it) }
            .filter { it.length == 4 }
            .firstOrError()

    }

    private fun waitActiveClient(): Completable {
        return activeClientObservable
            .firstOrError()
            .ignoreElement();
    }
}