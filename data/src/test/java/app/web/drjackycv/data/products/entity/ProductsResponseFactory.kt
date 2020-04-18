package app.web.drjackycv.data.products.entity

import app.web.drjackycv.domain.products.entity.Cluster
import app.web.drjackycv.domain.products.entity.Item

class ProductsResponseFactory {

    companion object {
        fun providesClustersResponse() = ClustersResponse(
            listOf(providesClusterResponse(), providesClusterResponse(tag = "Sugar & Home Baking"))
        )

        fun providesProductDetailResponse(
            id: Int = 309396011,
            price: String = "1.45",
            title: String = "MVVMTemplate Organic Fairtrade Bananas",
            imageUrl: String = "https://mobile.mvvmtemplate.com/webservices/catalogue/items/item/309396011/images/image/0/360x360.jpg",
            description: String = "Organic. Suitable for vegetarians",
            allergyInformation: String = "May contain traces of Sesame Seeds"
        ) = ProductResponse(
            id = id,
            price = price,
            title = title,
            imageUrl = imageUrl,
            description = description,
            allergyInformation = allergyInformation
        )

        private fun providesClusterResponse(
            tag: String = "Bananas",
            items: List<Item> = listOf(
                providesItemResponse(),
                providesItemResponse(id = 386138011)
            )
        ) = Cluster(
            tag = tag,
            items = items
        )

        private fun providesItemResponse(
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