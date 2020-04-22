package io.github.daeun1012.withhotels.data.remote

sealed class NetworkState constructor(val status: Status, val msg: String? = null) {

    class Loaded(status: Status = Status.SUCCESS): NetworkState(status)
    class Loading(status: Status = Status.RUNNING): NetworkState(status)
    class Error(status: Status = Status.FAILED, msg: String): NetworkState(status, msg)
}