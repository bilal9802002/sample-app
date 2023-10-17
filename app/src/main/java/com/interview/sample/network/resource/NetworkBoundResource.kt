package com.interview.sample.network.resource

import com.interview.sample.data.models.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

inline fun <Entity, Model : Mapper<Entity>> networkBoundResource(
    crossinline query: () -> Flow<Entity>,
    crossinline fetch: suspend () -> Model,
    crossinline saveFetchResult: suspend (Entity) -> Unit = {},
    crossinline shouldFetch: (Entity?) -> Boolean = { true }
) = flow {

    //If shouldFetch returns true,

    val data = query().firstOrNull()

    if (data == null || shouldFetch(data)) {

        //Dispatch a message to the UI that you're doing some background work
        emit(Resource.Loading)

        try {

            //make a networking call
            val resultType = fetch()

            //save it to the database
            val mappedEntity = resultType.mapToEntity()
            saveFetchResult(mappedEntity)

            //Now fetch data again from the database and Dispatch it to the UI
            emit(Resource.Success(query().firstOrNull().takeIf{ it != null } ?: mappedEntity))

        } catch (throwable: Throwable) {
            //Dispatch any error emitted to the UI, plus data emmited from the Database
            emit(Resource.Error(throwable))
        }

        //If should fetch returned false
    } else {
        //Make a query to the database and Dispatch it to the UI.
        emit(Resource.Success(data))
    }
}