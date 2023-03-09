package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.models.TechnicResponse
import javax.inject.Inject

class Server @Inject constructor() {

    fun getAllTechnic(): List<TechnicResponse> = listOf(
        TechnicResponse(
            "Смартфон Apple iPhone 14 Pro Max 128GB (космический черный)",
            "https://shop.mts.by/upload/resize_cache/webp/iblock/23f/9l997qhse9k0fksdzi7j23l7jo2ynp6p/270_500_1/iPhone_14_Pro_Max_White_1.webp",
            "Apple iOS, экран 6.7\" OLED (1290x2796) 120 Гц, Apple A16 Bionic, ОЗУ 6 ГБ, память 128 ГБ, камера 48 Мп, аккумулятор 4323 мАч, 1 SIM (nano-SIM/eSIM), влагозащита IP68",
            3817.00,
            "Телефоны"
        ),
        TechnicResponse(
            "Iphone 8",
            "https://cdn1.it4profit.com/brd//app/public/models/MQ8N2/large/j/180413170207900665.jpg?0.0.3.7",
            "1qwwwwwwwwweqweeeeeeeeee",
            600.7,
            "Телефоны"
        ),
        TechnicResponse(
            "Iphone x",
            "https://avatars.mds.yandex.net/get-mpic/5426148/img_id5039253332411716027.jpeg/orig",
            "mnmmnnnnnnnnnnnnnnnn",
            800.0,
            "Телефоны"
        ),
        TechnicResponse(
            "Apple iPad Pro 12.9",
            "https://cdn21vek.by/img/galleries/6500/932/preview_b/ipadpro129wifi128gbmhnf3_apple_60ae6f9e30c8e.jpeg",
            "iejrgjerjeroigjerjeoijelknnsv,jdfjvnjnsnvd,fvndfndjnbkjnekfvlernlkenblermlirnjgioerg",
            3000.0,
            "Планшеты"
        ),
        TechnicResponse(
            "Apple iPad 10.2",
            "https://img.5element.by/import/images/ut/goods/good_1bfc80fd-6738-11ed-bb95-0050560120e8/-1_600.jpg",
            "чбсттчситчсбтбчстбтчсбмтчстмбчстмчстмбьчстисчбтичбьитсчбмтбчьтмчбьтмбсчьтичбсьитбьчсттибчсьтбьчстсбь",
            1000.0,
            "Планшеты"
        ),
        TechnicResponse(
            "Apple Watch SE 2",
            "https://cdn21vek.by/img/galleries/8109/201/preview_b/watchse2gps40mmmnt73_apple_63dcae7e5e0fb.jpeg",
            "ыовпрыподываопдвпжлваовадопваыдлровылорвылоповлаопвалоповадлповдлровадлроваждлровдалровабдро",
            1020.0,
            "Смарт-часы"
        ),
        TechnicResponse(
            "Amazfit Bip 3 Pro",
            "https://xistore.by/upload/resize/element/102822/9d0/67dde73d8cd4eff6eea6474e48926f6a_482_482_85.webp",
            "укщгщшугкнукщгнукшеукшузшкуншзукшнзщукшнзщукшнзщшзушкзщшукзншукшнзщукшнзщукшнзщушнзщузщкшнзщукнзукшз",
            1050.0,
            "Смарт-часы"
        ),
        TechnicResponse(
            "Beko CNMV5335E20VXR",
            "https://cdn21vek.by/img/galleries/7167/643/preview_b/cnmv5335e20vxr_beko_61f39422ad104.jpeg",
            "уаьмщуькзцуьзльцуьсщьцусзьцузсьцзщусьцзщьсцзущсьзсьузсьзьуцзщсьцзщуьсзщцуьсзщьзщцуьсжцсуцьжл",
            1070.0,
            "Холодильники"
        ),
        TechnicResponse(
            "Samsung RS64R5331B4",
            "https://cdn21vek.by/img/galleries/5960/594/preview_b/rs64r5331b4wt_samsung_5fcdf68a633f4.jpeg",
            "ьччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччч",
            9999.0,
            "Холодильники"
        )
    )

    fun getCategories(): List<String> = listOf(
        "Телефоны",
        "Планшеты",
        "Смарт-часы",
        "Холодильники"
    )
}