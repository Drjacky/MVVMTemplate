package app.web.drjackycv.core.testing.data

import app.web.drjackycv.core.domain.products.entity.Beer
import app.web.drjackycv.core.domain.products.entity.Location

object TestData {

    val testBeer = Beer(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(name = "Earth (C-137)", url = "https://rickandmortyapi.com/api/location/1"),
        location = Location(name = "Citadel of Ricks", url = "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z",
    )

    val testBeer2 = Beer(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(name = "Earth (C-137)", url = "https://rickandmortyapi.com/api/location/1"),
        location = Location(name = "Citadel of Ricks", url = "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        url = "https://rickandmortyapi.com/api/character/2",
        created = "2017-11-04T18:50:21.651Z",
    )

    val testBeer3 = Beer(
        id = 3,
        name = "Summer Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Female",
        origin = Location(name = "Earth (Replacement Dimension)", url = "https://rickandmortyapi.com/api/location/20"),
        location = Location(name = "Earth (Replacement Dimension)", url = "https://rickandmortyapi.com/api/location/20"),
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        url = "https://rickandmortyapi.com/api/character/3",
        created = "2017-11-04T19:09:56.428Z",
    )

    @Suppress("LongParameterList")
    fun createBeer(
        id: Int = 1,
        name: String = "Test Character",
        status: String = "Alive",
        species: String = "Human",
        type: String = "",
        gender: String = "Male",
        origin: Location = Location(name = "Earth", url = ""),
        location: Location = Location(name = "Earth", url = ""),
        image: String = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg",
        episode: List<String> = emptyList(),
        url: String = "https://rickandmortyapi.com/api/character/$id",
        created: String = "2017-11-04T18:48:46.250Z",
    ) = Beer(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
        episode = episode,
        url = url,
        created = created,
    )
}
