fun main() {
    fun part1(input: List<String>): Int {
        // empty check
        val depths = input.map(String::toInt)
        return depths
            .filterIndexed { index, depth ->
                index != 0 && depth > depths[index - 1]
            }
            .count()
    }

    fun part2(input: List<String>): Int {
        var depths = input.map(String::toInt)
        depths = depths
            .mapIndexed { index, depth ->
                if (index < 2)
                    return@mapIndexed null // partial
                depth + depths[index - 1] + depths[index - 2]
            }
            .filterNotNull()
        return depths.filterIndexed { index, depthSum ->
            index != 0 && depthSum > depths[index - 1]
        }
            .count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
