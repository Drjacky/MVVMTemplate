package app.web.drjackycv.mvvmtemplate.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalTrackerApp()
                }
            }
        }
    }
}

@Composable
fun PersonalTrackerApp() {
    var journalEntry by remember { mutableStateOf("") }
    var healthChecked by remember { mutableStateOf(false) }
    var studyChecked by remember { mutableStateOf(false) }
    var relationshipChecked by remember { mutableStateOf(false) }
    var entertainmentChecked by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "ഡെയിലി ട്രാക്കർ (LEVEND Framework)",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // ലൈഫ് ഏരിയാ 1: ആരോഗ്യം
        item {
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("ശാരീരികവും മാനസികവുമായ ആരോഗ്യം", style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Checkbox(checked = healthChecked, onCheckedChange = { healthChecked = it })
                        Text("ഭക്ഷണം, ഉപവാസം, വ്യായാമം, വിശ്രമം ഉറപ്പാക്കി")
                    }
                    OutlinedTextField(
                        value = journalEntry,
                        onValueChange = { journalEntry = it },
                        label = { Text("ഡെയിലി ജേർണൽ (സന്തോഷം/സങ്കടം)") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
            }
        }

        // ലൈഫ് ഏരിയാ 2: കരിയർ
        item {
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("കരിയർ, പഠനം, തൊഴിൽ", style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Checkbox(checked = studyChecked, onCheckedChange = { studyChecked = it })
                        Text("പ്രധാന ലക്ഷ്യം (ഉദാ: PSC / KTET പഠനം) ചെയ്തു")
                    }
                }
            }
        }

        // ലൈഫ് ഏരിയാ 3: ബന്ധങ്ങൾ
        item {
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("ബന്ധങ്ങൾ (Relationships)", style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Checkbox(checked = relationshipChecked, onCheckedChange = { relationshipChecked = it })
                        Text("കുടുംബം/സുഹൃത്തുക്കളുമായി നല്ല രീതിയിൽ ഇടപഴകി")
                    }
                }
            }
        }

        // ലൈഫ് ഏരിയാ 4: വിനോദം
        item {
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("എൻ്റർടൈൻമെൻ്റ് (വിനോദം)", style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Checkbox(checked = entertainmentChecked, onCheckedChange = { entertainmentChecked = it })
                        Text("മറ്റ് ലക്ഷ്യങ്ങളെ ബാധിക്കാത്ത രീതിയിൽ വിനോദത്തിൽ ഏർപ്പെട്ടു")
                    }
                }
            }
        }

        // സബ്മിറ്റ് ബട്ടൺ
        item {
            Button(
                onClick = { /* ഡാറ്റാബേസിലേക്ക് സേവ് ചെയ്യാനുള്ള കോഡ് ഇവിടെ നൽകണം */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ഇന്നത്തെ വിവരങ്ങൾ സേവ് ചെയ്യുക")
            }
        }
    }
}
