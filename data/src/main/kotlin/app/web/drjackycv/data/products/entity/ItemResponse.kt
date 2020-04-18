package app.web.drjackycv.data.products.entity


import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.products.entity.Item
import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: String,
    @SerializedName("title") val title: String,
    @SerializedName("size") val size: String,
    @SerializedName("imageUrl") val imageUrl: String
) : ResponseObject<Item> {

    override fun toDomain(): Item = Item(
        id = id,
        price = price,
        title = title,
        size = size,
        imageUrl = imageUrl
    )

}