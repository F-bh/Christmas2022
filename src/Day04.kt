fun main() {
    val input = readDayInput(4)
    val pairList = input.map { line ->
        val pairList = line.split(",")
        Pair(pairList[0], pairList[1])
    }.map { pair ->
        val split1 = pair.first.split("-")
        val split2 = pair.second.split("-")
        Pair(
            split1[0].toInt()..split1[1].toInt(),
            split2[0].toInt()..split2[1].toInt()
        )
    }
    pt1(pairList)
    pt2(pairList)
}

fun pt1(pairs:  List<Pair<IntRange, IntRange>>){
    val res = pairs
    .filter { pair ->
        pair.first.contains(pair.second.first) && pair.first.contains(pair.second.last) ||
        pair.second.contains(pair.first.first) && pair.second.contains(pair.first.last)
    }
    println(res.size)
}

fun pt2(pairs:  List<Pair<IntRange, IntRange>>) {
    val res = pairs.
    filter { pair ->
        pair.first.contains(pair.second.first) || pair.first.contains(pair.second.last) ||
        pair.second.contains(pair.first.first) || pair.second.contains(pair.first.last)
    }
    println(res.size)
}
