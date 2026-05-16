package com.example.kavyakanaja

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textToSpeech = TextToSpeech(this) { status ->

            if (status != TextToSpeech.ERROR) {

                textToSpeech.language = Locale.US
                textToSpeech.setSpeechRate(0.9f)
            }
        }

        setContent {
            KavyaKanajaApp(textToSpeech)
        }
    }

    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroy()
    }
}

@Composable
fun KavyaKanajaApp(textToSpeech: TextToSpeech) {

    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {

        "home" -> HomeScreen(
            onReadPoems = { currentScreen = "poem" },

            onTakeQuiz = { currentScreen = "quiz" },

            onListenAudio = {

                val poemText = """
                    O Nanna Chetana,
                    Aagu Nee Aniketana.
                    
                    Explore knowledge everywhere.
                """.trimIndent()

                textToSpeech.speak(
                    poemText,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "poem_audio"
                )
            }
        )

        "poem" -> PoemScreen(
            onBack = { currentScreen = "home" }
        )

        "quiz" -> QuizScreen(
            onBack = { currentScreen = "home" }
        )
    }
}

@Composable
fun HomeScreen(
    onReadPoems: () -> Unit,
    onTakeQuiz: () -> Unit,
    onListenAudio: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EBDD)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Kavya-Kanaja",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A189A)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Reviving Kannada Literature for Gen-Z",
                fontSize = 18.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = onReadPoems,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE85D04)
                ),
                modifier = Modifier.width(220.dp)
            ) {

                Text(
                    text = "Read Poems",
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onListenAudio,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7209B7)
                ),
                modifier = Modifier.width(220.dp)
            ) {

                Text(
                    text = "Listen Audio",
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onTakeQuiz,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B9348)
                ),
                modifier = Modifier.width(220.dp)
            ) {

                Text(
                    text = "Take Quiz",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun PoemScreen(
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0))
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Kuvempu Poem",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8B0000)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = """
                        ಓ ನನ್ನ ಚೇತನ,
                        ಆಗು ನೀ ಅನಿಕೇತನ
                    """.trimIndent(),
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Simple Kannada Meaning",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF006400)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = """
                    ಕವಿ ಇಲ್ಲಿ ಮನಸ್ಸು ಮತ್ತು ಚಿಂತನೆಗೆ
                    ಸ್ವತಂತ್ರವಾಗಿರು ಎಂದು ಹೇಳುತ್ತಾರೆ.
                    
                    ಯಾವ ಒಂದು ಸ್ಥಳಕ್ಕೆ ಮಾತ್ರ ಸೀಮಿತವಾಗಬೇಡ.
                    
                    ಜ್ಞಾನವನ್ನು ಎಲ್ಲೆಡೆ ಹುಡುಕು.
                """.trimIndent(),
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "English Meaning",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00008B)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = """
                    The poet tells us to keep our mind
                    free and open.
                    
                    Do not limit yourself to one place
                    or one idea.
                    
                    Explore knowledge everywhere.
                """.trimIndent(),
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB22222)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Back to Home",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun QuizScreen(
    onBack: () -> Unit
) {

    var selectedAnswer by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FFF8))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Kannada Literature Quiz",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF006400)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Who wrote 'O Nanna Chetana'?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                val options = listOf(
                    "Kuvempu",
                    "Bendre",
                    "Pampa",
                    "Kalidasa"
                )

                options.forEach { option ->

                    Button(
                        onClick = {
                            selectedAnswer = option
                            showResult = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {

                        Text(option)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (showResult) {

            if (selectedAnswer == "Kuvempu") {

                Text(
                    text = "Correct Answer 🎉",
                    fontSize = 24.sp,
                    color = Color(0xFF008000),
                    fontWeight = FontWeight.Bold
                )

            } else {

                Text(
                    text = "Wrong Answer ❌",
                    fontSize = 24.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB22222)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Back to Home",
                fontSize = 18.sp
            )
        }
    }
}