/*
    카카오 2022 인턴 코테 3번

    알고력 코딩력 구하기

    알고리즘에 대한 지식은 알고력
    코드를 구현하는 능력은 코딩력

    문제를 풀기 위해서는 문제가 요구하는 일정 이상의 알고력과 코딩력이 필요합니다

    예)
    알고력15, 코딩력10

    A : 알고력10, 코딩력10 => 가능
    B : 알고력10, 코딩력20 => 불가능

    알고력과 코딩력을 높이는 방법
    - 알고력 : 알고리즘 공부를 진행. +1에 1시간 필요
    - 코딩력 : 코딩 공부 진행. +1에 1시간 필요

    주어진 모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 구하라
*/
/*
    alp : 알고력
    cop : 코딩력

    문제의 정보 problems

    0<= alp, cop <=150
    1<= problems <=100

    problem : 
    - alp_req : 푸는데 필요한 알로력
    - cop_req : 푸는데 필요한 코딩력
    - alp_rwd : 풀었을때 증가하는 알고력
    - cop_rwd : 풀었을때 증가하는 코딩력
    - cost : 문제를 푸는데 드는 시간
*/
/*
    주어진 모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간
    

    경우의 수
    0 : 자연공부로 시간을 늘린다
    1 : 1번 문제를 푼다
    2 : 2번 문제를 푼다
*/


/*
    문제 list를 만든다
    
    모든 문제를 풀기 위해 내가 모자란 코딩력, 알고력을 구한다

    알고력10, 코딩력5가 모자라네
    이걸 채우기 위해 할 수 있는 경우의 수

    0 : 자연공부
    1 : 1번문제를 푼다
    2 : 2번문제를 푼다

    이중 효율을 따져보자
    내가 현재 할 수 있는 행동중에 가장 보상이 많은것을 체크?

*/
/*
    문제를 푸는데 필요한 최고 alp, cop를 구한다 => 목표
    여기서 문제를 풀면서 rwd값들을 빼나간다

    초기값까지 만드는데 걸리는 시간
*/
/*
    그리디 알고리즘같다

    현재 점수를 올리기 위한 최적의 루트를 찾는것

    1. 현재 올려야 하는 점수를 구한다
    2. 
*/
fun main(args : Array<String>){
    // val problems = arrayOf(
    //     intArrayOf(10,15,2,1,2),
    //     intArrayOf(20,20,3,3,4)
    // )
    val problems = arrayOf(
        intArrayOf(0,0,2,1,2),
        intArrayOf(4,5,3,1,2),
        intArrayOf(4,11,4,0,2),
        intArrayOf(10,4,0,4,2)
    )
    // Kakao2022003().solution(10, 10, problems)
    Kakao2022003().solution(0, 0, problems)
}

data class Problem (
    val alpReq : Int,
    val copReq : Int,
    val alpRwd : Int,
    val copRwd : Int,
    val cost : Int
){
    constructor(arr : IntArray) : this(arr[0], arr[1], arr[2], arr[3], arr[4])
}
/*
    그냥 시간초과 
*/
private class Kakao2022003 {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var maxAlp = Int.MIN_VALUE
        var maxCop = Int.MIN_VALUE

        val pList = Array(problems.size){it -> Problem(problems[it])}
        for(problem in pList){
            maxAlp = Math.max(maxAlp, problem.alpReq)
            maxCop = Math.max(maxCop, problem.copReq)
        }

        println("maxAlp : $maxAlp, maxCop : $maxCop")

        var time = 0

        loop@while(true){
            // 풀 문제를 찾는다
            var needAlp = maxAlp - alp 
            var needCop = maxCop - cop
            println("needAlp : $needAlp, needCop : $needCop")
            val problem = if(needAlp > needCop){
                print("alp ")
                findMaxAlpRwdProblem(maxAlp, maxCop, needAlp, pList)
            }else{
                print("cop ")
                findMaxCopRwdProblem(maxAlp, maxCop, needCop, pList)
            }
            // 풀 문제가 없다면 break
            println("find problem : $problem")
            if(problem == null){
                break
            }

            // 문제를 풀어서 need 값들을 까먹는다 => 이 문제를 풀지 못하거나, 이 문제를 풀었을때 need가 음수가 되기 전까지
            while(true){
                // max 값을을 까먹다보니, 이 문제를 못푸는 수준이 되면 이 문제 다푼거
                if(problem.alpReq > maxAlp || problem.copReq > maxCop){
                    println("break2")
                    break
                }
                maxAlp -= problem.alpRwd
                if(maxAlp < 0)maxAlp = 0
                needAlp = maxAlp - alp
                maxCop -= problem.copRwd
                if(maxCop < 0)maxCop = 0
                needCop = maxCop - cop
                time += problem.cost
                println("===================================")
                println("maxAlp : $maxAlp, needAlp : $needAlp")
                println("maxCop : $maxCop, needCop : $needCop")
                println("time : $time")
                // 음수가 되었다는건, 다른 효율좋은 문제를 다시 찾아볼수도 있다는것
                if(needAlp < 0 || needCop < 0){
                    println("break1")
                    break
                }
                if(maxAlp == alp && maxCop == cop){
                    println("break0")
                    break@loop
                }
            }
        }
        println("solve finish all problem needAlp : ${maxAlp - alp}, needCop : ${maxCop - cop}, time : $time")

        // 풀수 있는 문제는 다 풀었다. 남은 need값은 개인공부해라
        if(maxAlp - alp >= 0)
            time += maxAlp - alp
        if(maxCop - cop >= 0)
            time += maxCop - cop

        println("time = $time\n")
        return time
    }
    /*
        alp, cop : 현재 alp cop값
        need : 깎아야 할 값

        current >= problem.alpReq
        내가 풀 수 있는 문제여야 한다
    */
    private fun findMaxAlpRwdProblem(alp : Int, cop : Int, need : Int, problems: Array<Problem>) : Problem?{
        var maxAlpRwd = 0
        var idx = -1
        for((i, problem) in problems.withIndex()){
            // 내가 풀수 없는 문제
            if(alp < problem.alpReq || cop < problem.copReq){
                continue
            }
            // 보상이 크고, 그리고 need 보다 보상이 크면 안된다
            if(maxAlpRwd < problem.alpRwd && need >= problem.alpRwd){
                maxAlpRwd = problem.alpRwd
                idx = i
            }
        }
        return if(idx < 0)
            null
        else
            problems[idx]
    }
    
    private fun findMaxCopRwdProblem(alp : Int, cop : Int, need : Int, problems: Array<Problem>) : Problem?{
        var maxCopRwd = 0
        var idx = -1
        for((i, problem) in problems.withIndex()){
            // 내가 풀수 없는 문제
            if(alp < problem.alpReq || cop < problem.copReq){
                continue
            }
            // 보상이 크고, 내가 현재 풀 수 있는 문제, 그리고 need 보다 보상이 크면 안된다
            if(maxCopRwd < problem.copRwd && need >= problem.copRwd){
                maxCopRwd = problem.copRwd
                idx = i
            }
        }
        return if(idx < 0)
            null
        else
            problems[idx]
    }
}
