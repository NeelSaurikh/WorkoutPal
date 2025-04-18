package com.example.workoutpal

import HistoryAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutpal.databinding.ActivityBmiBinding
import com.example.workoutpal.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "HISTORY"
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val dao =(application as WorkoutApp).db.historyDao()
        getAllCompletedDates(dao)
    }

    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{ allCompletedDatesList ->
             for(i in allCompletedDatesList){
                 Log.e("Date:",""+i)
                 if(allCompletedDatesList.isNotEmpty()){
                     binding?.tvHistory?.visibility = View.VISIBLE
                     binding?.rvHistory?.visibility = View.VISIBLE
                     binding?.tvNoDataAvailable?.visibility = View.INVISIBLE

                     binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                     val dates = ArrayList<String>()
                     for(date in allCompletedDatesList){
                         dates.add(date.date)
                     }
                     val historyAdapter = HistoryAdapter(dates)

                     binding?.rvHistory?.adapter = historyAdapter
                 }else{
                     binding?.tvHistory?.visibility = View.GONE
                     binding?.rvHistory?.visibility = View.GONE
                     binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                 }
             }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}