fun main() {
    val tempMap = ('a'..'z').
    fold(mutableMapOf<Char, Int>()) { map, ch -> map[ch] = map.size+1; map }

    val valueMap = ('A'..'Z').
    fold(tempMap) { map, ch -> map[ch] = map.size+1; map }.toMap()

    val input = readDayInput(3)

    pt1(valueMap, input)
    pt2(valueMap, input)
}

fun pt1(valMap: Map<Char, Int>, lines: List<String>){
    val res =  lines
        .map { line -> line.chunked(line.length/2)}
        .map { bag ->
            bag[0].fold(mutableListOf<Char>()) { list, item ->
                bag[1].fold(list) { _, item2 ->
                    if (item2 == item && !list.contains(item))
                        list.add(item)
                    list}
            }
        }
        .flatten()
        .sumOf { item ->
            valMap[item] ?: 0
        }
    println(res)
}

fun pt2(valMap: Map<Char, Int>, lines: List<String>) {
    val res =  lines.chunked(3)
        .map { group ->
            group[0].fold(mutableSetOf<Char>()){ set, item ->
                if (group[1].contains(item) && group[2].contains(item))
                   set.add(item)
                set
            }
        }.flatten()
        .sumOf { item ->
            valMap[item] ?: 0
        }
    println(res)
}
