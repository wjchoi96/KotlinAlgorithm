//sliver 5
// 12-8

import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val a = Array<String>(size){""}
    for(i in 0 until size){
        a[i] = br.readLine()
    }

    quickSort3(a, 0, size-1)

    for(i in 0 until size){
        if(i-1 >= 0 && a[i] == a[i-1]){
            continue
        }
        bw.write("${a[i]}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun quickSort3(a : Array<String>, start : Int, end : Int){
    var pl = start
    var pr = end
    val x = a[(start + end)/2]

    print("a[$start] ~ a[$end] : {")
    for(i in a){
        print("$i ")
    }
    print("}\n")

    do{
        // pl포인터의 값이 피벗보다 커질때까지 반복
        while(compare(a[pl], x) < 0) pl++
        // pr포인터의 값이 피벗보다 작아질때까지 반복
        while(compare(a[pr], x) > 0) pr--
        if(pl<=pr){
            swap(a, pl++, pr--)
        }
    }while(pl<=pr)

    if(start < pr) quickSort3(a, start, pr)
    if(pl < end)quickSort3(a, pl, end)
}

// str1 이 길이가 더 짧거나 사전적으로 더 앞이면 음수, 아니면 양수
private fun compare(str1 : String, str2 : String) : Int {
    if(str1.length == str2.length){
        var count1 = 0
        var count2 = 0
        while(count1 != str1.length && count2 != str2.length){
            if(str1[count1++].code == str2[count2++].code){
                continue
            }else{
                // 사전적으로 앞에 있을수록 아스키 코드가 더 작다
                // str1의 앞자리가 더 사전에 앞에있다면 음수, 반대면 양수 리턴
                return str1[count1-1].code - str2[count2-1].code
            }
        }
    }else{
        //str1의 길이가 더 길면 양수, 반대면 음수가 리턴될것
        return str1.length - str2.length
    }
    return 0
}

private fun swap(arr : Array<String>, idx1 : Int, idx2 : Int){
    val t = arr[idx1]
    arr[idx1] = arr[idx2]
    arr[idx2] = t
}