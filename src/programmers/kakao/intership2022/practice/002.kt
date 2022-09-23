/*
    카카오 인턴 코테 2022 002 
    level 1
    https://school.programmers.co.kr/learn/courses/30/lessons/118666?language=kotlin

    [R, T]
    [C, F]
    [J, M]
    [A, N]
    4개의 지표가 있으니 2*2*2*2 = 16 으로 총 16가지의 경우의 수 존재

    survey[i]의 첫번째 캐릭터는 i+1번 질문의 비동의 관련 선택지
    survey[i]의 두번째 캐릭터는 i+1번 질문의 동의 관련 선택지

    choices.size == survey.size
    chioces[i]는 검사자가 선택한 i+1번의 응답

    하나의 유형에서 같은 점수가 나온다면 사전순으로 빠른게 선택된다

    [1, 매우 비동의] 3점
    [2, 비동의] 2점
    [3, 약간 비동의] 1점
    [4, 모르겠음] 0점
    [5, 약간 동의] 1점
    [6, 동의] 2점 
    [7, 매우 동의] 3점
*/
/*
    map에 유형:점수 저장

*/

fun main(args: Array<String>){
    val survey = arrayOf(
        "TR", "RT", "TR"
    )
    val choices = intArrayOf(
        7, 1, 3
    )
    val res = Kakao2022002Practice().solve(survey, choices)
    println(res)
}

private class Kakao2022002Practice {
    private val map: MutableMap<Char, Int> = hashMapOf()
    private val surveyPrint = arrayOf(
        "RT",
        "CF",
        "JM",
        "AN"
    )
    fun solve(survey: Array<String>, choices: IntArray): String {
        
        repeat(survey.size){
            processChoice(survey[it], choices[it])
        }
        println(map)
        val sb = StringBuffer()
        repeat(surveyPrint.size){
            sb.append(getSurveyResult(surveyPrint[it].toString()))
        }
        return sb.toString()
    }

    private fun processChoice(survey: String, choice: Int){
        when {
            choice < 4 -> {
                map[survey[0]] = map.getOrDefault(survey[0], 0) + (4 - choice)
            }
            choice > 4 -> {
                map[survey[1]] = map.getOrDefault(survey[1], 0) + (choice - 4)
            }
        }
    }
    private fun getSurveyResult(survey: String): String {
        val a = map.getOrDefault(survey[0], 0)
        val b = map.getOrDefault(survey[1], 0)
        return when {
            a == b ->  {
                return if(survey[0]<survey[1])
                    survey[0].toString()
                else
                    survey[1].toString()
            }
            a > b -> {
                survey[0].toString()
            }
            else -> {
                survey[1].toString()
            }
        }
    }
}