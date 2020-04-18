package app.web.drjackycv.domain.products.entity

class ProductsFactory {

    companion object {
        fun providesClusters(): Clusters = Clusters(
            listOf(providesCluster(), providesCluster(tag = "Sugar & Home Baking"))
        )

        fun providesProductDetail(
            id: Int = 309396011,
            price: String = "1.45",
            title: String = "MVVMTemplate Organic Fairtrade Bananas",
            imageUrl: String = "https://mobile.mvvmtemplate.com/webservices/catalogue/items/item/309396011/images/image/0/360x360.jpg",
            description: String = "Organic. Suitable for vegetarians",
            allergyInformation: String = "May contain traces of Sesame Seeds"
        ) = Product(
            id = id,
            price = price,
            title = title,
            imageUrl = imageUrl,
            description = description,
            allergyInformation = allergyInformation
        )

        private fun providesCluster(
            tag: String = "Bananas",
            items: List<Item> = listOf(providesItem(), providesItem(id = 386138011))
        ) = Cluster(
            tag = tag,
            items = items
        )

        private fun providesItem(
            id: Int = 309396011,
            price: String = "1.45",
            title: String = "MVVMTemplate Organic Fairtrade Bananas",
            size: String = "6 per pack",
            imageUrl: String = "https://mobile.mvvmtemplate.com/webservices/catalogue/items/item/309396011/images/image/0/240x240.jpg"
        ) = Item(
            id = id,
            price = price,
            title = title,
            size = size,
            imageUrl = imageUrl
        )
    }

}