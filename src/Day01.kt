fun main() {
    val elves = mutableListOf<MutableList<Int>>(mutableListOf())

    for (line in readInput("01_input")){
        if (line == ""){
            elves.add(mutableListOf())
            continue
        }

        elves.last() += line.toInt()
    }

    val max = elves.reduce { acc, calorieList ->
        acc.add(calorieList.sum())
        acc
    }
    max.sortDescending()
    //part 1 res
    println(max[0])

    //part 2 res
    println(max.take(3).sum())
}
