class HashMapPatterns {

    fun subarraySum(nums: IntArray, k: Int): Int {
        val prefixSumArr = IntArray(nums.size + 1)
        for (i in 1..prefixSumArr.size - 1) {
            prefixSumArr[i] = nums[i - 1] + prefixSumArr[i - 1]
        }
        var retValue = 0
        for (i in nums.indices) {
            for (j in i until nums.size) {
                if (prefixSumArr[j+1] - prefixSumArr[i+1] == k) {
                    retValue++
                }
            }
        }
        return retValue
    }
}