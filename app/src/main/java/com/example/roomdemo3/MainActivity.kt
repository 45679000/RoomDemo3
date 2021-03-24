package com.example.roomdemo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo3.databinding.ActivityMainBinding
import com.example.roomdemo3.db.Subscriber
import com.example.roomdemo3.db.SubscriberDao
import com.example.roomdemo3.db.SubscriberDatabase
import com.example.roomdemo3.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao: SubscriberDao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel =  ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscriber()
    }
    private fun displaySubscriber(){
        subscriberViewModel.subscribes.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it,{selectedItem:Subscriber ->listItemClicked(selectedItem)})

        })
    }
    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"Selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateOrDelete(subscriber)
    }

}