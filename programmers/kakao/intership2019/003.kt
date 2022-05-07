/*
    카카오 2019 인턴 기출문제 3번
    level 3

    불량 사용자

    불량 사용자 목록
    아이디 중 일부 문자를 *로 변경

    아이디당 최소 하나 이상의 문자가 *로 변경

    불량 사용자 목록에 매핑된 응모자 아이디를 제재 아이디라 칭함

    불량사용자의 id를 가져온다

    1<= userid <=8
    1<= userId[item] <= 8
    
    1<= bannedId <= userid.size
    1<= bannedId[item] <= 8

    8*8 = 64
    64*8 = 272

    bannedId 를 순회

    banId
    - 전체 사이즈
    - *들의 위치
    => userId를 순회하여 사이즈가 같은 id를 찾는다
    => *들의 위치를 userID에 적용
    => 비교
*/

fun main(args : Array<String>){
    val userIds = arrayOf(
        // "frodo", "fradi", "crodo", "abc123", "frodoc"
        "frodo", "fradi", "crodo", "abc123", "frodoc"
    )
    val bannedIds = arrayOf(
        // "fr*d*", "abc1**"
        "*rodo", "*rodo", "******"
    )
    Kakao2019003().solution(userIds, bannedIds)
}

private class Kakao2019003 {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val map = HashMap<String, Int>()
        for(banndId in banned_id){
            for(userId in user_id){
                if(userId.count() != banndId.count()){
                    continue
                }
                var startList = ArrayList<Int>()
                for(i in 0 until banndId.count()){
                    if(banndId[i] == '*'){
                        startList.add(i)
                    }
                }
                startList.reverse()

                var uid = StringBuilder(userId)
                var bid = banndId.replace("*", "")
                for(starIdx in startList){
                    uid.deleteCharAt(starIdx)
                }  
                if(bid == uid.toString()){
                    print("[$userId, $banndId]\n")
                } 
                if(bid == uid.toString()){
                    map[userId] = map.getOrDefault(userId, 0) + 1
                }
            }
        }
        print("$map\n")
        return 0
    }
}