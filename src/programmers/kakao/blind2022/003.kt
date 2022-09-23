/*
    카카오 2022 블라인드 003
    level 2
    https://school.programmers.co.kr/learn/courses/30/lessons/92341

    주차장 요금표, 입차, 출차 기록을 확인하여
    차량별로 주차 요금 계산

    차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아 리턴

    차량이 입차 후 출차 내역이 없다면 23:59에 출차로 간주
    00:00 - 23:59 까지 입/출차 내역을 바탕으로 차량별 누적 주차시간을 계산하여 요금을 일괄로 정산
    누적 주차시간이 기본시간 이하라면, 기본요금
    누적 주차시간이 기본시간 초과시, 기본요금 + 초과한 시간에 대해 단위시간 마다 단위요금 청구
    초과한 시간이 단위시간으로 나눠떨어지지 않는다면 올림 
    [a] : a보다 작지 않은 최소의 정수 => 올림

    주차장에 없는 차량이 출차되는 경우
    주차장에 이미 있는 차량(차량번호가 같은 차량)이 다시 입차되는 경우
    는 주어지지 않는다
*/
/*
    records 배열 해석
    시각, 차량번호, 내역 => 공백을 기준으로 나눠짐
    시각 => 00:00 의 5자리 문자열 고정
    차량번호 => 4자리 정수
    내역 => IN or OUT
*/
/*
    - 차량별 이용시각을 계산하면 거의 끝
    carInMap: Map<Int, Int> // 차량번호, 입차시간 4자리 수
    carOutMap: Map<Int, Int> // 차량번호, 입차시간 4자리 수
*/
/*
    - forEach 내부에서 remove를 실행하여 런타임 에러 발생했었음
*/

fun main(args: Array<String>){
    val fees = intArrayOf(
        // 180, 5000, 10, 600
        // 120, 0, 60, 591
        1, 461, 1, 10
    )
    val records = arrayOf(
        // "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        // "16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"
        "00:00 1234 IN"
    )
    KakaoBlind2022003().solution(fees, records)
}

class KakaoBlind2022003 {
    private var carInMap = mutableMapOf<Int, Int>()
    private var carMinuteMap = mutableMapOf<Int, Int>()
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        saveRecords(records, fees)
        val moneys = IntArray(carMinuteMap.keys.size)
        carMinuteMap.keys.sortedBy { it }.forEachIndexed { i, car -> 
            moneys[i] = calculateMoney(car, carMinuteMap[car]!!, fees)
        }
        
        println(moneys.toList())

        return moneys
    }

    private fun saveRecords(records: Array<String>, fees: IntArray){
        records.forEach { record -> 
            record.split(' ').let { 
                val time = it[0].replace(":", "").toInt()
                val car = it[1].toInt()
                when(it[2]){
                    "OUT" -> {
                        calculateTime(car, carInMap[car]!!, time, fees)
                        carInMap[car] = -1
                    }
                    "IN" -> {
                        carInMap[car] = time
                    }
                }
            }
        }
        carInMap.keys.forEach { car ->
            if(carInMap[car] != -1){
                calculateTime(car, carInMap[car]!!, 2359, fees)
                carInMap[car] = -1
            }
        }
    }

    private fun calculateTime(car: Int, inTime: Int, outTime: Int, fees: IntArray){
        val inTimM = (inTime/100*60)+inTime%100
        val outTimeM = (outTime/100*60)+outTime%100
        println("$car => in[$inTime], out[$outTime]")

        var timeM = outTimeM - inTimM
        carMinuteMap[car] = carMinuteMap.getOrDefault(car, 0) + timeM
    }

    private fun calculateMoney(car: Int, useMinute: Int, fees: IntArray): Int{
        var money: Int = fees[1]
        var minute = useMinute
        when {
            minute > fees[0] -> {
                minute -= fees[0]
                println("after default[$money] => $minute need pay ${fees[3]*(minute/fees[2])}")
                money += fees[3]*(minute/fees[2])
                minute %= fees[2]
                if(minute != 0){
                    println("add, $money to ${fees[3]}")
                    money += fees[3]
                }
            }
        }
        println("$car: use $useMinute, pay $money")
        return money
    }
}