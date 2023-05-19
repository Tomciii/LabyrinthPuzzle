package com.example.labyrinthpuzzle.view.screen

const val LABYRINTH_TILE_ID = "labyrinthTileID"
const val PUZZLE_ARCHETYPE_ID = "puzzleArcheTypeID"
const val PUZZLE_ID = "puzzleID"

sealed class Screen (val route: String) {
    object HomeScreen : Screen("home")

    object HowToScreen : Screen("howTo")

    object SettingsScreen : Screen("settings")

    object ViewLabyrinthScreen : Screen("viewLabyrinthScreen")

    object LabyrinthTileScreen : Screen("labyrinth/{$LABYRINTH_TILE_ID}") {
        fun withId(id: String): String {
            return this.route.replace(oldValue = "{$LABYRINTH_TILE_ID}", newValue = id)
        }
    }

    object PuzzleScreen : Screen("puzzle/{$PUZZLE_ARCHETYPE_ID}/{$PUZZLE_ID}") {

        fun withIds(archetypeId: String, puzzleId: String): String {
            var routeWithIds = this.route
            routeWithIds = routeWithIds.replace("{$PUZZLE_ARCHETYPE_ID}", archetypeId)
            routeWithIds = routeWithIds.replace("{$PUZZLE_ID}", puzzleId)
            return routeWithIds
        }
    }
}