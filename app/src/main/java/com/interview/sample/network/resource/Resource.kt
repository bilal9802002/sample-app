package com.interview.sample.network.resource

sealed interface Resource<out T> {
    class Success<T>(val data: T) : Resource<T>
    class Error(val throwable: Throwable) : Resource<Nothing>
    object Loading : Resource<Nothing>
}