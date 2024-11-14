package il.rian.projectmuhammadriansyahtaskfront_endjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Menu Nasi Padang") })
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "screen1",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("screen1") { MenuScreen(navController) }
            composable("screen2") { Screen2(navController) }
            composable("about") { AboutScreen() }
            composable("detail/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: 0
                DetailScreen(navController, itemId)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Restaurant, contentDescription = "Menu 1") },
            label = { Text("Menu 1") },
            selected = false,
            onClick = { navController.navigate("screen1") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.GridOn, contentDescription = "Menu 2") },
            label = { Text("Menu 2") },
            selected = false,
            onClick = { navController.navigate("screen2") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "About") },
            label = { Text("About") },
            selected = false,
            onClick = { navController.navigate("about") }
        )
    }
}

@Composable
fun MenuScreen(navController: NavHostController) {
    val dishes = listOf(
        "Rendang", "Sate Padang", "Gulai Ikan", "Dendeng Balado", "Ayam Pop",
        "Sambal Hijau", "Kerupuk Balado", "Pisang Goreng", "Nasi Putih", "Nasi Goreng",
        "Sayur Nangka", "Ayam Penyet", "Gulai Kambing", "Tahu Tempe", "Telur Balado"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Menu Nasi Padang", style = TextStyle(fontSize = 24.sp))
        LazyColumn {
            items(dishes) { dish ->
                val itemId = dishes.indexOf(dish)
                Text(
                    text = dish,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("detail/$itemId")
                        }
                )
            }
        }
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    val dishes = listOf(
        "Rendang", "Sate Padang", "Gulai Ikan", "Dendeng Balado", "Ayam Pop",
        "Sambal Hijau", "Kerupuk Balado", "Pisang Goreng", "Nasi Putih", "Nasi Goreng",
        "Sayur Nangka", "Ayam Penyet", "Gulai Kambing", "Tahu Tempe", "Telur Balado"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Menu Nasi Padang (Grid View)", style = TextStyle(fontSize = 24.sp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dishes) { dish ->
                val itemId = dishes.indexOf(dish)
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("detail/$itemId")
                        }
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = dish, style = TextStyle(fontSize = 16.sp))
                }
            }
        }
    }
}

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Profile")
        Text(text = "Nama: Muhammad Riansyah", style = TextStyle(fontSize = 20.sp))
        Text(text = "Email: muhammadriansyahpratama@gmail.com", style = TextStyle(fontSize = 16.sp))
        Text(text = "Asal Perguruan Tinggi: Institut Teknologi Batam", style = TextStyle(fontSize = 16.sp))
        Text(text = "Jurusan: Sistem Informasi", style = TextStyle(fontSize = 16.sp))
    }
}

