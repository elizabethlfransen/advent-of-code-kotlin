data class Position(
    val depth: Int = 0,
    val horizontal: Int = 0,
    val aim: Int = 0,
) {
    operator fun plus(other: Position) = Position(
        depth + other.depth,
        horizontal + other.horizontal,
        aim + other.aim
    )
}

enum class Direction {
    FORWARD,
    DOWN,
    UP
}

data class Movement(
    val amount: Int,
    val direction: Direction
)

fun parseMovement(line: String): Movement {
    val parts = line.split(' ')
    return Movement(parts[1].toInt(), Direction.valueOf(parts[0].uppercase()))
}

fun main() {
    fun reduceMovement(input: List<String>, reducer: (position: Position, movement: Movement) -> Position) =
        input
            .map(::parseMovement)
            .fold(Position(), reducer)
            .let { position -> position.depth * position.horizontal }

    fun part1(input: List<String>): Int {
        return reduceMovement(input) { position, movement ->
            when (movement.direction) {
                Direction.FORWARD ->
                    position + Position(horizontal = movement.amount)
                Direction.DOWN ->
                    position + Position(depth = movement.amount)
                Direction.UP ->
                    position + Position(depth = -movement.amount)
            }
        }
    }

    fun part2(input: List<String>): Int {
        return reduceMovement(input) { position, movement ->
            when (movement.direction) {
                Direction.FORWARD ->
                    position + Position(
                        horizontal = movement.amount,
                        depth = position.aim * movement.amount
                    )
                Direction.DOWN ->
                    position + Position(aim = movement.amount)
                Direction.UP ->
                    position + Position(aim = -movement.amount)
            }

        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    check(part2(testInput) == 900)
    println(part1(input))
    println(part2(input))
}
