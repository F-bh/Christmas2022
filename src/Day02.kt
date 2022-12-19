enum class Result (val points: Int) {
    Win(6),
    Draw(3),
    Lose(0);

    companion object {
        fun parse(input: Char): Result {
            return mapOf(
                'X' to Lose,
                'Y' to Draw,
                'Z' to Win
            )[input]!!
        }
    }
}

enum class Choice(val points: Int) {
    Rock(1),
    Paper(2),
    Scissor(3);

    operator fun plus(enemyChoice: Choice): Int {
       return lookupTwoChoice[Pair(this, enemyChoice)]!!.points + this.points
    }

    companion object {
        fun parse(input: Char): Choice? {
            return mapOf(
                'A' to Rock,
                'B' to Paper,
                'C' to Scissor,
            )[input]
        }

        private val lookupTwoChoice = mapOf(
            Pair(Rock, Scissor) to Result.Win,
            Pair(Rock, Rock) to Result.Draw,
            Pair(Rock, Paper) to Result.Lose,
            Pair(Paper, Rock) to Result.Win,
            Pair(Paper, Paper) to Result.Draw,
            Pair(Paper, Scissor) to Result.Lose,
            Pair(Scissor, Paper) to Result.Win,
            Pair(Scissor, Scissor) to Result.Draw,
            Pair(Scissor, Rock) to Result.Lose,
        )

        val lookupChoiceAndRes = lookupTwoChoice.entries.associate { (k, v) ->
            Pair(k.second, v) to k.first
        }
    }
}


fun main() {
    val input =  readDayInput(2)
        .map { line -> line.split(" ") }
        .map {strings -> Pair(strings[0].toCharArray()[0], strings[1].toCharArray()[0])}

    //part 1
    println(p1(input))

    //part 2
    println(p2(input))
}

fun p1(input:  List<Pair<Char, Char>>): Int {
    fun parse(input: Char): Choice {
        return Choice.parse(input) ?:
         mapOf(
            'X' to Choice.Rock,
            'Y' to Choice.Paper,
            'Z' to Choice.Scissor,
        )[input]!!
    }

    return input.sumOf { pair -> parse(pair.second) + parse(pair.first) }
}

fun p2(input:  List<Pair<Char, Char>>): Int {
    return input
        .map { pair ->
            Pair(Choice.parse(pair.first), Result.parse(pair.second))
        }
        .sumOf { duel -> duel.second.points + Choice.lookupChoiceAndRes[duel]!!.points }
}