@Composable
fun DetailScreen(navController: NavHostController, itemId: Int) {
    val dishesDetails = listOf(
        "Rendang: Rendang adalah masakan daging sapi yang dimasak dengan cara direbus dalam santan yang kaya rempah-rempah, menciptakan kuah kental dengan rasa pedas yang mendalam. Daging dimasak perlahan hingga empuk, menyerap semua rasa dari rempah-rempah seperti serai, kunyit, dan cabai, menjadikannya salah satu hidangan paling ikonik dari Padang yang kaya akan cita rasa.",
        "Sate Padang: Sate Padang adalah sate yang terbuat dari daging sapi yang ditusuk dan dipanggang, kemudian disiram dengan kuah kacang khas Padang yang pedas dan gurih. Kuah sate Padang ini terbuat dari rempah-rempah pilihan yang memberikan rasa pedas, manis, dan gurih yang sempurna, disajikan dengan potongan lontong yang menyerap kuah dengan sempurna.",
        "Gulai Ikan: Gulai ikan adalah hidangan ikan yang dimasak dalam kuah santan kental dengan bumbu rempah-rempah yang kaya seperti kunyit, cabai, dan daun jeruk. Kuahnya yang pedas dan aromatik memberikan rasa yang khas, menjadikan ikan lebih lezat. Gulai ikan biasanya menggunakan ikan laut segar yang empuk dan penuh rasa.",
        "Dendeng Balado: Dendeng Balado adalah daging sapi yang dipotong tipis, digoreng hingga kering dan renyah, kemudian disiram dengan sambal balado yang pedas dan penuh cita rasa. Sambal balado terbuat dari cabai merah, bawang merah, dan tomat yang dihancurkan, memberikan rasa pedas, asam, dan sedikit manis yang menyatu dengan daging kering yang gurih.",
        "Ayam Pop: Ayam Pop adalah ayam yang digoreng dengan cara yang unik, menjaga kelembutan dagingnya dan memberikan rasa gurih yang lezat. Ayam ini biasanya disajikan dengan sambal pedas dan sayuran segar sebagai pelengkap, menciptakan kombinasi rasa yang segar, pedas, dan gurih.",
        "Sambal Hijau: Sambal Hijau adalah sambal yang terbuat dari cabai hijau, tomat hijau, dan bahan-bahan segar lainnya yang memberikan rasa pedas namun lebih segar dan tidak terlalu tajam. Sambal ini sangat cocok dipadukan dengan nasi putih, lauk pauk, atau bahkan sebagai pelengkap gorengan, memberikan sensasi pedas yang menyegarkan.",
        "Kerupuk Balado: Kerupuk Balado adalah kerupuk yang digoreng renyah, kemudian diberi taburan sambal balado yang pedas. Kerupuk ini memiliki tekstur yang crunchy dan rasa pedas yang tajam dari sambal balado, menjadikannya camilan yang sempurna untuk menemani hidangan Nasi Padang.",
        "Pisang Goreng: Pisang Goreng adalah pisang yang digoreng dalam adonan tepung sehingga menjadi renyah di luar namun tetap lembut di dalam. Pisang yang digunakan biasanya adalah pisang yang sudah matang, memberikan rasa manis alami yang berpadu dengan tekstur garing dari adonan tepung goreng, cocok dinikmati sebagai makanan penutup atau camilan.",
        "Nasi Putih: Nasi Putih adalah nasi yang dimasak dengan cara dipanaskan dalam air hingga matang dan lembut. Nasi putih adalah makanan pokok yang menjadi pendamping dari hidangan utama, terutama dalam hidangan Nasi Padang, yang menyerap rasa dari berbagai lauk pauk yang disajikan bersamanya.",
        "Nasi Goreng: Nasi Goreng adalah nasi yang digoreng dengan bumbu rempah-rempah seperti kecap manis, bawang putih, dan cabai. Biasanya ditambah dengan telur, ayam, dan sayuran, menciptakan rasa gurih dan sedikit manis. Nasi Goreng sering dijadikan hidangan favorit, baik sebagai sarapan atau makan malam, dengan cita rasa yang khas.",
        "Sayur Nangka: Sayur Nangka adalah hidangan sayur yang terbuat dari nangka muda yang dimasak dengan bumbu rempah, biasanya dengan kuah santan. Nangka muda yang kenyal dimasak hingga empuk, menyerap semua bumbu rempah yang digunakan, memberikan rasa gurih dan pedas yang sempurna untuk menemani nasi.",
        "Ayam Penyet: Ayam Penyet adalah ayam yang digoreng hingga garing, kemudian dihancurkan atau 'penyet' dengan ulekan dan sambal pedas. Hidangan ini menyajikan kombinasi tekstur ayam yang garing dan sambal yang pedas, memberikan sensasi rasa yang unik dan lezat yang sering disantap bersama dengan nasi putih dan lalapan.",
        "Gulai Kambing: Gulai Kambing adalah hidangan kambing yang dimasak dalam kuah santan dengan bumbu-bumbu seperti kunyit, jahe, dan cabai. Kuahnya yang kaya akan rempah memberikan rasa gurih, pedas, dan sedikit tajam yang sangat cocok dengan daging kambing yang empuk, menjadikannya hidangan yang sangat digemari di Nasi Padang.",
        "Tahu Tempe: Tahu Tempe adalah hidangan yang terdiri dari tahu dan tempe yang digoreng garing dan disajikan dengan sambal pedas. Tahu yang lembut dan tempe yang kenyal berpadu dengan sambal yang pedas dan gurih, memberikan keseimbangan rasa yang sempurna dalam setiap gigitan.",
        "Telur Balado: Telur Balado adalah telur yang digoreng hingga matang dan kemudian disiram dengan sambal balado yang pedas dan berwarna merah. Sambal balado terbuat dari cabai merah, bawang merah, dan tomat yang dihancurkan, memberikan rasa pedas dan sedikit manis yang cocok dengan telur yang kenyal."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail - ${dishesDetails[itemId].split(":")[0]}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = dishesDetails[itemId], style = TextStyle(fontSize = 18.sp))
        }
    }
}
