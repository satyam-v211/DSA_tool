package google

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var i = 0
    var j = 0
    for (k in numbers.size - 1 downTo 0) {
        val num = numbers[k]
        if (num <= (target - numbers[i])) {
            j = k
            break
        }
    }
    var sum = numbers[i] + numbers[j]
    while (sum != target) {
        if (sum > target) {
            j--
        } else {
            i++
        }
        sum = numbers[i] + numbers[j]
    }
    return intArrayOf(i + 1, j + 1)
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
   var wI = m + n - 1
   if(m == 0 && n == 0) return
   if(n == 0) return
   var rI1 = m - 1
   var rI2 = n - 1
   while(wI>0 && rI1 > 0 && rI2 > 0){
      val num1 = nums1[rI1]
      val num2 = nums2[rI2]
      if(num2 > num1){
          nums1[wI] = num2
      }else{
         nums1[wI] = num1
      }
      wI--
   }
}
