package ru.vk.apps.test_data

import ru.vk.apps.domain.model.App
import ru.vk.common.domain.Category
import javax.inject.Inject

class TestData @Inject constructor() {
    val apps = listOf(
        App(
            id = "1",
            name = "СберБанк Онлайн - c Салютом",
            category = Category.FINANCE,
            iconUrl = "https://static.rustore.ru/imgproxy/5DBuRi4ibjpr7w6t7-Vb6guitE-OOhe46ifQ2FOt5XY/preset:web_app_icon_160/plain/https://static.rustore.ru/1fea04e2-9ad7-475d-990d-02d16d351efd@webp",
            description = "Больше чем банк"
        ),
        App(
            id = "2",
            name = "Яндекс Браузер - С Алисой",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/bZNt9jiZUOVXXOG0JdJQleTYIB2cFeE3MaWk7o897jE/preset:web_app_icon_160/plain/https://static.rustore.ru/2025/10/25/1e/apk/579007/content/ICON/939321c0-03f7-484d-9043-c0fb12736ef1.png@webp",
            description = "Быстрый и безопасный бразуер"
        ),
        App(
            id = "3",
            name = "Почта Mail.ru",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/2wnsbc-wCmdbFYEdpH8uL3Jl4db6i7HE9Vj5079oh6Q/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/3/11/7c/apk/332223/content/ICON/2ea61211-2ee2-469b-a08e-acc8a9f3b4c6.png@webp",
            description = "Почтовый клиент для любых ящиков"
        ),
        App(
            id = "4",
            name = "Яндекс Навигатор",
            category = Category.MAPS,
            iconUrl = "https://static.rustore.ru/imgproxy/FvKuW-aUKk34jUz1ZEPXebdfDR0ikU93-JJYC5_Oh4Y/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/595135/content/ICON/32cb5e63-9c59-4280-9a6a-c808113be88f.png@webp",
            description = "Парковки и заправки - по пути"
        ),
        App(
            id = "5",
            name = "Мой МТС",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/uAJeOzFbun_tDiquzvvs_kieJ8ihjODiwCb7LGISdos/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/336831/content/ICON/ea6c9e63-bd7f-486f-ac3f-3e9069ecf018.png@webp",
            description = "Мой МТС - центр экосистемы МТС"
        ),
        App(
            id = "6",
            name = "MAX: общение, звонки, сервисы",
            category = Category.SOCIAL,
            iconUrl = "https://static.rustore.ru/imgproxy/JNQXxouJcssXg2hd8FaJX6Bj9OXC2l0Xd2KBtHWxo6c/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/24/6f/apk/2063618637/content/ICON/0564e0a3-a2be-414e-8fec-55257fedea8e.png@webp",
            description = "Мессенджер"
        ),
        App(
            id = "7",
            name = "SQLight playground",
            category = Category.EDUCATION,
            iconUrl = "https://static.rustore.ru/imgproxy/BlE7l8K6zxZ9A2D1oaaW0qZd7vq67MoHYxlTGrz3lZM/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2063648560/content/ICON/8cadfacb-194e-4de6-a029-c3bbc4404b88.png@webp",
            description = "Изучение синтаксиса SQL"
        ),
        App(
            id = "8",
            name = "ВКонтакте: чаты, видео, музыка",
            category = Category.SOCIAL,
            iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
            description = "Социальная сеть"
        )
    )
}