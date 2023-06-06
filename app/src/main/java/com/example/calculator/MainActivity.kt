package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                MyApp{
                   //MainContent()
                }
            }
        }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit){
    CalculatorTheme {
        Surface(
            color = MaterialTheme.colors.onBackground
        ) {}
       Column() {
           TopHeader()
           MainContent()

       }
    }
}
@Composable
fun TopHeader(totalPerPerson : Double=134.0 ){
    androidx.compose.material.Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = androidx.compose.foundation.shape.CircleShape.copy(all = CornerSize(10.dp))),
         color= Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total="%.2f".format(totalPerPerson)
            Text(text = "Total Per Person" ,
            style = MaterialTheme.typography.h5)
            Text(text = "$ $total",
                style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
            )

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationGraphicsApi::class)
@Composable
fun MainContent(){
    val totalBillState=remember{
        mutableStateOf("")
    }
    val validState= remember (totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController= LocalSoftwareKeyboardController.current

    androidx.compose.material.Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        color= Color(0xFFE9D7F7),
    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    border= BorderStroke(width = 1.dp, color = Color.Blue)
    ) {
       Column() {
           InputField(valueState = totalBillState,
               labelId = "Enter Bill",
               enabled = true ,
               isSingleLine = true,
           onAction = KeyboardActions{
               if(!validState) return@KeyboardActions
                keyboardController?.hide()
           })
       }

        val image =
            AnimatedImageVector.animatedVectorResource(R.drawable.p7)
        val atEnd by remember { mutableStateOf(false) }
        Icon(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = null // decorative element
        )

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        
    }
}