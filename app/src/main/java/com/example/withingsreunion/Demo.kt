package com.example.withingsreunion

import java.sql.Time


fun calculateStepTimeRanges(steps: List<Pair<Time, Int>>): List<Pair<Time, Time>> {
    val timeRanges = mutableListOf<Pair<Time, Time>>()
    var startTime: Time? = null
    var endTime: Time? = null
    var stepsCount = 0

    for (step in steps) {
        val timestamp = step.first
        val stepCount = step.second

        if (stepCount > 0) {
            if (stepsCount == 0) {
                startTime = timestamp
            }
            endTime = timestamp
            stepsCount += stepCount
        } else {
            if (stepsCount > 0) {
                timeRanges.add(Pair(startTime!!, endTime!!))
                startTime = null
                endTime = null
                stepsCount = 0
            }
        }
    }

    // Add the last time range if the steps were ongoing until the end
    if (startTime != null && endTime != null) {
        timeRanges.add(Pair(startTime, endTime))
    }

    return timeRanges
}

fun main() {
    val steps = listOf(
        Pair(Time.valueOf("08:00:00"), 1000),
        Pair(Time.valueOf("08:15:00"), 0),
        Pair(Time.valueOf("09:00:00"), 1500),
        Pair(Time.valueOf("09:30:00"), 2000),
        Pair(Time.valueOf("09:45:00"), 0),
        Pair(Time.valueOf("10:30:00"), 500),
        Pair(Time.valueOf("11:00:00"), 0)
    )

    val timeRanges = calculateStepTimeRanges(steps)
    for (range in timeRanges) {
        println("Début : ${range.first}, Fin : ${range.second}")
    }
}
