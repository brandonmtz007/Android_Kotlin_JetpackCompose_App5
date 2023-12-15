package com.example.tessdtstdstst

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    FrutList()
                }
            }
        }


data class Frut(
    val nombre: String,
    val descripcion: String,
    val image: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrutList() {
    var frutName by remember {
        mutableStateOf("")
    }

    var frutDescription by remember {
        mutableStateOf("")
    }

    var frutImage by remember {
        mutableStateOf("")
    }

    val fruts = remember {
        mutableStateListOf<Frut>()
    }
    val context = LocalContext.current

    Column {
        TextField(
            value = frutName,
            label = { Text(text = "Nombre:") },
            onValueChange = { frutName = it },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = frutDescription,
            label = { Text(text = "Descripcion:") },
            onValueChange = { frutDescription = it },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = frutImage,
            label = { Text(text = "ImageUrl:") },
            onValueChange = { frutImage = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            if (frutName.isNotEmpty() && frutDescription.isNotEmpty() && frutImage.isNotEmpty()){
                val newFrut = Frut(frutName,frutDescription,frutImage)
                fruts.add(newFrut)
                frutName = ""
                frutDescription=""
                frutImage = ""
                Toast.makeText(context,"Se agrego correctamente", Toast.LENGTH_SHORT).show()
            }
        },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Agregar")
        }

        LazyColumn{
            items(fruts) {
                    frut ->
                Card(
                    modifier = Modifier.padding(5.dp)
                ){
                    Column(Modifier.padding(5.dp)){
                        Row(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(model = frut.image, contentDescription = null, modifier = Modifier.size(100.dp))
                            Column(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "nombre:${frut.nombre}")
                                Text(text = "Descripcion: ${frut.descripcion}")
                            }
                        }
                    }
                }
            }
        }
    }
}