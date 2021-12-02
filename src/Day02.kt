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
    fun part1Apply(position: Position, movement: Movement) = when (movement.direction) {
        Direction.FORWARD ->
            position + Position(horizontal = movement.amount)
        Direction.DOWN ->
            position + Position(depth = movement.amount)
        Direction.UP ->
            position + Position(depth = -movement.amount)
    }

    fun part1(input: List<String>): Int {
        return input
            .map(::parseMovement)
            .fold(Position(), ::part1Apply)
            .let { p -> p.depth * p.horizontal }
    }

    fun part2(input: List<String>): Int {
        // TODO
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
