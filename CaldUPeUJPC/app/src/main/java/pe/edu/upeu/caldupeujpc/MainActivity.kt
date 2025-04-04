package pe.edu.upeu.caldupeujpc

import androidx.compose.material3.Button
import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.caldupeujpc.ui.theme.CaldUPeUJPCTheme
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaldUPeUJPCTheme {

                }
            }
        }
    }

@Preview
@Composable
fun CalculadoraGUI(){
    var valorA by remember {  mutableStateOf("")  }
    var valorO by remember {  mutableStateOf("")  }
    var oper by remember {  mutableStateOf("")  }

    CaldUPeUJPCTheme{
        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = valorA, fontSize = 30.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))

            val buttons= listOf(
                listOf("7", "8", "9", "/" ),
                listOf("4", "5", "6", "*" ),
                listOf("1", "2", "3", "-" ),
                listOf("C", "0", "=", "+" ),
                listOf("^", "√", "1/x", "π" )

            )

            buttons.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    row.forEach { b -> Button(onClick = {
                        when(b){
                            "="->{
                                try {
                                    when(oper){
                                        "+"->{valorA=(valorO.toDouble()+valorA.toDouble()).toString()}
                                        "-"->{valorA=(valorO.toDouble()-valorA.toDouble()).toString()}
                                        "*"->{valorA=(valorO.toDouble()*valorA.toDouble()).toString()}
                                        "/"->{valorA=(valorO.toDouble()/valorA.toDouble()).toString()}
                                        "^"->valorA=valorO.toDouble().pow(valorA.toDouble()).toString()

                                    }
                                    oper=""
                                    valorO=""
                                }catch (e:Exception){valorA="Error"}
                            }
                            "+","-","*","/","^"->{
                                oper=b;
                                valorO=valorA;
                                valorA=""
                            }
                            "C"->{valorA=""
                                valorO=""
                                oper=""
                            }
                            "√" -> {
                                try {
                                    valorA = sqrt(valorA.toDouble()).toString()
                                } catch (e: Exception) {
                                    valorA = "Error"
                                }
                            }
                            "1/x" -> {
                                try {
                                    valorA = (1 / valorA.toDouble()).toString()
                                } catch (e: Exception) {
                                    valorA = "Error"
                                }
                            }
                            "π" -> {
                                valorA = Math.PI.toString()
                            }

                            else->{valorA+=b}
                        }
                    }) {
                            Text(text = b, fontSize = 24.sp)
                        }
                    }
                }
            }


        }

        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaldUPeUJPCTheme {
        Greeting("Android")
    }
}