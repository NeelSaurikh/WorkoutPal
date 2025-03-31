package com.example.workoutpal

object Constants {

    fun defaultExerciseList(): ArrayList<ExceriseModel>{
        val exerciseList = ArrayList<ExceriseModel>()

        val jumpingJacks = ExceriseModel(
            1,
            "JumpingJacks",
            R.drawable.jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val pushups = ExceriseModel(
            2,
            "Push Ups",
            R.drawable.push_ups,
            false,
            false
        )
        exerciseList.add(pushups)

        val pushupandrotate = ExceriseModel(
            3,
            "Push & Rotate",
            R.drawable.pushup_and_rotate,
            false,
            false
        )
        exerciseList.add(pushupandrotate)

        val lunges = ExceriseModel(
            4,
            "Lunges",
            R.drawable.lunges,
            false,
            false
        )
        exerciseList.add(lunges)

        val leftplank = ExceriseModel(
            5,
            "Left Plank",
            R.drawable.left_plank,
            false,
            false
        )
        exerciseList.add(leftplank)

        val plank = ExceriseModel(
            6,
            "Plank",
            R.drawable.plank,
            false,
            false
        )
        exerciseList.add(plank)

        val chairstandup = ExceriseModel(
            7,
            "Chair Stands Ups",
            R.drawable.chair_stand_up,
            false,
            false
        )
        exerciseList.add(chairstandup)

        val tricepchair = ExceriseModel(
            8,
            "Chair Tricep Dip",
            R.drawable.tricep_chair,
            false,
            false
        )
        exerciseList.add(tricepchair)

        val abdominalCrunches = ExceriseModel(
            9,
            "Abdominal Crunches",
            R.drawable.abdominal_crunches,
            false,
            false
        )
        exerciseList.add(abdominalCrunches)

        val wallSit = ExceriseModel(
            10,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        val squats = ExceriseModel(
            11,
            "Squats",
            R.drawable.squats,
            false,
            false
        )
        exerciseList.add(squats)

        val highKnees = ExceriseModel(
            12,
            "High Knees",
            R.drawable.high_knees,
            false,
            false
        )
        exerciseList.add(highKnees)

        return exerciseList
    }
}