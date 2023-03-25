package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.models.TechnicResponse
import javax.inject.Inject

class Server @Inject constructor() {

    fun getAllTechnic(): List<TechnicResponse> = listOf(
        TechnicResponse(
            1,
            "Смартфон Apple iPhone 14 Pro Max 128GB",
            mapOf(
                "черный" to "https://shop.mts.by/upload/resize_cache/webp/iblock/c5f/p1z6qjgh6tdg4737vztex7rqhp5ifzid/270_500_1/iPhone_14_Pro_Max_Black_1.webp",
                "белый" to "https://shop.mts.by/upload/resize_cache/webp/iblock/991/f1kpt753kxzkrsnhy2ur383eopg80uts/270_500_1/iPhone_14_Pro_Max_White_1.webp",
                "золотой" to "https://shop.mts.by/upload/resize_cache/webp/iblock/0da/dny8vimwuqntr0w899b8rq6t3ylb9ph3/270_500_1/iPhone_14_Pro_Max_Gold_1.webp"
            ),
            "Apple iOS, экран 6.7\" OLED (1290x2796) 120 Гц, Apple A16 Bionic, ОЗУ 6 ГБ, память 128 ГБ, камера 48 Мп, аккумулятор 4323 мАч, 1 SIM (nano-SIM/eSIM), влагозащита IP68",
            3817.00,
            "Телефоны"
        ),
        TechnicResponse(
            2,
            "Iphone 8",
            mapOf(
                "черный" to "https://shop.mts.by/upload/resize_cache/webp/iblock/341/cst5k7lh76lq3o4mxzyhhlfn87pssgcm/270_500_1/gray_1.webp",
                "белый" to "https://shop.mts.by/upload/resize_cache/webp/iblock/759/dd5m97tts55oi7jqh5r5x01dwum2esgg/270_500_1/silver-1.webp",
                "золотой" to "https://shop.mts.by/upload/resize_cache/webp/iblock/ba1/nmpis1q6cek0s012khohy315fjf5d74f/270_500_1/gold-1.webp"
            ),
            "1qwwwwwwwwweqweeeeeeeeee",
            600.7,
            "Телефоны"
        ),
        TechnicResponse(
            3,
            "Iphone x",
            mapOf(
                "черный" to "https://img.5element.by/import/images/ut/goods/good_81ac8c4d-3fc1-11ed-bb95-0050560120e8/iphone-x-b-u-64gb-space-gray-telefon-gsm-apple-2bmqac2-3_600.jpg",
                "белый" to "https://img.5element.by/import/images/ut/goods/good_b5de4500-cf56-11e7-80c9-00505684744b/good_img_894d11fa-3655-11e8-80c3-00505684296c_600.jpg",
                "золотой" to "https://shop.mts.by/upload/resize_cache/webp/iblock/cce/pywdkfhde1w880joybu4tqmw9iyawk8g/270_500_1/Silver_1.webp"
            ),
            "mnmmnnnnnnnnnnnnnnnn",
            800.0,
            "Телефоны"
        ),
        TechnicResponse(
            4,
            "Apple iPad Pro 12.9",
            mapOf(
                "черный" to "https://img.5element.by/import/images/ut/goods/good_c3ee2466-cb87-11e7-80c9-00505684744b/good_img_aa544b7c-38df-11e8-80c3-00505684296c_600.jpg",
                "белый" to "https://img.5element.by/import/images/ut/goods/good_d39f946a-cabe-11e7-80c9-00505684744b/good_img_00dc0e39-38d7-11e8-80c3-00505684296c_600.jpg",
                "золотой" to "https://img.5element.by/import/images/ut/goods/good_5aa2cc8a-a444-11eb-bb92-0050560120e8/mhr43rk-a-a2461-ipad-pro-wi-fi--plus-cellular-128gb-space-grey-planshet-apple-1_600.jpg"
            ),
            "iejrgjerjeroigjerjeoijelknnsv,jdfjvnjnsnvd,fvndfndjnbkjnekfvlernlkenblermlirnjgioerg",
            3000.0,
            "Планшеты"
        ),
        TechnicResponse(
            5,
            "Apple iPad 10.2",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/6647093/img_id498998991555209554.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/6382710/img_id3390920459963259491.png/orig",
                "серый" to "https://avatars.mds.yandex.net/get-mpic/6273606/img_id3985862946103356369.jpeg/orig"
            ),
            "чбсттчситчсбтбчстбтчсбмтчстмбчстмчстмбьчстисчбтичбьитсчбмтбчьтмчбьтмбсчьтичбсьитбьчсттибчсьтбьчстсбь",
            1000.0,
            "Планшеты"
        ),
        TechnicResponse(
            6,
            "Apple Watch SE 2",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/6647093/img_id498998991555209554.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/7388241/img_id1112452931486745517.jpeg/orig",
                "найк" to "https://avatars.mds.yandex.net/get-mpic/4383514/img_id5598416085487204553.jpeg/orig"
            ),
            "ыовпрыподываопдвпжлваовадопваыдлровылорвылоповлаопвалоповадлповдлровадлроваждлровдалровабдро",
            1020.0,
            "Смарт-часы"
        ),
        TechnicResponse(
            7,
            "Amazfit Bip 3 Pro",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/4303532/img_id6910416738879334657.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/1362400/img_id3273563002750261843.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/1060343/img_id145774430878710512.jpeg/orig"
            ),
            "укщгщшугкнукщгнукшеукшузшкуншзукшнзщукшнзщукшнзщшзушкзщшукзншукшнзщукшнзщукшнзщушнзщузщкшнзщукнзукшз",
            1050.0,
            "Смарт-часы"
        ),
        TechnicResponse(
            8,
            "Beko CNMV5335E20VXR",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/5366650/img_id768041951970023939.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/5332179/img_id8901982404901973584.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/5279470/img_id5873312728049095145.jpeg/orig"
            ),
            "уаьмщуькзцуьзльцуьсщьцусзьцузсьцзщусьцзщьсцзущсьзсьузсьзьуцзщсьцзщуьсзщцуьсзщьзщцуьсжцсуцьжл",
            1070.0,
            "Холодильники"
        ),
        TechnicResponse(
            9,
            "Beko RCNK 335E20",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/5210335/img_id3626995939602442775.jpeg/orig",
                "серый" to "https://avatars.mds.yandex.net/get-mpic/5228636/img_id5535369735075416904.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/5234026/img_id3817842498083274832.jpeg/orig"
            ),
            "ьччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччччч",
            9999.0,
            "Холодильники"
        )
    )

    fun getColorsTechnic(id: Int): List<String> =
        getAllTechnic().first { it.id == id }.colorsAndImageUrl?.keys?.toList() ?: emptyList()

    fun getImageTechnic(id: Int, color: String): String =
        getAllTechnic().first { it.id == id }.colorsAndImageUrl?.get(color)
            .toString()

    fun getCategories(): List<String> = listOf(
        "Телефоны",
        "Планшеты",
        "Смарт-часы",
        "Холодильники"
    )
}