package fr.isen.osirisnft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.osirisnft.data.Constants
import fr.isen.osirisnft.data.PublicationData
import fr.isen.osirisnft.databinding.ActivityHomeBinding
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeRequest()
    }

    fun setPubList(publications: List<PublicationData>) { // envoie les données à l'adapter pour qu'il complète les champs
        binding.listOfPublication.layoutManager = LinearLayoutManager(this)
        binding.listOfPublication.adapter = PublicationAdapter(publications) { selectedPub ->
            showDetails(selectedPub)
        }
    }

    private fun showDetails(pub: PublicationData) { // ouvre une publication en particulier
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(SELECTED_PUB, pub)
        startActivity(intent)
    }

    private fun makeRequest() { // requête json
        val queue = Volley.newRequestQueue(this)
        val url = Constants.URL
        val parameters = JSONObject()
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            parameters,
            {
                Log.d("debug", it.toString(2))
                parseResult(it)
            },
            {
                Log.d("debug", "$it")
            }
        )
        queue.add(request)
    }

    private fun parseResult(json: JSONObject) { // parse des données en une liste de publications


        // si parse ok => it de type List<PublicationData>
        // setPubList(it)
    }

    companion object {
        const val SELECTED_PUB = "SELECTED_PUB"
    }
}