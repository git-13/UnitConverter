package eu.tutorials.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Composable
fun unitConverter(){

    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var inputExpanded by remember {mutableStateOf(false)}
    var outputExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.0)}
    val outputConversionFactor = remember { mutableStateOf(1.0)}

    fun convertUnits(){
        // ?:  - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / outputConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all UI elements will be snacked bellow each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
            inputValue = it
                convertUnits()
            },
            label = {Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Here all UI elements will be stacked next to each other
            //Input box
            Box {
                //Input Button
                Button(onClick = { inputExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Millimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output box
            Box {
                //Output Button
                Button(onClick = { outputExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            outputConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Meters"
                            outputConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Feet"
                            outputConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Millimeters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Millimeters"
                            outputConversionFactor.value = 0.001
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}



