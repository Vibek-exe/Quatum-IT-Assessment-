package com.quantum.assessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.quantum.assessment.adapter.NewsAdapter
import com.quantum.assessment.viewmodel.NewsViewModel
import com.quantum.assessment.databinding.ActivityMainBinding
import com.quantum.assessment.model.Articles
import com.quantum.assessment.model.Data
import com.quantum.assessment.network.ApiInterface
import com.quantum.assessment.repository.PostRepository
import com.quantum.assessment.retrofit.ApiClient
import com.quantum.assessment.viewmodel.TestViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel

    private var articles: ArrayList<Articles>? = null

    var list: ArrayList<Articles> = ArrayList()
    private val adapter = NewsAdapter(list)

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.signIn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }*/
        binding.signOut.setOnClickListener {
            auth.signOut()
            binding.userDetails.text = updateData()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


        initListener()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.postList.observe(this, Observer {
            Log.d("TAG", "List: $it")

            articles = it.articles

            adapter.setData(articles!!)

        })
        viewModel.getAllData()

    }

    private fun initListener() {

        val apiInterface = ApiClient.getApiData()?.create(ApiInterface::class.java)
        val repository  = PostRepository(apiInterface!!)
        viewModel = ViewModelProvider(this, TestViewModelFactory(repository)).get(NewsViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        binding.userDetails.text = updateData()
    }

    private fun updateData(): String{
        return "Email : ${auth.currentUser?.email}"
    }
}