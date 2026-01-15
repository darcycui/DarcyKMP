package com.darcy.kmpdemo.ui.screen

//import com.darcy.kmpdemo.platform.loadImageAsBitmap

//@Composable
//fun ShowUploadImage() {
//    val scope: CoroutineScope = rememberCoroutineScope()
//    val filePath: MutableState<String> = remember { mutableStateOf("unknown") }
//    val imageBitmap: MutableState<ImageBitmap?> = remember { mutableStateOf(null) }
//    val scrollState: ScrollState = rememberScrollState()
//    val imagePicker: ImagePicker = remember { ImagePicker() }
//
//    Column(
//        modifier = Modifier.verticalScroll(scrollState).fillMaxSize(),
//        // 垂直间距
//        verticalArrangement = Arrangement.spacedBy(5.dp)
//    ) {
//        Button(onClick = { uploadFile(scope, imagePicker, filePath, imageBitmap) }) {
//            Text(text = "选择并上传图片")
//        }
//        Text(text = filePath.value)
//        imageBitmap.value?.let {
//            Image(
//                bitmap = it,
//                contentDescription = "本地图片",
//                modifier = Modifier.fillMaxSize()
//            )
//        } ?: Text("无法加载图片")
//
//    }
//}

