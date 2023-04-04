package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.models.TechnicResponse
import com.example.technicstoreapp.domain.UserData
import javax.inject.Inject

class Server @Inject constructor() {

    fun addUserToList(userData: UserData) {
        userList.add(userData)
    }

    fun getUserById(idUser: String): UserData = userList.first { it.id == idUser }

    fun checkLogIn(number: String, password: String): UserData? {
        return if (userList.none { it.id == number && it.hashPassword == password }) {
            null
        } else {
            userList.first { it.id == number && it.hashPassword == password }
        }
    }

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
            "Apple iOS, экран 4.7\" IPS (750x1334), Apple A11 Bionic, ОЗУ 2 ГБ, память 64 ГБ, камера 12 Мп, аккумулятор 1820 мАч, 1 SIM (nano-SIM), влагозащита IP67",
            799.00,
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
            "Apple iOS, экран 5.8\" AMOLED (1125x2436), Apple A11 Bionic, ОЗУ 3 ГБ, память 64 ГБ, камера 12 Мп, аккумулятор 2716 мАч, 1 SIM (nano-SIM), влагозащита IP67",
            1090.00,
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
            "12.9\" IPS, 120 Гц (2732x2048), iOS, Apple M2, ОЗУ 8 Гб, флэш-память 128 ГБ, цвет серебристый",
            4400.00,
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
            "10.2\" IPS, 60 Гц (2160x1620), iPadOS, Apple A13 Bionic, ОЗУ 3 ГБ, флэш-память 256 ГБ, цвет темно-серый",
            1638.91,
            "Планшеты"
        ),
        TechnicResponse(
            6,
            "Apple Watch SE 2",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/5177817/img_id6237219671660909594.png/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/7388241/img_id1112452931486745517.jpeg/orig",
                "найк" to "https://avatars.mds.yandex.net/get-mpic/4383514/img_id5598416085487204553.jpeg/orig"
            ),
            "умные часы, поддержка iOS, экран OLED, шагомер, пульсометр, время работы: 18 часов, корпус: алюминий, браслет: силикон",
            1099.00,
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
            "умные часы, поддержка Android/iOS, экран TFT 1.69\" (240x280, сенсорный), шагомер, пульсометр, время работы: 2 недели, корпус: пластик, браслет: силикон",
            199.00,
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
            "отдельностоящий, полный No Frost, электронное управление, класс A+, полезный объём: 300 л (200 + 100 л), перенавешиваемые двери, лоток для яиц, 54x60x201 см, антрацит",
            1559.00,
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
            "Высокий двухкамерный холодильник с нижней морозильной камерой оснащен технологией HarvestFresh с трехцветной подсветкой в ящике для фруктов и овощей, которая дольше сохраняет продукты свежими и полезными. Система NoFrost Dual Cooling (Ноу Фрост)",
            1756.00,
            "Холодильники"
        ),
        TechnicResponse(
            10,
            "Samsung Galaxy S22 Ultra",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/6338835/img_id3981391512228697779.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/6479667/img_id4624620053166369658.jpeg/orig",
                "зеленый" to "https://avatars.mds.yandex.net/get-mpic/6417556/img_id8505201014944947590.jpeg/orig"
            ),
            "Android, экран 6.8\" AMOLED (1440x3088) 120 Гц, Exynos 2200, ОЗУ 8 ГБ, память 128 ГБ, камера 108 Мп, аккумулятор 5000 мАч, 2 SIM (nano-SIM), влагозащита IP68",
            4399.00,
            "Телефоны"
        ),
        TechnicResponse(
            11,
            "HONOR X6",
            mapOf(
                "бирюзовый" to "https://avatars.mds.yandex.net/get-mpic/5323398/img_id7332517806758878908.jpeg/orig",
                "черный" to "https://avatars.mds.yandex.net/get-mpic/1417902/img_id4606099779282957207.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/4334326/img_id3190618949770734072.jpeg/orig"
            ),
            "Android, экран 6.5\" IPS (720x1600) 60 Гц, Mediatek Helio G25, ОЗУ 4 ГБ, память 64 ГБ, поддержка карт памяти, камера 50 Мп, аккумулятор 5000 мАч, 2 SIM (nano-SIM)",
            432.44,
            "Телефоны"
        ),
        TechnicResponse(
            12,
            "Xiaomi POCO M5",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/5209949/img_id1652965839842128938.jpeg/orig",
                "бирюзовый" to "https://avatars.mds.yandex.net/get-mpic/7650323/img_id6526108371014111137.jpeg/orig",
                "желтый" to "https://avatars.mds.yandex.net/get-mpic/1930823/img_id6110298512056846971.jpeg/orig"
            ),
            "Эффективный игрок.Флагманский техпроцесс 6 нм.Мощный восьмиядерный процессор.Восьмиядерный процессор MediaTek Helio G99 создан по передовому техпроцессу 6 нм. ",
            599.00,
            "Телефоны"
        ),
        TechnicResponse(
            13,
            "vivo Y22",
            mapOf(
                "голубой" to "https://avatars.mds.yandex.net/get-mpic/5349375/img_id1101814555756375446.jpeg/orig",
                "синий" to "https://avatars.mds.yandex.net/get-mpic/4076910/img_id3478771976689282642.jpeg/orig",
                "черный" to "https://avatars.mds.yandex.net/get-mpic/7547374/img_id8602031584972389429.jpeg/orig"
            ),
            "Android, экран 6.55\" IPS (720x1612) 60 Гц, Mediatek Helio G85, ОЗУ 4 ГБ, память 64 ГБ, поддержка карт памяти, камера 50 Мп, аккумулятор 5000 мАч, 2 SIM (nano-SIM)",
            459.00,
            "Телефоны"
        ),
        TechnicResponse(
            14,
            "HUAWEI Nova 10",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/4615588/img_id4252994111233812445.jpeg/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/1767151/img_id2141658422846138260.png/orig",
                "серый" to "https://avatars.mds.yandex.net/get-mpic/4412310/img_id7996534549846528978.jpeg/orig"
            ),
            "Android, экран 6.67\" OLED (1080x2400) 120 Гц, Qualcomm Snapdragon 778G, ОЗУ 8 ГБ, память 128 ГБ, камера 50 Мп, аккумулятор 4000 мАч, 2 SIM (nano-SIM)",
            1299.00,
            "Телефоны"
        ),
        TechnicResponse(
            15,
            "Apple iPad mini 2021",
            mapOf(
                "серый" to "https://avatars.mds.yandex.net/get-mpic/6479667/img_id6045973670849485420.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/6212300/img_id7639501669384516198.jpeg/orig",
                "фиолетовый" to "https://avatars.mds.yandex.net/get-mpic/6479667/img_id3442377146142639203.jpeg/orig"
            ),
            "8.3\" IPS (2266x1488), iPadOS, Apple A15 Bionic, ОЗУ 4 ГБ, флэш-память 64 ГБ, цвет темно-серый",
            1898.00,
            "Планшеты"
        ),
        TechnicResponse(
            16,
            "Samsung Galaxy Tab A8",
            mapOf(
                "серый" to "https://avatars.mds.yandex.net/get-mpic/4267432/img_id2475114551437361755.png/orig",
                "белый" to "https://avatars.mds.yandex.net/get-mpic/5234126/img_id2572607660917479660.png/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/5221946/img_id7578278412875205729.png/orig"
            ),
            "10.5\" TFT, 60 Гц (1920x1080), Android, Unisoc Tiger T618, ОЗУ 4 ГБ, флэш-память 128 ГБ, цвет темно-серый",
            926.84,
            "Планшеты"
        ),
        TechnicResponse(
            17,
            "TCL 10 TABMAX ",
            mapOf(
                "бирюзовый" to "https://avatars.mds.yandex.net/get-mpic/1101307/img_id1120830034625350920.jpeg/orig",
                "серый" to "https://avatars.mds.yandex.net/get-mpic/4080484/img_id458856249613495130.jpeg/orig",
                "черный" to "https://avatars.mds.yandex.net/get-mpic/5320264/img_id2449810971142215980.jpeg/orig"
            ),
            "10.36\" IPS, 60 Гц (2000x1200), Android, MediaTek MT8788A, ОЗУ 4 ГБ, флэш-память 64 ГБ, цвет серый",
            829.00,
            "Планшеты"
        ),
        TechnicResponse(
            18,
            "BQ 8077L Exion Plus",
            mapOf(
                "черный" to "https://avatars.mds.yandex.net/get-mpic/4881627/img_id5226842838561579835.jpeg/orig",
                "желтый" to "https://avatars.mds.yandex.net/get-mpic/5215227/img_id1283359290167826062.jpeg/orig",
                "красный" to "https://avatars.mds.yandex.net/get-mpic/5262552/img_id8369970612345014867.png/orig"
            ),
            "8.0\" IPS (1280x800), Android, Unisoc SC9863A, ОЗУ 3 ГБ, флэш-память 32 ГБ, цвет серебристый",
            336.75,
            "Планшеты"
        ),
        TechnicResponse(
            19,
            "Amazfit T-Rex 2",
            mapOf(
                "хаки" to "https://avatars.mds.yandex.net/get-mpic/7430655/img_id689713748581560113.jpeg/orig",
                "черный" to "https://avatars.mds.yandex.net/get-mpic/4012413/img_id6888866324902498728.png/orig",
                "зеленый" to "https://avatars.mds.yandex.net/get-mpic/5319505/img_id5232074385210026527.jpeg/orig"
            ),
            "умные часы, поддержка Android/iOS, экран AMOLED 1.39\" (454x454, сенсорный), шагомер, пульсометр, время работы: 6 недель 3 суток, корпус: пластик, браслет: силикон",
            663.00,
            "Смарт-часы"
        ),
        TechnicResponse(
            20,
            "Apple Watch SE",
            mapOf(
                "белый" to "https://avatars.mds.yandex.net/get-mpic/4464754/img_id2950807816586518059.jpeg/orig",
                "розовый" to "https://avatars.mds.yandex.net/get-mpic/5236733/img_id5847205111383374229.jpeg/orig",
                "желтый" to "https://avatars.mds.yandex.net/get-mpic/5247291/img_id7644035021415556579.jpeg/orig"
            ),
            "умные часы, поддержка iOS, экран OLED, шагомер, пульсометр, время работы: 18 часов, корпус: алюминий, браслет: силикон",
            1099.00,
            "Смарт-часы"
        ),
        TechnicResponse(
            21,
            "BEKO B1RCNK402W",
            mapOf(
                "серый" to "https://content2.onliner.by/catalog/device/header/24c51bf19fba9f89919c2b02c3c37a06.jpeg",
                "белый" to "https://content2.onliner.by/catalog/device/header/fa47f9823663f514d3ac1dc2592af286.jpeg",
                "золотой" to "https://content2.onliner.by/catalog/device/header/607a0a2eeecb1ee1aecd1dc9570b9080.jpeg"
            ),
            "отдельностоящий, полный No Frost, механическое управление, класс A+, полезный объём: 357 л (257 + 100 л), перенавешиваемые двери, лоток для яиц, 59.5x65x201 см, белый",
            1339.89,
            "Холодильники"
        ),
        TechnicResponse(
            21,
            "BEKO RCSK310M20S",
            mapOf(
                "серый" to "https://content2.onliner.by/catalog/device/header/d78dc1a02f02569337bf60c51545a8bf.jpeg",
                "розовый" to "https://content2.onliner.by/catalog/device/header/fd35227d3161bc2a0e932dcf9d2fd9f7.jpeg",
                "белый" to "https://content2.onliner.by/catalog/device/header/506bf90cec2de3905e8fa838293594b7.jpeg"
            ),
            "отдельностоящий, без No Frost, механическое управление, класс A+, полезный объём: 300 л (213 + 87 л), перенавешиваемые двери, лоток для яиц, 54x60x184 см, серебристый",
            1101.78,
            "Холодильники"
        )
    )

    fun getColorsTechnic(id: Int): List<String> =
        getAllTechnic().first { it.id == id }.colors?.keys?.toList() ?: emptyList()

    fun getImageTechnic(id: Int, color: String): String =
        getAllTechnic().first { it.id == id }.colors?.get(color)
            .toString()

    fun getCategories(): List<String> = listOf(
        "Телефоны|https://cdn-icons-png.flaticon.com/512/545/545245.png",
        "Планшеты|https://cdn-icons-png.flaticon.com/512/5495/5495918.png",
        "Смарт-часы|https://cdn-icons-png.flaticon.com/512/3197/3197701.png",
        "Холодильники|https://cdn-icons-png.flaticon.com/512/4151/4151229.png"
    )

    companion object {
        private val userList = mutableListOf(
            UserData(
                id = "fe65b1fd44aa4fd522a2cb11d71843e4f6854010535567dc9ef955a2bb29b815",
                name = "Nikita",
                hashPassword = "4aaddb02457f15ef2be140605b177a76e3c1d13db8c24e020668ff808e7a8531",
                number = "+375 (29) 804-31-37",
                address = "",
                email = "dfvdfvdfvdfv",
                discountPoints = "0",
                carts = emptyList(),
                dateOfBirth = "15.05.2003"
            )
        )
    }
}