package com.example.roomdemo3.db

class SubscriberRepository(private val dao: SubscriberDao) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) :Long{
        return dao.insertSubscriber(subscriber)
    }
    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }
    suspend fun deleteAll(){
        dao.clear()
    }
    suspend fun delete(subscriber: Subscriber){
        dao.deleteAll(subscriber)
    }
}