package com.anushka.coroutinesdemo1

import java.util.*

/**
 * We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.


Input: asteroids = [5,10,6, -5]
Output: [5,10, 6]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Input: asteroids = [10,9]
Output: [10,-4]
Explanation: The 8 and -8 collide exploding each other.
 */
val updatedStack = Stack<Int>()
fun main() {
    var inputArray = arrayListOf<Int>(-11, 8, 2, -6, 15, 7, -8)
    var i = 0
    while (i < (inputArray.size - 1)) {
        val currentItem = inputArray.get(i)
        val nextItem = inputArray.get(i + 1)
        i = compareAndReturnI(currentItem, nextItem, i)
    }
    println("Final output : ")
    for (item in updatedStack) {
        print("$item, ")
    }
}

fun compareAndReturnI(currentItem: Int, nextItem: Int, index: Int): Int {
    var i = index
    if (currentItem > 0) {
        //currentItem is positive
        if (nextItem < 0) {
            //next item is negative
            if (currentItem == (nextItem * -1)) {
                //both collide
                i = i + 2
            } else if (currentItem - (nextItem * -1) > 0) {
                //current item is winner
                updatedStack.add(currentItem)
                i = i + 2
            } else {
                //next Item is winner
                compareWithStackItems(nextItem)
                i = i + 2
            }
        } else {
            //both are in same direction so add them to stack
            // updatedStack.add(currentItem)
            compareWithStackItems(currentItem)
            // updatedStack.add(nextItem)
            i = i + 1
        }
    } else if (nextItem > 0) {
        //currentItem is negative & next Item is positive
        if ((currentItem * -1) == nextItem) {
            i = i + 2
        } else if ((currentItem * -1) - (nextItem) > 0) {
            //current item is winner
            //updatedStack.add(currentItem)
            compareWithStackItems(currentItem)
            compareWithStackItems(nextItem)
            i = i + 2
        } else {
            //next Item is winner
            compareWithStackItems(nextItem)
            i = i + 2
        }
    } else {
        //both are in same direction so add them both
        compareWithStackItems(currentItem)
        compareWithStackItems(nextItem)
        //updatedStack.add(nextItem)
        i = i + 2
    }
    return i
}

private fun compareWithStackItems(nextItem: Int) {
    if (updatedStack.isEmpty()) {
        updatedStack.add(nextItem)
    } else {
        while (updatedStack.isNotEmpty()) {
            val stackItem = updatedStack.pop()
            print(" Comparing $nextItem with ")
            println("poppedItem : $stackItem")
            if (stackItem > 0) { //stackItem is positive
                if (nextItem < 0) { // nextItem is negative
                    //they collide
                    if (stackItem == (nextItem * -1)) {
                        //both Collide
                        break
                    } else if (stackItem - (nextItem * -1) > 0) {
                        //stackItem wins
                        updatedStack.add(stackItem)
                        break
                    } else {
                        //nextItem wins
                        if (updatedStack.isEmpty()) {
                            updatedStack.add(nextItem)
                            break
                        }
                    }
                } else {
                    //If both are in same direction, add the next item to stcak as well
                    updatedStack.add(stackItem)
                    updatedStack.add(nextItem)
                    break
                }
            } else if (nextItem > 0) { // stackItem is negative
                if ((stackItem * -1) == nextItem) {
                    //both collide
                    break
                } else if (nextItem - (stackItem * -1) > 0) {
                    //nextItem wins
                    if (updatedStack.isEmpty()) {
                        updatedStack.add(nextItem)
                        break
                    }
                } else {
                    //stackItem wins
                    updatedStack.add(stackItem)
                    break
                }
            } else {
                //both are in same direction
                updatedStack.add(stackItem)
                updatedStack.add(nextItem)
                break
            }
        }
    }
    println("Printing redid stack items : ")
    for (item in updatedStack) {
        print("$item, ")
    }
    println("")
}

