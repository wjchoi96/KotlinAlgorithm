/*
    카카오 2022 인턴 코테 1번

    성격유형 검사(MBTI 같은거)

    4개지표로 성격 유형을 구분
    성격은 각 지표에서 두 유형 중 하나로 결정된디ㅏ

    4개의 지표가있으니 유형은 2*2*2*2 = 16가지가 나올 수 있다

    검사지에는 총n개의 질문이 존재
    각각 7가지의 선택지가 존재

    매우 비동의 - N3점
    비동의     - N2점
    약간 비동의 - N1점
    모르겠음  - 어떤 유형도 점수가 없음
    약간 동의 - A1점
    동의     - A2점
    매우 동의 - A3점

    질문에 따라 동의 비동의의 유형은 바뀔 수 있다(위 예시대로면 매우 비동의가 a3점으로)
    하지만 각 선택지는 고정적인 점수를 가지고 있다

    매우~ : 3점
    common : 2점
    약간 ~ : 1점
    모르겠음 : 0점

    모든 질문의 성격 유형점수를 더해
    각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격유형이라 판단

    하나의 지표에서 각 성격유형 점수가 같으면, 두 성격 유형 중 사전순으로 빠른 성격 유형으로 판단
*/
/*
    1<= survey <=1,000
    survey 의 원소는 [RT, TR, FC, CF, MJ, JM, AN, NA] 중 하나

    survey[i]의 첫번째 캐릭터는 i+1번 질문의 비동의 관련 선택지를 선택하면 받는 성격 유형
    survey[i]의 두번째 캐릭터는 i+1번 질문의 동의 관련 선택지를 선택하면 받는 성격 유형

    choices 길이 = survey의 길이
    choices[i] = 검사자가 선택한 i+1번쨰 질문의 선택지

    1<= choices[i] <= 7
    choice의 숫자가 적을수록 비동의 쪽
    [1 : 매우 비동의] => survey[i-1]의 첫번째 문자 + 3
    [2 : 비동의] => survey[i-1]의 첫번째 문자 + 2
    [3 : 약간 비동의] => survey[i-1]의 첫번째 문자 + 1
    [4 : 모르겠음] => 0
    [5 : 약간 동의] => survey[i-1]의 두번째 문자 + 1
    [6 : 동의] => survey[i-1]의 두번째 문자 + 2
    [7 : 매우 동의] => survey[i-1]의 두번째 문자 + 3
*/
/*
    survey[i] 와 choice[i+1] 을 넣어서 총 결과 map에 저장?
    
    다 map에 점수 계산하고
    1번은 rt중에 점수 높은거
    2번은 ~ 이렇게 하자
*/

fun main(args : Array<String>){
    val survey = arrayOf(
        "AN", "CF", "MJ", "RT", "NA"
        // "TR", "RT", "TR"
        // "AN", "CF", "MJ", "RT", "NA", "AN", "CF"
    )
    val choices = intArrayOf(
        5, 3, 2, 7, 5
        // 7, 1, 3
        // 5, 3, 2, 7, 5, 2, 7
    )
    val res = Kakao2022001().solution(survey, choices)
    println("$res")
}

private class Kakao2022001 {
    private val map : HashMap<String, Int> = HashMap()
    fun solution(survey: Array<String>, choices: IntArray): String {

        for(i in 0 until survey.size){
            solveSurvey(survey[i], choices[i])
        }
        return getResult()
    }

    private fun getResult() : String {
        val s1 = getTypeSurvey("RT")
        val s2 = getTypeSurvey("CF")
        val s3 = getTypeSurvey("JM")
        val s4 = getTypeSurvey("AN")
        return "$s1$s2$s3$s4"
    }

    private fun getTypeSurvey(survey : String) : String {
        val s1 = survey[0].toString() 
        val s2 = survey[1].toString() 
        
        val s1Count = map.getOrDefault(s1, 0)
        val s2Count = map.getOrDefault(s2, 0)
        print("$s1 : $s1Count\n")
        print("$s2 : $s2Count\n")
        return if(s1Count == s2Count){
            s1
        }else if(s1Count > s2Count){
            s1
        }else{
            s2
        }
    }

    private fun solveSurvey(survey : String, choice : Int){
        val s1 = survey[0].toString() // choice 3 이하면 여기에 +
        val s2 = survey[1].toString() // choice 가 5이상이면 여기에 +
        
        when(choice){
            1 -> map[s1] = map.getOrDefault(s1, 0) + 3
            2 -> map[s1] = map.getOrDefault(s1, 0) + 2
            3 -> map[s1] = map.getOrDefault(s1, 0) + 1
            
            5 -> map[s2] = map.getOrDefault(s2, 0) + 1 // getOrDefault(s1, 0) 으로 오타나서 30분 날린듯
            6 -> map[s2] = map.getOrDefault(s2, 0) + 2 // getOrDefault(s1, 0) 으로 오타나서 30분 날린듯
            7 -> map[s2] = map.getOrDefault(s2, 0) + 3 // getOrDefault(s1, 0) 으로 오타나서 30분 날린듯
        }
        println("$s1 => ${map.getOrDefault(s1, 0)}")
        println("$s2 => ${map.getOrDefault(s2, 0)}")
    }
}