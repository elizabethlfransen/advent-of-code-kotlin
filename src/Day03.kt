fun main() {

    fun toCount(c: Char) = if (c == '1')
        1
    else
        -1

    fun part1(input: List<String>): Int {
        val result = IntArray(input[0].length)
        result.fill(0)
        input.forEach { line ->
            line
                .map(::toCount)
                .forEachIndexed { index, count ->
                    result[index] += count
                }
        }
        val gamma = result
            .map { c -> if (c > 0) '1' else '0' }
            .joinToString(separator = "")
            .toInt(2)
        val epsilon = result
            .map { c -> if (c > 0) '0' else '1' }
            .joinToString(separator = "")
            .toInt(2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {

        // TODO
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
