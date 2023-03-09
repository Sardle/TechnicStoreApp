package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.models.TechnicResponse
import javax.inject.Inject

class Server @Inject constructor() {

    fun getAllTechnic(): List<TechnicResponse> = listOf(
        TechnicResponse(
            "Смартфон Apple iPhone 14 Pro Max 128GB (космический черный)",
            "https://shop.mts.by/upload/resize_cache/webp/iblock/23f/9l997qhse9k0fksdzi7j23l7jo2ynp6p/270_500_1/iPhone_14_Pro_Max_White_1.webp",
            "Apple iOS, экран 6.7\" OLED (1290x2796) 120 Гц, Apple A16 Bionic, ОЗУ 6 ГБ, память 128 ГБ, камера 48 Мп, аккумулятор 4323 мАч, 1 SIM (nano-SIM/eSIM), влагозащита IP68",
            3817.00
        ),
        TechnicResponse(
            "Iphone 8",
            "https://cdn1.it4profit.com/brd//app/public/models/MQ8N2/large/j/180413170207900665.jpg?0.0.3.7",
            "1qwwwwwwwwweqweeeeeeeeee",
            600.7
        ),
        TechnicResponse("Iphone x",
        "https://avatars.mds.yandex.net/get-mpic/5426148/img_id5039253332411716027.jpeg/orig",
        "mnmmnnnnnnnnnnnnnnnn",
        800.0)
    )
}