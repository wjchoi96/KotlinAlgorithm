/*
    =========== 시간복잡도 ==================
    list 에서의 탐색(contains)는 O(n) 이지만
    set, map 에서의 탐색(contains)는 O(1) 로 훨씬 빠르기 때문

    탐색이 잦은 작업을 할때는 list 대신, set, map 을 사용

    다만 set 과, map 의 key 값은 중복이 허용되지 않는다

    Collections 의 연산 시간복잡도
    https://www.grepiu.com/post/9

    빠른 순서 ↑
    상수 시간    O(1)
    로그시간     O(log N)
    직선형 시간  O(N)
    2차 시간     O(n^2)
    지수 시간    O(C^n)
    느린 순서 ↓

    

*/