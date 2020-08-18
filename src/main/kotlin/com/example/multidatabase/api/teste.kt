package com.example.multidatabase.api

import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


fun main(args: Array<String>) {
    val list = mutableListOf<Int>()
    list.addAll(listOf(2, 1, 3, 5, 3, 2))
    firstDuplicate2(list)

    firstNotRepeatingCharacter("abacabad")


    var mult = mutableListOf<Int>(1, 2, 3)
    var mult2 = mutableListOf<Int>(4, 5, 6)
    var mult3 = mutableListOf<Int>(7, 8, 9)
    var multTotal = mutableListOf<MutableList<Int>>(mult, mult2, mult3)
    rotateImage(multTotal)

}

fun firstDuplicate(a: MutableList<Int>): Int {
    var lessIndex = a.size
    var result = -1

    first@ for ((index, it) in a.iterator().withIndex()) {
        var indexTo = index+1
        second@ for (indexActual in indexTo until a.size)
            if(it == a[indexActual]){
                if(indexActual in 1 until lessIndex){
                    lessIndex = indexActual
                    result = it
                    if(result == 1) break@first
                }else if(indexActual > lessIndex) break@second

            }
    }
    return result
}


fun firstDuplicate2(a: MutableList<Int>): Int {

    var list = HashSet<Int>()
    var retorno = -1;
    var indexLess = a.size

    for ((index, i) in a.iterator().withIndex()) {
        if(list.contains(i) && index < indexLess) {
            retorno = i
            indexLess = index
        }
        list.add(i)
    }
    return retorno
}

fun firstNotRepeatingCharacter(s: String): Char {

    //abacabad
    //bcb

    var str = s
    for ((index, i) in s.iterator().withIndex()) {
        if(index+1 < s.length && s.subSequence(index+1, s.length).contains(i)){
            str = str.replace(i.toString(), "")
        }
    }
    if(str.isEmpty()) return '_'
    return str[0]
}

fun rotateImage(a: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {

 /*   Input:
    a:
       [[1,2,3],
        [4,5,6],
        [7,8,9]]
    Expected Output:
       [[7,4,1],
        [8,5,2],
        [9,6,3]]
 */


    var list = mutableListOf<MutableList<Int>>()

    a.asReversed().forEach {
        for ((index, i) in it.iterator().withIndex()) {
            var listInt = mutableListOf<Int>()
            listInt.add(i)
            list.add(listInt)
        }
    }

    return list
}


