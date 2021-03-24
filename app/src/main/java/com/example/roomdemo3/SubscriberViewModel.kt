package com.example.roomdemo3

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import com.example.roomdemo3.db.Subscriber
import com.example.roomdemo3.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository):ViewModel(),Observable {

    val subscribes = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscribeToUpdateOrDelete: Subscriber

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val safeOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        safeOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
    }
    fun safeOrUpdate(){
        if (isUpdateOrDelete){
            subscribeToUpdateOrDelete.name = inputName.value!!
            subscribeToUpdateOrDelete.email = inputEmail.value!!
            update(subscribeToUpdateOrDelete)
        }
        else {
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            insert(Subscriber(0, name, email))
            inputName.value = null
           inputEmail.value = null
        }
    }

    fun  clearALlOrDelete(){
        if (isUpdateOrDelete){
            delete(subscribeToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.insert(subscriber)
    }
    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.update(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        safeOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }
    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.delete(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        safeOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }
    fun clearAll(): Job = viewModelScope.launch {
        repository.deleteAll()
    }
    fun initUpdateOrDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscribeToUpdateOrDelete = subscriber
        safeOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}