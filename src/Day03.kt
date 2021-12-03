fun main() {

    fun toCount(c: Char) = if (c == '1')
        1
    else
        -1

    fun part1(input: List<String>): Int {
        val result = IntArray(input[0].length)
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
        var i = 0
        val oxygenInput = input.toMutableList()
        val co2Input = input.toMutableList()
        while (oxygenInput.size > 1) {
            var counts = oxygenInput.groupBy { line -> line[i] }
            var charToRemove = if((counts['0']?.size ?: 0) > (counts['1']?.size ?: 0)) '1' else '0'
            oxygenInput.removeIf { s -> s[i] == charToRemove }
            i++
        }
        i = 0
        while (co2Input.size > 1) {
            var counts = co2Input.groupBy { line -> line[i] }
            var charToRemove = if((counts['0']?.size ?: 0) > (counts['1']?.size ?: 0)) '0' else '1'
            co2Input.removeIf { s -> s[i] == charToRemove }
            i++
        }
        // TODO
        return oxygenInput[0].toInt(2) * co2Input[0].toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    check(part2(testInput) == 230)
    println(part1(input))
    println(part2(input))
}
