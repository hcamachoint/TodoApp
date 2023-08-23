package com.webkingve.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private val apiService: ApiService by lazy{
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mcontext: Context
        mcontext = this


        val call = apiService.getTodo()
        call.enqueue(object: Callback<List<TodoResponse>> {
            override fun onResponse(call: retrofit2.Call<List<TodoResponse>>, response: Response<List<TodoResponse>>) {
                val dataResponse = response.body()
                if (response.code() == 200){
                    Log.i("App Response", dataResponse.toString())
                    val array: ArrayList<String> = ArrayList()
                    for (data in dataResponse!!){
                        array.add(data.title)
                    }
                    val adapter = ArrayAdapter(mcontext ,R.layout.listview_item, array)
                    val listView: ListView = findViewById(R.id.listView)
                    listView.setAdapter(adapter)
                }else{
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    val msgError = jsonObj.getString("detail") //jsonObj.getBoolean('wherever'), jsonObj.getInt('wherever'), jsonObj.getJSONObject('wherever')
                    Log.i("App Response", msgError)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<TodoResponse>>, t: Throwable) {
                Log.i("App Response", t.toString())
            }

        })
    }
}